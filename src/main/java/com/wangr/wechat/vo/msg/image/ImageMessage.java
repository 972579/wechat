package com.wangr.wechat.vo.msg.image;

import com.wangr.wechat.vo.BaseWechatMsg;
import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 13:46
 * @since JDK 1.8
 */
@Data
public class ImageMessage extends BaseWechatMsg {

    /**
     * 图片信息
     */
    private Image Image;

}
