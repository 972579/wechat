package com.wangr.wechat.vo.msg.articles;

import lombok.Data;

import java.util.List;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:35
 * @since JDK 1.8
 */
@Data
public class Articles {
    /**
     * 图文消息
     */
    private List<Item> item;

}
