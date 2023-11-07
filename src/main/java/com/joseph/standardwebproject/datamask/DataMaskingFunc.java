package com.joseph.standardwebproject.datamask;

import org.apache.logging.log4j.util.Strings;

import java.util.function.BinaryOperator;

/**
 * 数据脱敏操作枚举类
 * @author joseph
 * @create 2023-11-07
 */
public enum DataMaskingFunc {
    NO_MASK( (content, maskChar) -> content),
    ALL_MASK((content, maskChar) -> {
        if(Strings.isNotBlank(content)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < content.length(); i++){
                sb.append(Strings.isNotBlank(maskChar) ? maskChar : "*");
            }
            return sb.toString();
        }
        return content;
    }),
    ;
    private final BinaryOperator<String> operator;

    DataMaskingFunc(BinaryOperator<String> operator){
        this.operator = operator;
    }

    public BinaryOperator<String> operator(){
        return this.operator;
    }

}
