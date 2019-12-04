package com.wangr.wechat.vo.msg.video;

import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:31
 * @since JDK 1.8
 */
@Data
public class Video {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;
    /**
     * 视频消息的标题
     */
    private String Title;
    /**
     * 视频消息的描述
     */
    private String Description;

}
