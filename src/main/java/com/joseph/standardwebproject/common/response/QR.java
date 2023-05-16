package com.joseph.standardwebproject.common.response;

public class QR<T> extends QueryDataResponse<T> {

    public QR(){
        super();
    }
    public QR(PageResponse<T> data){
        super(data);
    }
}
