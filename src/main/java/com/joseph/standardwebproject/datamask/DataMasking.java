package com.joseph.standardwebproject.datamask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏的字段注解
 * @author joseph
 * @create 2023-11-07
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataMasking {
    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;
}
