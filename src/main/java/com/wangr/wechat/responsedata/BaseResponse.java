package com.wangr.wechat.responsedata;

import java.io.Serializable;

/**
 * 数据接口规范公共字段返回封装
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-16 17:56
 * @since JDK 1.8
 */
public class BaseResponse implements Serializable {
    private long responseTime = System.currentTimeMillis();
    private String message = "success";
    private int code;

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
