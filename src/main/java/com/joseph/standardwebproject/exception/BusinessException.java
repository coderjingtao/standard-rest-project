package com.joseph.standardwebproject.exception;

public class BusinessException extends BaseException{
    /**
     * 构造方法
     */
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message){
        super(responseEnum,args,message);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause){
        super(responseEnum,args,message,cause);
    }
}
