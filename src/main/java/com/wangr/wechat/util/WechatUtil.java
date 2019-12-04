package com.wangr.wechat.util;

import com.wangr.wechat.util.constant.WechatMsgTypeConstant;
import com.wangr.wechat.vo.BaseWechatMsg;
import com.wangr.wechat.vo.WechatMsgVo;
import com.wangr.wechat.vo.msg.image.Image;
import com.wangr.wechat.vo.msg.image.ImageMessage;
import com.wangr.wechat.vo.msg.text.TextMessage;
import com.wangr.wechat.vo.msg.video.Video;
import com.wangr.wechat.vo.msg.video.VideoMessage;
import com.wangr.wechat.vo.msg.voice.Voice;
import com.wangr.wechat.vo.msg.voice.VoiceMessage;

import java.time.LocalDateTime;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/4 19:59
 * @since JDK 1.8
 */
public class WechatUtil {

    /**
     * 复制收到的微信消息，并转换为xml格式的字符串
     *
     * @param vo
     * @return
     */
    public static String copyMsg(WechatMsgVo vo) {
        return XmlUtils.toWechatXml(createCopyMsg(vo));
    }

    /**
     * 创建相同的返回消息
     *
     * @param vo
     * @return
     */
    private static BaseWechatMsg createCopyMsg(WechatMsgVo vo) {
        BaseWechatMsg msg;
        switch (vo.getMsgType()) {
            case WechatMsgTypeConstant.TEXT:
                msg = new TextMessage();
                ((TextMessage) msg).setContent(vo.getContent());
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
                break;
            case WechatMsgTypeConstant.IMAGE:
                msg = new ImageMessage();
                Image image = new Image();
                image.setMediaId(vo.getMediaId());
                ((ImageMessage) msg).setImage(image);
                msg.setMsgType(WechatMsgTypeConstant.IMAGE);
                break;
            case WechatMsgTypeConstant.VOICE:
                msg = new VoiceMessage();
                Voice voice = new Voice();
                voice.setMediaId(vo.getMediaId());
                ((VoiceMessage) msg).setVoice(voice);
                msg.setMsgType(WechatMsgTypeConstant.VOICE);
                break;
            case WechatMsgTypeConstant.VIDEO:
                msg = new VideoMessage();
                Video video = new Video();
                video.setMediaId(vo.getMediaId());
                video.setTitle("返回给你一个视频消息");
                video.setDescription("这不是一样的嘛,有啥好看的");
                ((VideoMessage) msg).setVideo(video);
                msg.setMsgType(WechatMsgTypeConstant.VIDEO);
                break;
            case WechatMsgTypeConstant.SHORTVIDEO:
                msg = new VideoMessage();
                Video shortVideo = new Video();
                shortVideo.setMediaId(vo.getMediaId());
                shortVideo.setTitle("返回给你一个小视频消息");
                shortVideo.setDescription("这不是一样的嘛,有啥好看的");
                ((VideoMessage) msg).setVideo(shortVideo);
                msg.setMsgType(WechatMsgTypeConstant.VIDEO);
                break;
            case WechatMsgTypeConstant.LOCATION:
                msg = new TextMessage();
                ((TextMessage) msg).setContent("你在:" + vo.getLabel() + ",经纬度:" + vo.getLocation_X() + ";" + vo.getLocation_Y()
                        + ",你说我说的对不对");
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
                break;
            case WechatMsgTypeConstant.LINK:
                msg = new TextMessage();
                ((TextMessage) msg).setContent("我猜你想访问:" + vo.getTitle() + ",这个地址是干什么的呢:" + vo.getDescription()
                        + ",地址就是这个:" + vo.getUrl());
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
                break;
            default:
                msg = new BaseWechatMsg();
        }
        msg.setCreateTime(System.currentTimeMillis() + "");
        msg.setFromUserName(vo.getToUserName());
        msg.setToUserName(vo.getFromUserName());

        return msg;
    }

}
