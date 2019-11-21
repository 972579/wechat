package com.wangr.wechat.responsedata;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author: wenhai
 * @date:2019-07-19 16:57
 * @since JDK 1.8
 */
@Data
public class PageData implements Serializable {
    /**
     * 总条数
     */
    private int totalCount = 0;
    /**
     * 每页条数
     */
    private int pageSize = 10;
    /**
     * 总页数
     */
    private int totalPage = 0;
    /**
     * 当前页
     */
    private int currPage = 1;

    /**
     * 列表数据集合
     */
    private List rows = new ArrayList();

    public PageData() {
    }

    public PageData(Page resultData) {
        this.totalCount = (int) resultData.getTotalElements();
        this.pageSize = resultData.getSize();
        this.totalPage = resultData.getTotalPages();
        this.currPage = resultData.getNumber() + 1;
        this.rows = resultData.getContent();
    }
}
