package com.joseph.standardwebproject.aspect;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLogInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
}
