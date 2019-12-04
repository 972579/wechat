package com.wangr.wechat.vo.msg.music;

import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:32
 * @since JDK 1.8
 */
@Data
public class Music {
    /**
     * 音乐标题
     */
    private String Title;
    /**
     * 音乐描述
     */
    private String Description;
    /**
     * 音乐链接
     */
    private String MusicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId;


}
