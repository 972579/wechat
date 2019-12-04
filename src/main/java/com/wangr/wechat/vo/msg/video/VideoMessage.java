package com.wangr.wechat.vo.msg.video;

import com.wangr.wechat.vo.BaseWechatMsg;
import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:30
 * @since JDK 1.8
 */
@Data
public class VideoMessage extends BaseWechatMsg {
    /**
     * 音频信息
     */
    private Video Video;
}
