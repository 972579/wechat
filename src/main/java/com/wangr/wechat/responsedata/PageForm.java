package com.wangr.wechat.responsedata;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author TangGuangMing
 * @version 1.0.0
 * @date 2019-05-25 17:22
 * @since JDK 1.8
 */
public class PageForm<T> extends Page<T> {
    private int currPage = 1;
    private int pageSize = 10;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        // 这里由于分页属性不同，这里对其进行设置值
        this.setCurrent(currPage);
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        // 这里由于分页属性不同，这里对其进行设置值
        this.setSize(pageSize);
        this.pageSize = pageSize;
    }
}
