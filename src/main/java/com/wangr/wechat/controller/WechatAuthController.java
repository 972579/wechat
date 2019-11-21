package com.wangr.wechat.controller;

import com.wangr.wechat.responsedata.BaseResponse;
import com.wangr.wechat.util.aes.WXBizMsgCrypt;
import com.wangr.wechat.util.constant.WechatConfigConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/11/21 9:14
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/")
public class WechatAuthController {

    /**
     * 服务器校验
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


}
