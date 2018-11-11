package com.ejyi.demo.springboot.server.model.result;


import java.io.Serializable;
import java.util.List;

public class ReturnPageResult<T> implements Serializable {

    private Long total;
    private Long pageNum;
    private Long pageSize;

    private List<T> data;

    public ReturnPageResult(){ }

    public ReturnPageResult(Long total, Long pageNum, Long pageSize, List<T> data){
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
