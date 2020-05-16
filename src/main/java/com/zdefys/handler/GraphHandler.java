package com.zdefys.handler;

import com.google.gson.Gson;
import com.zdefys.bean.GraphAddBean;
import com.zdefys.bean.GraphBean;
import com.zdefys.bean.GraphColumnarBean;
import com.zdefys.bean.GraphPieBean;
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


    /**
     * 一条折线图
     *
     * @return
     */
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

    /**
     * 两条折线图
     *
     * @return
     */
    public static List<GraphAddBean> getGraphAddData() {
        List<GraphAddBean> result = new ArrayList<>(121);

        String str = HttpClientUtil.doGet(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayAddList");
        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);
            String date = (String) tmp.get("date");
            double addConfirm = (double) tmp.get("confirm");
            double addSuspect = (double) tmp.get("suspect");
            GraphAddBean graphAddBean = new GraphAddBean(date, (int) addConfirm, (int) addSuspect);
            result.add(graphAddBean);
        }
        return result;

    }

    public static String urlStrAll = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    /**
     * 柱状图
     *
     * @return
     */
    public static List<GraphColumnarBean> getGraphColumnarData() {
        List<GraphColumnarBean> result = new ArrayList<>(26);
        String respJson = HttpClientUtil.doGet(urlStrAll);
        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);
        ArrayList areaList = (ArrayList) subMap.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");
            ArrayList children = (ArrayList) tmp.get("children");
            for (int j = 0; j < children.size(); j++) {
                Map subTmp = (Map) children.get(j);
                if ("境外输入".equals((String) subTmp.get("name"))) {
                    Map total = (Map) subTmp.get("total");
                    // 境外输入的数据
                    double fromAbroad = (double) total.get("confirm");

                    GraphColumnarBean bean = new GraphColumnarBean(name, (int) fromAbroad);
                    result.add(bean);
                }
            }
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @return
     */
    public static List<GraphPieBean> getGraphPieData() {
        List<GraphPieBean> result = new ArrayList<>(3);

        String str = HttpClientUtil.doGet(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        Map dataMap = (Map) subMap.get("nowConfirmStatis");
        int total = 0;
        for (Object o : dataMap.keySet()) {
            String name = (String) o;
            switch (name) {
                case "gat":
                    name = "港澳台病例";
                    break;
                case "import":
                    name = "境外输入病例";
                    break;
                case "province":
                    name = "31省本土病例";
                    break;
            }
            double value = (double) dataMap.get(o);
            name += ":" + (int) value + "例";
            total+= value;
            GraphPieBean bean = new GraphPieBean(name, (int) value);
            result.add(bean);
        }
        return result;
    }

    public static void main(String[] args) {
        List<GraphBean> graphData = getGraphData();
        System.out.println(graphData);
    }
}
