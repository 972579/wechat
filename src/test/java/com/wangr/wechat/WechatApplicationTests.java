package com.wangr.wechat;

import cn.hutool.json.JSONUtil;
import com.wangr.wechat.util.XmlUtils;
import com.wangr.wechat.vo.msg.articles.Articles;
import com.wangr.wechat.vo.msg.articles.ArticlesMessage;
import com.wangr.wechat.vo.msg.articles.Item;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;

@SpringBootTest
class WechatApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 测试 转换xml的方法
     *
     * @param args
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {
        Item item = new Item();
        item.setDescription("aaa");
        Articles articles = new Articles();
        articles.setItem(Arrays.asList(item, item));
        ArticlesMessage message = new ArticlesMessage();
        message.setArticles(articles);
        Map<String, Object> map = XmlUtils.objectToMap(message);
        System.out.println(map);
        String s = XmlUtils.toWechatXml(message);
        System.out.println(s);
    }

}
