package com.joseph.standardwebproject.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse{
    /**
     * 数据列表
     */
    protected T data;

    public CommonResponse(){
        super();
    }

    public CommonResponse(int code, T data){
        super();
        this.code = code;
        this.data = data;
    }

    public CommonResponse(T data){
        super();
        this.data = data;
    }

    public CommonResponse(int code, String message, T data){
        super(code,message);
        this.data = data;
    }
}
