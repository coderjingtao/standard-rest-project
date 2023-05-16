package com.joseph.standardwebproject.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDataResponse<T> extends CommonResponse<PageResponse<T>> {
    public QueryDataResponse(){

    }

    public QueryDataResponse(PageResponse<T> data){
        super(data);
    }
}
