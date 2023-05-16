package com.joseph.standardwebproject.exception.assertion;

import com.joseph.standardwebproject.exception.BaseException;

/**
 * 断言接口
 * 断言方法是使用接口的默认方法定义的，断言失败后，抛出的异常不是某个具体的异常，而是交给了newException()接口方法
 * 具体的异常让Assert的实现类去决定
 */
public interface Assert {
    /**
     * 创建异常
     */
    BaseException newException(Object... args);
    BaseException newException(Throwable t, Object... args);

    default void assertNotNull(Object obj){
        if(obj == null){
            throw newException();
        }
    }

    default void assertNotNull(Object obj, Object... args){
        if(obj == null){
            throw newException(args);
        }
    }

    default void assertNotTrue(boolean bool){
        if(bool){
            throw newException();
        }
    }
}
