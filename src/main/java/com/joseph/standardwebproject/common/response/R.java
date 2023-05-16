package com.joseph.standardwebproject.common.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class R<T> extends CommonResponse<T> {

    public R(){
        super();
    }
    public R(T data){
        super();
        this.data =  data;
    }
    public R(T data, String message){
        super();
        this.data = data;
        this.message = message;
    }
    public R(Throwable e){
        super();
        this.message = e.getMessage();
        this.code = -1;
    }
}
