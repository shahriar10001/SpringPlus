package com.Application.Aspect;

import SVM.ObjectValue.ObjectResult;
import ServiceUtility.Extension;
import com.Application.Config.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
public class RestAspect {

    private String allowedPattern = "^[^#%&$*<>?/|]*$";

    private boolean isInjected(Object[] args) throws JsonProcessingException {

        String jsonStr = new ObjectMapper().writeValueAsString(Arrays.toString(args));

        if (jsonStr.matches(allowedPattern))
            return false;
        else
            return true;
    }

    @Around("execution(* com.Application.API.*.*.*(..))")
    public ResponseEntity<ObjectResult> setResult(ProceedingJoinPoint joinPoint) throws Throwable {

        try {

            Object[] args = joinPoint.getArgs();
            if (this.isInjected(args))
                throw new IllegalArgumentException(Arrays.toString(args));

            Date startProcess = new Date(System.currentTimeMillis());

            ResponseEntity<ObjectResult> obj = (ResponseEntity<ObjectResult>) joinPoint.proceed();

            Double durationTime = Extension.getDiffDateWithCurrentDateMilliSeconds(startProcess, TimeUnit.MILLISECONDS);

            obj.getBody().getTrace().setResponseTime(durationTime + " ms");

            return obj;

        } catch (Throwable ex) {
            ObjectResult obj = new ObjectResult();
            if (ex.getMessage().matches("\\d+"))
                obj.setError(ex.getMessage(), ErrorMessage.persianMessages.get(Integer.parseInt(ex.getMessage())));
            else
                obj.setError("Error_100", ex.getMessage());
            return ResponseEntity.ok(obj);
        }

    }
}
