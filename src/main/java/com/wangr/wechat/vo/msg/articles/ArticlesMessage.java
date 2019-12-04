package com.wangr.wechat.vo.msg.articles;

import com.wangr.wechat.vo.BaseWechatMsg;
import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:34
 * @since JDK 1.8
 */
@Data
public class ArticlesMessage extends BaseWechatMsg {
    /**
     * 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，
     * 开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     */
    private Integer ArticleCount;
    /**
     * 图文消息
     */
    private Articles Articles;
}
