package com.joseph.standardwebproject.exception.handler;

import com.joseph.standardwebproject.common.response.ErrorResponse;
import com.joseph.standardwebproject.exception.BaseException;
import com.joseph.standardwebproject.exception.BusinessException;
import com.joseph.standardwebproject.exception.BusinessResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@Component
@ControllerAdvice
@ConditionalOnWebApplication
//@ConditionalOnMissingBean(CommonExceptionHandler.class)
public class CommonExceptionHandler {
    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";
    /**
     * 当前环境，从配置文件中取得
     */
    @Value("${spring.profiles.active}")
    private String profile;



    /**
     * 处理进入Controller之后的【自定义业务异常】
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorResponse handleBusinessException(BaseException e){
        log.error(e.getMessage(),e);
        return new ErrorResponse(e.getResponseEnum().getCode(),e.getResponseEnum().getMessage());
    }

    /**
     * 处理进入Controller之前的Servlet的相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,//404异常,同时需要配置application.properties
            HttpRequestMethodNotSupportedException.class,//GET，POST混用
            HttpMediaTypeNotSupportedException.class,//content-type请求头的值与要求不符
            HttpMediaTypeNotAcceptableException.class,//content-type请求头的值未知
            MissingPathVariableException.class,//未检测到路径参数
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            TypeMismatchException.class,//参数类型匹配失败
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public ErrorResponse handleServletException(Exception e){
        log.error(e.getMessage(),e);
        if(ENV_PROD.equals(profile)){
            return new ErrorResponse(BusinessResponseEnum.SERVER_ERROR.getCode(), BusinessResponseEnum.SERVER_ERROR.getMessage());
        }
        return new ErrorResponse(BusinessResponseEnum.NETWORK_ERROR.getCode(), BusinessResponseEnum.NETWORK_ERROR.getMessage());
    }

    /**
     * 处理进入Controller之前的参数绑定异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ErrorResponse handleBindException(BindException e){
        log.error(e.getMessage(),e);
        return wrapBindingResult(e.getBindingResult());
    }

    /**
     * 处理进入Controller之前的参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleValidException(MethodArgumentNotValidException e){
        log.error(e.getMessage(),e);
        return wrapBindingResult(e.getBindingResult());
    }

    private ErrorResponse wrapBindingResult(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        for(ObjectError error : bindingResult.getAllErrors()){
            sb.append(", ");
            if(error instanceof FieldError){
                sb.append( ((FieldError)error).getField() ).append(": ");
            }
            sb.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return new ErrorResponse(BusinessResponseEnum.VALID_ERROR.getCode(), sb.substring(2));
    }

    /**
     * 处理所有未知的异常，比如操作数据库失败的异常。
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResponse handleException(Exception e){
        log.error(e.getMessage(),e);
        if(ENV_PROD.equals(profile)){
            return new ErrorResponse(BusinessResponseEnum.SERVER_ERROR.getCode(), BusinessResponseEnum.SERVER_ERROR.getMessage());
        }
        return new ErrorResponse(BusinessResponseEnum.NETWORK_ERROR.getCode(), BusinessResponseEnum.NETWORK_ERROR.getMessage());
    }
}
