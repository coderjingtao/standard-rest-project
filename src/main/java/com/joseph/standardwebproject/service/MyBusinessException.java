package com.joseph.standardwebproject.service;

public class MyBusinessException extends RuntimeException{
    /**
     * 异常信息
     */
    private String message;
    /**
     * 响应枚举
     */
    private ExceptionEnum exceptionEnum;

    public MyBusinessException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;
        this.message = exceptionEnum.getMessage();
    }
}
