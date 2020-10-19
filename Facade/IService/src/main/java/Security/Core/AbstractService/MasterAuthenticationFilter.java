package Security.Core.AbstractService;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public abstract class MasterAuthenticationFilter implements Filter {
    private static final Logger logger = Logger.getLogger(MasterAuthenticationFilter.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        logger.info("Connect By :" + httpServletRequest.getRequestURL());

        String tokenValue = getToken(httpServletRequest);
        String authorization = getAuthorization(httpServletRequest);
        (new JwtService()).getVerificationByWso2Carbon(httpServletRequest, httpServletResponse, filterChain, tokenValue, authorization);

        logger.info("doFilter() method is ended");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init");
    }

    public void destroy() {
        logger.info("destroy");
    }

    public boolean getAppStatus(String host) {
        return host.contains("localhost");
    }

    public String getToken(HttpServletRequest httpServletRequest) throws IOException {
        if (getAppStatus(httpServletRequest.getHeader("host"))) {
            return getTokenTest();
        } else {
            String headerValue = "";
            Enumeration<String> headers = httpServletRequest.getHeaders("X-JWT-Assertion");
            while (headers.hasMoreElements()) {
                headerValue = headers.nextElement();
                logger.info(headerValue);
            }
            return headerValue;
        }

    }

    public String getAuthorization(HttpServletRequest httpServletRequest) throws IOException {
        if (getAppStatus(httpServletRequest.getHeader("host"))) {
            return "Bearer 0d49f308-37f2-3f6d-9c5e-443656cc9761";
        } else {
            String authorization = "";
            Enumeration<String> headers = httpServletRequest.getHeaders("Authorization");
            while (headers.hasMoreElements()) {
                authorization = headers.nextElement();
                logger.info(authorization);
            }
            return authorization;
        }

    }

    public String getTokenTest() throws IOException {
        InputStream output = JwtService.class.getClassLoader().getResourceAsStream("publicKey.properties");
        Properties prop = new Properties();
        prop.load(output);
        String tokenTest = prop.getProperty("app.TokenTest");
        output.close();
        return tokenTest;
    }
}
