package com.joseph.standardwebproject.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> extends BaseResponse{

    private T data;

    PageResponse(int code, String message, T data){
        super(code,message);
        this.data = data;
    }
}
