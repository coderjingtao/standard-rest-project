package com.joseph.standardwebproject.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class RequestLogAspect {

    ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* com.joseph.standardwebproject.controller..*(..))")
    public void controllerRequest(){

    }

    @Around("controllerRequest()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //controller方法的执行结果
        Object result = proceedingJoinPoint.proceed();

        RequestLogInfo requestLogInfo = RequestLogInfo.builder()
                .ip(request.getRemoteAddr())
                .url(request.getRequestURL().toString())
                .httpMethod(request.getMethod())
                .classMethod(String.format("%s.%s",proceedingJoinPoint.getSignature().getDeclaringTypeName(),proceedingJoinPoint.getSignature().getName()))
                .requestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint))
                .result(result)
                .timeCost(System.currentTimeMillis() - start)
                .build();
        //用json格式输出
        log.info("Request Info : {}",objectMapper.writeValueAsString(requestLogInfo));
        return result;
    }

    @AfterThrowing(pointcut = "controllerRequest()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, RuntimeException e) throws JsonProcessingException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        RequestLogError requestLogError = RequestLogError.builder()
                .ip(request.getRemoteAddr())
                .url(request.getRequestURL().toString())
                .httpMethod(request.getMethod())
                .classMethod(String.format("%s.%s",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName()))
                .requestParams(getRequestParamsByJoinPoint(joinPoint))
                .exception(e)
                .build();

        log.error("Request Error: {}", objectMapper.writeValueAsString(requestLogError) );
    }

    private Map<String,Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint){
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        return buildRequestParam(paramNames,paramValues);
    }
    private Map<String,Object> getRequestParamsByJoinPoint(JoinPoint joinPoint){
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();
        return buildRequestParam(paramNames,paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues){
        Map<String,Object> requestParams = new HashMap<>();
        for(int i = 0; i < paramNames.length; i++){
            Object paramValue = paramValues[i];
            //如果是文件对象,获取文件名
            if(paramValue instanceof MultipartFile){
                MultipartFile file = (MultipartFile) paramValue;
                paramValue = file.getOriginalFilename();
            }
            requestParams.put(paramNames[i],paramValue);
        }
        return requestParams;
    }
}
