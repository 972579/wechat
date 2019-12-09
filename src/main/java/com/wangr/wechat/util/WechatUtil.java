package com.wangr.wechat.util;

import com.wangr.wechat.util.constant.WechatMsgTypeConstant;
import com.wangr.wechat.vo.BaseWechatMsg;
import com.wangr.wechat.vo.WechatMsgVo;
import com.wangr.wechat.vo.msg.image.Image;
import com.wangr.wechat.vo.msg.image.ImageMessage;
import com.wangr.wechat.vo.msg.text.TextMessage;

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
                // 音频需要提前定义在素材库，无法直接返回音频
                msg = new TextMessage();
                ((TextMessage) msg).setContent("暂不支持返回给你一样的音频，缓缓哈~~~");
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
                break;
            case WechatMsgTypeConstant.VIDEO:
                // 视频需要提前定义在素材库，无法直接返回视频
                msg = new TextMessage();
                ((TextMessage) msg).setContent("暂不支持返回给你一样的视频，缓缓哈~~~");
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
                break;
            case WechatMsgTypeConstant.SHORTVIDEO:
                // 小视频需要提前定义在素材库，无法直接返回小视频
                msg = new TextMessage();
                ((TextMessage) msg).setContent("暂不支持返回给你一样的小视频，缓缓哈~~~");
                msg.setMsgType(WechatMsgTypeConstant.TEXT);
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
