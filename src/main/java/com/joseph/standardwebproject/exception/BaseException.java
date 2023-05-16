package com.joseph.standardwebproject.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException{
    /**
     * 异常码
     */
    private int code;
    /**
     * 异常信息
     */
    private String message;
    /**
     * 响应枚举
     */
    private IResponseEnum responseEnum;
    /**
     * 参数
     */
    private Object[] args;

    public BaseException(IResponseEnum responseEnum, Object[] args, String message){
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause){
        super(message,cause);
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }
}
