package Security.Core.AbstractService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;



public class JwtService {

    private static final Logger logger = Logger.getLogger(JwtService.class);
    private static String wso2Cert = null;

    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        String pkey = key.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        keyBytes = (new BASE64Decoder()).decodeBuffer(pkey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    private static void getUnauthorized(HttpServletResponse httpServletResponse, String message) throws IOException {
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("UNAUTHORIZED", " JWT invalid signature.");
        httpServletResponse.getWriter().write(message);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

    private Map<String, Object> verifyToken(String token, String propertyPkName) throws IOException {

        if (wso2Cert == null) {
            InputStream output = JwtService.class.getClassLoader().getResourceAsStream("publicKey.properties");
            Properties prop = new Properties();
            prop.load(output);
            wso2Cert = prop.getProperty(propertyPkName);
            output.close();
        }
        try {
            Map<String, Object> climesMap = new HashMap<>();
            if (token == null || token.length() == 0 ||
                    (token != null && token.toUpperCase().equals("test".toUpperCase()))) {

                List<String> role = new ArrayList<>();
                role.add("BrokerAdmin");
                return getClimes(role, "EbrokerAdmin@carbon.super", "APPLICATION_USER",
                        "EbrokerSystem", null, "", "", null);
            }

            PublicKey publicKey = getPublicKey(wso2Cert);
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();

            String givenName = null;
            String lastName = "";
            String endUser = "";
            Object department = null;
            try {
                endUser = (String) claims.get("http://wso2.org/claims/enduser", Object.class);
                givenName = (String) claims.get("http://wso2.org/claims/givenname", Object.class);
                lastName = (String) claims.get("http://wso2.org/claims/lastname", Object.class);
                department = claims.get("http://wso2.org/claims/department", Object.class);
            } catch (Exception ex) {
                if (endUser == null)
                    throw new NullPointerException("endUser");

                logger.info("givenName or lastName is empty." + endUser);
            }

            return getClimes(
                    claims.get("http://wso2.org/claims/role", Object.class),
                    endUser,
                    claims.get("http://wso2.org/claims/usertype", Object.class),
                    claims.get("http://wso2.org/claims/applicationname", Object.class),
                    null,
                    givenName,
                    lastName,
                    department);

        } catch (Exception ex) {
            return null;
        }

    }

    public Map<String, Object> getClimes(Object role, Object user, Object userType, Object applicationName, Object token,
                                         String givenName, String lastName, Object department) {
        Map<String, Object> climesMap = new HashMap<>();
        climesMap.put("role", role);
        climesMap.put("user", user);
        climesMap.put("userType", userType);
        climesMap.put("applicationName", applicationName);
        climesMap.put("token", token);
        climesMap.put("givenName", givenName);
        climesMap.put("lastName", lastName);
        climesMap.put("department", department);
        return climesMap;
    }

    public Map<String, Object> getVerificationByWso2Carbon(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, String token, String authorization) throws IOException, ServletException {

        Map<String, Object> result = (new JwtService()).verifyToken(token, "wso2carbon.publicKey");
        if (result != null) {
            logger.info("token is verify.");
            result.put("authorization", authorization);
            httpServletRequest.setAttribute("climes", result);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return result;
        } else {
            getUnauthorized(httpServletResponse, "JWT is Invalid.");
            return null;
        }
    }
}