package com.zdefys.handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author: zdefys
 * @date: 2020/5/12 20:45
 * @version: v1.0
 * @description:
 */
public class JsoupHandler {
    public static String htmlStr = "<html><head></head><body>" +
            "<p>hello html</p></body></html>";

    public static String urlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline";

    public static void main(String[] args) {
//        Document document = Jsoup.parse(htmlStr);
        // 通过标签名找到元素
//        Elements element = document.getElementsByTag("p");
//        System.out.println(element);
        // 通过id找到元素
//        document.getElementById()
        // 通过正则表达式找到元素
//        Elements element = document.select("a[href]);
    }
}
