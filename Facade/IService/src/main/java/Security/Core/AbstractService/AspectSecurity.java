package Security.Core.AbstractService;

import IService.Security.Authorize;
import IService.Security.IBaseService;
import SVM.ObjectValue.ObjectResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class AspectSecurity {

    @Autowired
    IBaseService _securityService;

    @Around(" @annotation(IService.Security.Authorize)")
    public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Authorize validateAction = method.getAnnotation(Authorize.class);
        String[] getValue = validateAction.role();

        List<String> roles = _securityService.getTokenEntity().getRoles().stream()
                .collect(Collectors.mapping(v -> v.getRoleName(), Collectors.toList()));

        Boolean hasAccess = roles.containsAll(Arrays.asList(getValue));

        if (hasAccess)
            return pjp.proceed();

        ObjectResult obj = ObjectResult.setError("not permission to access resource.");

        return obj;
    }
}