package com.joseph.standardwebproject.service;

public interface MyAssert extends ExceptionEnum{

    default void assertNotNull(Object obj){
        if(obj == null){
            throw new MyBusinessException(this);
        }
    }
}
