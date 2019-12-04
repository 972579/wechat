package com.wangr.wechat.vo;

import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/3 17:32
 * @since JDK 1.8
 */
@Data
public class BaseWechatMsg {
    /**
     * 接收方
     */
    private String ToUserName;
    /**
     * 发送方
     */
    private String FromUserName;
    /**
     * 时间戳
     */
    private String CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;
}
