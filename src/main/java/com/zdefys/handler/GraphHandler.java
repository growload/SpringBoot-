package com.zdefys.handler;

import com.google.gson.Gson;
import com.zdefys.bean.GraphBean;
import com.zdefys.util.HttpClientUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zdefys
 * @date: 2020/5/13 21:33
 * @version: v1.0
 * @description:
 */
@Component
public class GraphHandler {

    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";

    public static List<GraphBean> getGraphData() {

        List<GraphBean> result = new ArrayList<>(121);

        String str = HttpClientUtil.doGet(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayList");
        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);
            String date = (String) tmp.get("date");
            double nowConfirm = (double) tmp.get("nowConfirm");
            GraphBean graphBean = new GraphBean(date, (int) nowConfirm);
            result.add(graphBean);
        }
        return result;
    }

    public static void main(String[] args) {
        List<GraphBean> graphData = getGraphData();
        System.out.println(graphData);
    }
}
