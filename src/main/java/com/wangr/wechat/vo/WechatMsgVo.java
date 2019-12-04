package com.wangr.wechat.vo;

import lombok.Data;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/3 15:00
 * @since JDK 1.8
 */
@Data
public class WechatMsgVo extends BaseWechatMsg {
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
    /**
     * 内容
     */
    private String Content;
    /**
     * 消息id
     */
    private String MsgId;
    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;

    @Override
    public String toString() {
        return "WechatMsgVo{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", Content='" + Content + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", Format='" + Format + '\'' +
                ", Recognition='" + Recognition + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                ", Location_X='" + Location_X + '\'' +
                ", Location_Y='" + Location_Y + '\'' +
                ", Scale='" + Scale + '\'' +
                ", Label='" + Label + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
