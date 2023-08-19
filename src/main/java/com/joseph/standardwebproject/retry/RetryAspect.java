package com.joseph.standardwebproject.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Aspect
@Component
public class RetryAspect {

    @Pointcut("@annotation(com.joseph.standardwebproject.retry.MyRetryable)")
    private void retryPointcut(){}

    @Around("retryPointcut()")
    public Object retry(ProceedingJoinPoint joinPoint) throws InterruptedException {
        MyRetryable retryAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(MyRetryable.class);
        int maxRetryTimes = retryAnnotation.retryTimes();
        int retryInterval = retryAnnotation.retryInterval();

        for(int retryTimes = 1; retryTimes <= maxRetryTimes; retryTimes++ ){
            try{
                Object result = joinPoint.proceed();
                return result;
            } catch (Throwable e) {
                System.out.println("aop retry "+ retryTimes + " times");
            }
            TimeUnit.SECONDS.sleep(retryInterval);
        }
        System.out.println("aop "+joinPoint.getSignature().toShortString()+ " Won't retry, exit!");
        return null;
    }
}
