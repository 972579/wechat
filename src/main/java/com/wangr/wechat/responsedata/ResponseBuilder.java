package com.wangr.wechat.responsedata;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 对response返回进行构造
 *
 * @author TangGuangMing
 * @version 4.0
 * @date 2019-05-16 17:22
 * @since JDK 1.8
 */

public class ResponseBuilder {
    /**
     * 返回成功【使用接口规范默认的message和code】
     *
     * @return 成功数据
     */
    public static BaseResponse builderSuccess() {
        return new BaseResponse();
    }

    /**
     * 返回成功【使用自定义的message和默认code】
     *
     * @param message 自定义信息
     * @return 成功数据
     */
    public static BaseResponse builderSuccess(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        return baseResponse;
    }

    /**
     * 返回成功【使用自定义信息和自定义code】
     *
     * @param message 自定义信息
     * @param code    自定义编码
     * @return 成功数据
     */
    public static BaseResponse builderSuccess(String message, int code) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        baseResponse.setCode(code);
        return baseResponse;
    }

    /**
     * 返回失败【使用接口规范的默认message和code】
     *
     * @return 失败数据
     */
    public static BaseResponse builderFailed() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("error");
        baseResponse.setCode(1);
        return baseResponse;
    }



    /**
     * 返回失败【使用自定义message和默认的code】
     *
     * @param message 自定义信息
     * @return 失败数据
     */
    public static BaseResponse builderFailed(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        baseResponse.setCode(1);
        return baseResponse;
    }

    /**
     * 返回失败【使用自定义message和code】
     *
     * @param message 自定义信息
     * @param code    自定义编码
     * @return 失败数据
     */
    public static BaseResponse builderFailed(String message, int code) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        baseResponse.setCode(code);
        return baseResponse;
    }

    /**
     * 返回成功数据【使用规范的message和code，数据如果是 List类型，则返回中包含 datas ，其它则包含 data】
     *
     * @param obj 多类型数据体
     * @return 成功数据
     */
    public static BaseResponse dataBuilderSuccess(Object obj) {
        BaseResponse baseResponse;
        if (obj instanceof List) {
            DatasResponse datasResponse = new DatasResponse();
            datasResponse.setDatas((List<Object>) obj);
            baseResponse = datasResponse;
        } else {
            DataResponse dataResponse = new DataResponse<>();
            dataResponse.setData(obj);
            baseResponse = dataResponse;
        }
        return baseResponse;
    }

    /**
     * mybatis-plus
     * 返回成功数据【使用接口规范的message和code，返回数据中同时包含接口规范 datas 和 page】
     *
     * @param page
     * @return
     */
    public static BaseResponse pageBuilderSuccess(IPage page) {
        PageDatasResponse pageDatasResponse = new PageDatasResponse();
        pageDatasResponse.setDatas(page.getRecords());
        pageDatasResponse.setPage(new PageResponse(page.getTotal(), (int) page.getCurrent(), (int) page.getSize(),
                page.getPages()));
        BaseResponse baseResponse = pageDatasResponse;
        return baseResponse;
    }


    /**
     * elasticsearch
     * 返回成功数据【使用接口规范的message和code，返回数据中同时包含接口规范 datas 和 page】
     *
     * @param page
     * @return
     */
    public static BaseResponse pageBuilderSuccess(Page page) {
        PageDatasResponse pageDatasResponse = new PageDatasResponse();
        pageDatasResponse.setDatas(page.getContent());
        pageDatasResponse.setPage(new PageResponse(page.getTotalElements(), page.getNumber() + 1, page.getSize(),
                page.getTotalPages()));
        BaseResponse baseResponse = pageDatasResponse;
        return baseResponse;
    }

    /**
     * 自定义
     * 返回成功数据【使用接口规范的message和code，返回数据中同时包含接口规范 datas 和 page】
     *
     * @param pageData
     * @return
     */
    public static BaseResponse pageBuilderSuccess(PageData pageData) {
        PageDatasResponse pageDatasResponse = new PageDatasResponse();
        pageDatasResponse.setDatas(pageData.getRows());
        pageDatasResponse.setPage(new PageResponse(pageData.getTotalCount(), pageData.getCurrPage(), pageData.getPageSize(),
                pageData.getTotalPage()));
        BaseResponse baseResponse = pageDatasResponse;
        return baseResponse;
    }


    /**
     * pagehelper
     * 返回成功数据【使用接口规范的message和code，返回数据中同时包含接口规范 datas 和 page】
     *
     * @param page
     * @return
     */
    public static BaseResponse pageBuilderSuccess(PageInfo page) {
        PageDatasResponse pageDatasResponse = new PageDatasResponse();
        pageDatasResponse.setDatas(page.getList());
        pageDatasResponse.setPage(new PageResponse(page.getTotal(), page.getPageNum(), page.getPageSize(), page.getPages()));
        BaseResponse baseResponse = pageDatasResponse;
        return baseResponse;
    }
}
