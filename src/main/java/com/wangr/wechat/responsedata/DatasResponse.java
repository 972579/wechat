package com.wangr.wechat.responsedata;

import lombok.Data;

import java.util.List;

/**
 * 多数据
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-16 18:13
 * @since JDK 1.8
 */
@Data
public class DatasResponse<T> extends BaseResponse {
    private List<T> datas;
}
