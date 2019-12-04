package com.wangr.wechat.vo.msg.text;

import com.wangr.wechat.vo.BaseWechatMsg;
import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 13:45
 * @since JDK 1.8
 */
@Data
public class TextMessage extends BaseWechatMsg {
    /**
     * 文本消息内容
     */
    private String Content;

}
