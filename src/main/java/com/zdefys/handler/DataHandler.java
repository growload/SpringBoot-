package com.zdefys.handler;

import com.google.gson.Gson;
import com.zdefys.bean.DataBean;
import com.zdefys.service.DataService;
import com.zdefys.util.HttpURLConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangdeen
 * @date: 2020/5/9 8:56
 * @version: v1.0
 * @descrption:
 */
@Component
public class DataHandler {

    @Autowired
    private DataService dataService;
    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    @PostConstruct
    public void saveData(){
        try {
            List<DataBean> dataBeans = getData();
            // 先将数据清空
            dataService.remove(null);
            dataService.saveBatch(dataBeans);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 配置定时执行的注解 支持cron表达式
    @Scheduled(cron = "0 0 0 1-2 * ?")
    public void updateData(){
        saveData();
    }

    public static List<DataBean> getData(){
        // 实时数据
        String respJson = HttpURLConnectionUtil.doGet(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);
        // 此时增加了一层处理，data对应的数据类型为String
        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList arrayList = (ArrayList) subMap.get("areaTree");
        Map dataMap = (Map) arrayList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>(34);
        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            // 区域名字
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            // 现有确认
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            // 累计确诊
            double confirm = (Double) totalMap.get("confirm");
            // 治愈人数
            double heal = (Double) totalMap.get("heal");
            // 死亡人数
            double dead = (Double) totalMap.get("dead");

            DataBean dataBean = new DataBean(name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
            result.add(dataBean);
        }

        return result;
    }
}
