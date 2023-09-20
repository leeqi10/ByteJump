package com.qxy.bytejump.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodExecutionTimeAspect {

    @Around("@annotation(com.qxy.bytejump.config.MonitorExecutionTime)|| execution(* com.qxy.bytejump.service.impl.UserServiceImpl.*(..)) ")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            System.out.println(joinPoint.getSignature() + " executed in " + executionTime + " ns");
        }
    }
}
