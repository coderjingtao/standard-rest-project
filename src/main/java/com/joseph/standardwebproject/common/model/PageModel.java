package com.joseph.standardwebproject.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageModel<T> {
    /**
     * 当前页数
     */
    private Integer pageNo;
    /**
     * 当前页的大小
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 数据
     */
    private List<T> data;
}
