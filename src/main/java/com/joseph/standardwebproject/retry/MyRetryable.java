package com.joseph.standardwebproject.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRetryable {
    //最大重试次数
    int retryTimes() default 3;
    //重试间隔,单位秒
    int retryInterval() default 1;
}
