package com.wangr.wechat.util;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wangr.wechat.util.constant.WechatConfigConstant;
import com.wangr.wechat.vo.WechatMsgVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019/12/3 14:50
 * @since JDK 1.8
 */
public class XmlUtils {


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
    @Deprecated
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
     * @param obj bean对象 [由于无法将List转换成xml，废弃]
     * @return 转换后的map
     * @throws IllegalAccessException 转换异常
     */
    @Deprecated
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Map<String, Object> map = new HashMap<>(clazz.getDeclaredFields().length);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (ObjectUtil.isNotEmpty(value)) {
                // 内部类变量
                if (value.toString().contains("=")) {
                    String str = value.toString();
                    value = str.substring(str.indexOf("=") + 1, str.length() - 1);
                }
                String buffer = "<![CDATA[" +
                        value +
                        "]]>";
                map.put(fieldName, buffer);
            }
        }
        return map;
    }

    /**
     * 重写hutool中的json转xml方法，封装微信xml消息
     *
     * @param object
     * @return
     */
    public static String toWechatXml(Object object) {
        return toXml(JSONUtil.parseObj(object), "xml");
    }


    /**
     * 转换JSONObject为XML
     * Convert a JSONObject into a well-formed, element-normal XML string.
     *
     * @param object  A JSONObject.
     * @param tagName The optional name of the enclosing tag.
     * @return A string.
     * @throws JSONException Thrown if there is an error parsing the string
     */
    private static String toXml(Object object, String tagName) throws JSONException {
        if (null == object) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        JSONArray ja;
        JSONObject jo;
        String key;
        Iterator<String> keys;
        String string;
        Object value;

        if (object instanceof JSONObject) {

            // Emit <tagName>
            if (tagName != null) {
                sb.append('<');
                sb.append(tagName);
                sb.append('>');
            }

            // Loop thru the keys.
            jo = (JSONObject) object;
            keys = jo.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                value = jo.get(key);
                if (value == null) {
                    value = "";
                } else if (value.getClass().isArray()) {
                    value = new JSONArray(value);
                }
                string = value instanceof String ? (String) value : null;

                // Emit content in body
                if ("content".equals(key)) {
                    if (value instanceof JSONArray) {
                        ja = (JSONArray) value;
                        int i = 0;
                        for (Object val : ja) {
                            if (i > 0) {
                                sb.append('\n');
                            }
                            sb.append(parseCDATA(val.toString()));
                            i++;
                        }
                    } else {
                        sb.append(parseCDATA(value.toString()));
                    }

                    // Emit an array of similar keys

                } else if (value instanceof JSONArray) {
                    ja = (JSONArray) value;
                    for (Object val : ja) {
                        if (val instanceof JSONArray) {
                            sb.append('<');
                            sb.append(key);
                            sb.append('>');
                            sb.append(toXml(val, null));
                            sb.append("</");
                            sb.append(key);
                            sb.append('>');
                        } else {
                            sb.append(toXml(val, key));
                        }
                    }
                } else if ("".equals(value)) {
                    sb.append('<');
                    sb.append(key);
                    sb.append("/>");

                    // Emit a new tag <k>

                } else {
                    sb.append(toXml(value, key));
                }
            }
            if (tagName != null) {

                // Emit the </tagname> close tag
                sb.append("</");
                sb.append(tagName);
                sb.append('>');
            }
            return sb.toString();

        }

        if (object != null) {
            if (object.getClass().isArray()) {
                object = new JSONArray(object);
            }

            if (object instanceof JSONArray) {
                ja = (JSONArray) object;
                for (Object val : ja) {
                    // XML does not have good support for arrays. If an array
                    // appears in a place where XML is lacking, synthesize an
                    // <array> element.
                    sb.append(toXml(val, tagName == null ? "array" : tagName));
                }
                return sb.toString();
            }
        }

        string = (object == null) ? "null" : parseCDATA(object.toString());
        return (tagName == null) ? "\"" + string + "\"" : (string.length() == 0) ? "<" + tagName + "/>" : "<" + tagName + ">" + string + "</" + tagName + ">";
    }

    /**
     * 将值封装到CDATA中
     *
     * @param str 字符串
     * @return
     */
    private static String parseCDATA(String str) {
        return "<![CDATA[" +
                str +
                "]]>";
    }
}
