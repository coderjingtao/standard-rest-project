package com.joseph.standardwebproject.service;

public enum BusinessExceptionEnum implements MyAssert{
    USER_NOT_FOUND("User not found")
    ;
    private String message;

    BusinessExceptionEnum(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
