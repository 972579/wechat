package com.wangr.wechat.responsedata;

/**
 * 带分页的数据
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-16 18:17
 * @since JDK 1.8
 */
public class PageDatasResponse<T> extends DatasResponse<T>{
    private PageResponse page;

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }
}
