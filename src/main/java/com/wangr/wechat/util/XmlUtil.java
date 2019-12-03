package com.wangr.wechat.util;


import cn.hutool.core.util.ObjectUtil;
import com.wangr.wechat.util.constant.WechatConfigConstant;
import com.wangr.wechat.vo.WechatMsgVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/3 14:50
 * @since JDK 1.8
 */
public class XmlUtil {


    /**
     * 解析微信消息
     *
     * @param xmlMsg xml格式消息
     * @return 消息封装实体类
     */
    public static WechatMsgVo parseWechatXmlMsg(String xmlMsg) {
        // 去掉xml 消息中的 CDATA
        xmlMsg = xmlMsg.trim().replaceAll("(<!\\[CDATA\\[)|(\\]\\]>)", "").replace("\n", "");
        WechatMsgVo vo = new WechatMsgVo();
        try {
            Document document = cn.hutool.core.util.XmlUtil.parseXml(xmlMsg);
            NodeList childNodes = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                parseNodeValue(vo, item);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    /**
     * 创建微信xml消息
     *
     * @param openId  openid
     * @param msgType 消息类型
     * @param content 内容
     * @return xml消息字符串
     */
    public static String createWechatXmlMsg(String openId, String msgType, String content) {
        WechatMsgVo vo = new WechatMsgVo();
        vo.setFromUserName(WechatConfigConstant.ORIGINAL_ID);
        vo.setToUserName(openId);
        vo.setMsgType(msgType);
        vo.setContent(content);
        vo.setCreateTime(System.currentTimeMillis() + "");
        try {
            String xml = cn.hutool.core.util.XmlUtil.mapToXmlStr(objectToMap(vo), "xml");
            // 去除自带的xml头部信息以及 将 &lt;和&gt 转换 < >
            // &lt;和&gt 是XmlUtil转成xml时将 <> 自动替换后的结果，这里需要手动再次转换回来
            xml = xml.replaceAll("<\\?.+\\?>", "")
                    .replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">");
            return xml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将xml节点值封装到JAVA对象中
     *
     * @param obj  JAVA对象
     * @param item xml节点
     * @throws IllegalAccessException 异常信息
     */
    private static void parseNodeValue(Object obj, Node item) throws IllegalAccessException {
        String name = item.getNodeName();
        String value = item.getTextContent();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(name)) {
                field.setAccessible(true);
                field.set(obj, value);
                break;
            }
        }
    }


    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj bean对象
     * @return 转换后的map
     * @throws IllegalAccessException 转换异常
     */
    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Map<String, Object> map = new HashMap<>(clazz.getDeclaredFields().length);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (ObjectUtil.isNotEmpty(value)) {
                String buffer = "<![CDATA[" +
                        value +
                        "]]>";
                map.put(fieldName, buffer);
            }
        }
        return map;
    }
}
