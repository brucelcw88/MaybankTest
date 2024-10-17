package com.bruce.services.user_service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.bruce.services.user_service.controller..*(..))") // Change to your package
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("API Call: {} - Request: {}", joinPoint.getSignature(), joinPoint.getArgs());

        Object result = joinPoint.proceed();

        logger.info("API Response: {}", result);

        return result;
    }
}