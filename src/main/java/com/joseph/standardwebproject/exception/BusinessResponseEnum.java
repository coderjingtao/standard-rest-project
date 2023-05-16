package com.joseph.standardwebproject.exception;

import com.joseph.standardwebproject.exception.assertion.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常枚举类
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {
    VALID_ERROR(300,"validation error"),
    SERVER_ERROR(500,"server error"),
    NETWORK_ERROR(400,"Network error, please try it later."),
    USER_NOT_FOUND(6001,"User not found"),
    BAD_LICENSE_TYPE(7001,"Bad license type"),
    LICENSE_NOT_FOUND(7002,"License not found"),
    ;

    private int code;
    private String message;
}
