package com.wangr.wechat.responsedata;

/**
 * 分页数据(接口规范)
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-17 11:22
 * @since JDK 1.8
 */
public class PageResponse{
    /**
     * 总记录数
     */
    private long total;
    /**
     * 当前页数
     */
    private long currPage;
    /**
     * 每页记录数
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long totalPage;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(long currPage) {
        this.currPage = currPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public PageResponse() {
    }
    public PageResponse(long total, long currPage, long pageSize, long totalPage) {
        this.total = total;
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
    }
}
