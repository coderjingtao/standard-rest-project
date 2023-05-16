package com.joseph.standardwebproject.common.response;

public class ErrorResponse extends BaseResponse{

    public ErrorResponse(){
    }

    public ErrorResponse(int code, String message){
        super(code,message);
    }
}
