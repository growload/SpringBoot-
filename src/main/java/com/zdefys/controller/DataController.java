package com.zdefys.controller;

import com.google.gson.Gson;
import com.zdefys.bean.DataBean;
import com.zdefys.bean.GraphBean;
import com.zdefys.handler.GraphHandler;
import com.zdefys.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zdefys
 * @date: 2020/5/11 21:01
 * @version: v1.0
 * @description:
 */
@Controller
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String list(Model model){
        List<DataBean> list = dataService.list();
        model.addAttribute("dataList", list);
        return "list";
    }

   /* @GetMapping("/list/{id}")
    public String listById(Model model,@PathVariable String id){
        List<DataBean> list = dataService.listById(Integer.parseInt(id));
        model.addAttribute("dataList", list);
        return "list";
    }*/

    @GetMapping("/graph")
    public String graph(Model model){
        List<GraphBean> list = GraphHandler.getGraphData();
        // 进一步改造数据格式
        // 因为前端需要的数据是 x轴所有数据的数据和y轴所有数据的数组
        ArrayList<String> dateList = new ArrayList<>(121);
        ArrayList<Integer> nowConfirmList = new ArrayList<>(121);
        for (int i = 0; i < list.size(); i++) {
            GraphBean graphBean = list.get(i);
            dateList.add(graphBean.getDate());
            nowConfirmList.add(graphBean.getNowConfirm());
        }
        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("nowConfirmList", new Gson().toJson(nowConfirmList));
        return "graph";
    }

}
