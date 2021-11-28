package com.chendehe.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CostTimeAspect {

    @Pointcut("execution(public * com.chendehe.controller..*.*(..))")
    public void controllerPoint() {
    }

    @Around("controllerPoint()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            long time = System.currentTimeMillis() - start;
            log.info("Request finished: " + className + "." + methodName + " cost: " + time + "ms.");
        }

    }

}
