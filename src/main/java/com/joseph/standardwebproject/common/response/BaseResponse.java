package com.joseph.standardwebproject.common.response;

import lombok.Data;

@Data
public class BaseResponse {
    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String message;
    /**
     * 构造方法
     */
    public BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String message){
        this(ResponseCode.SUCCESS,message);
    }

    public BaseResponse(){
        this(ResponseCode.SUCCESS,"操作成功");
    }
}
