package com.wangr.wechat.responsedata;

import lombok.Data;

/**
 * 单数据
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-16 18:13
 * @since JDK 1.8
 */
@Data
public class DataResponse<T> extends BaseResponse {
    private T data;
}
