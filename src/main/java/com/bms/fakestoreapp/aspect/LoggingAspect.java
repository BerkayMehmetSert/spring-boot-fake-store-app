package com.bms.fakestoreapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.bms.fakestoreapp.service.*.*(..))")
    public void beforeServiceLog(JoinPoint joinPoint) {
        LOGGER.info("Service method called: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Before("execution(* com.bms.fakestoreapp.controller.*.*(..))")
    public void beforeControllerLog(JoinPoint joinPoint) {
        LOGGER.info("Controller method called: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Before("execution(* com.bms.fakestoreapp.repository.*.*(..))")
    public void beforeRepositoryLog(JoinPoint joinPoint) {
        LOGGER.info("Repository method called: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.bms.fakestoreapp.controller.*.*(..))", returning = "result")
    public void afterControllerLog(JoinPoint joinPoint, Object result) {
        LOGGER.info("Controller method returned: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
        LOGGER.info("Method returned value is : {}", result);
    }

    @AfterReturning(pointcut = "execution(* com.bms.fakestoreapp.service.*.*(..))", returning = "result")
    public void afterServiceLog(JoinPoint joinPoint, Object result) {
        LOGGER.info("Service method returned: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
        LOGGER.info("Method returned value is : {}", result);
    }

    @AfterReturning(pointcut = "execution(* com.bms.fakestoreapp.repository.*.*(..))", returning = "result")
    public void afterRepositoryLog(JoinPoint joinPoint, Object result) {
        LOGGER.info("Repository method returned: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
        LOGGER.info("Method returned value is : {}", result);
    }

    @AfterThrowing(pointcut = "execution(* com.bms.fakestoreapp.service.*.*(..))", throwing = "exception")
    public void afterThrowingServiceLog(JoinPoint joinPoint, Throwable exception) {
        LOGGER.info("Service method threw exception: " + joinPoint.getSignature().getName());
        LOGGER.info("Enter:{}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
        LOGGER.info("Exception is : {}", exception);
    }
}
