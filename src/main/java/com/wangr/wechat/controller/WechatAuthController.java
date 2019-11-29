package com.wangr.wechat.controller;

import com.wangr.wechat.util.aes.WXBizMsgCrypt;
import com.wangr.wechat.util.constant.WechatConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/11/21 9:14
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/")
@Slf4j
public class WechatAuthController {

    /**
     * 服务器校验
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping
    public String auth(String signature, String timestamp, String nonce, String echostr) {
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(WechatConfigConstant.TOKEN,
                    WechatConfigConstant.ENCODING_AESKEY, WechatConfigConstant.APP_ID);
            wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce);
        } catch (Exception e) {
            return "erroe";
        }
        return echostr;
    }


    @PostMapping
    public String wechatMsg(String msg_signature, String timestamp, String nonce,@RequestBody String postData) {
        log.info("收到消息,{}", postData);
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(WechatConfigConstant.TOKEN,
                    WechatConfigConstant.ENCODING_AESKEY, WechatConfigConstant.APP_ID);
            String decryptMsg = wxBizMsgCrypt.decryptMsg(msg_signature, timestamp, nonce, postData);
            log.info("收到消息解密后:" + decryptMsg);
            return wxBizMsgCrypt.encryptMsg("哈哈哈哈，返回给微信的消息",timestamp,nonce);
        } catch (Exception e) {
            e.printStackTrace();
            return "erroe";
        }
    }

}
