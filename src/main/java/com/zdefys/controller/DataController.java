package com.zdefys.controller;

import com.google.gson.Gson;
import com.zdefys.bean.*;
import com.zdefys.handler.GraphHandler;
import com.zdefys.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
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
        List<DataBean> dataList = dataService.list();
        model.addAttribute("dataList", dataList);

        List<MapBean> result = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            DataBean dataBean = dataList.get(i);
            MapBean mapBean = new MapBean(dataBean.getArea(), dataBean.getNowConfirm());
            result.add(mapBean);

        }
        model.addAttribute("mapData", new Gson().toJson(result));

        String str = GraphHandler.getData();
        List<GraphBean> list = GraphHandler.getGraphData();
        //  进一步改造数据格式
        //  因为前端需要的数据是  x轴所有数据的数组和y轴所有数据的数组

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> nowConfirmList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GraphBean graphBean = list.get(i);
            dateList.add(graphBean.getDate());
            nowConfirmList.add(graphBean.getNowConfirm());
        }

        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("nowConfirmList", new Gson().toJson(nowConfirmList));


        List<GraphAddBean> addList = GraphHandler.getGraphAddData(str);

        ArrayList<String> addDateList = new ArrayList<>();
        ArrayList<Integer> addConfirmList = new ArrayList<>();
        ArrayList<Integer> addSuspectList = new ArrayList<>();

        for (int i = 0; i < addList.size(); i++) {
            GraphAddBean graphAddBean = addList.get(i);
            addDateList.add(graphAddBean.getDate());
            addConfirmList.add(graphAddBean.getAddConfirm());
            addSuspectList.add(graphAddBean.getAddSuspect());
        }

        model.addAttribute("addDateList", new Gson().toJson(addDateList));
        model.addAttribute("addConfirmList", new Gson().toJson(addConfirmList));
        model.addAttribute("addSuspectList", new Gson().toJson(addSuspectList));


        List<GraphPieBean> pieList = GraphHandler.getGraphPieData(str);
        Collections.sort(pieList);
        model.addAttribute("pieList", new Gson().toJson(pieList));

        List<GraphColumnarBean> columnarList = GraphHandler.getGraphColumnarData();
        Collections.sort(columnarList);

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> fromAbroadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            GraphColumnarBean bean = columnarList.get(i);
            nameList.add(bean.getArea());
            fromAbroadList.add(bean.getFromAbroad());
        }

        model.addAttribute("nameList", new Gson().toJson(nameList));
        model.addAttribute("fromAbroadList", new Gson().toJson(fromAbroadList));
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

    @GetMapping("/graphAdd")
    public String graphAdd(Model model){
        List<GraphAddBean> list = GraphHandler.getGraphAddData();
        // 进一步改造数据格式
        // 因为前端需要的数据是 x轴所有数据的数据和y轴所有数据的数组
        ArrayList<String> dateList = new ArrayList<>(121);
        ArrayList<Integer> addConfirmList = new ArrayList<>(121);
        ArrayList<Integer> addSuspectList = new ArrayList<>(121);
        for (int i = 0; i < list.size(); i++) {
            GraphAddBean graphAddBean = list.get(i);
            dateList.add(graphAddBean.getDate());
            addConfirmList.add(graphAddBean.getAddConfirm());
            addSuspectList.add(graphAddBean.getAddSuspect());
        }
        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("addConfirmList", new Gson().toJson(addConfirmList));
        model.addAttribute("addSuspectList", new Gson().toJson(addSuspectList));
        return "graphAdd";
    }

    @GetMapping("/graphColumnar")
    public String graphColumnar(Model model) {
        List<GraphColumnarBean> list = GraphHandler.getGraphColumnarData();
        Collections.sort(list);
        ArrayList<String> nameList = new ArrayList<>(10);
        ArrayList<Integer> fromAbroadList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            GraphColumnarBean bean = list.get(i);
            nameList.add(bean.getArea());
            fromAbroadList.add(bean.getFromAbroad());
        }
        model.addAttribute("nameList", new Gson().toJson(nameList));
        model.addAttribute("fromAbroadList", new Gson().toJson(fromAbroadList));
        return "graphColumnar";
    }

    @GetMapping("/graphPie")
    public String graphPie(Model model) {
        List<GraphPieBean> list = GraphHandler.getGraphPieData();
        int total = 0;
        for (GraphPieBean bean : list) {
            total += bean.getValue();
        }
        Collections.sort(list);
        model.addAttribute("list", new Gson().toJson(list));
        model.addAttribute("total", total);
        return "graphPie";
    }

    @GetMapping("/map")
    public String map(Model model) {
        List<DataBean> list = dataService.list();
        List<MapBean> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DataBean dataBean = list.get(i);
            MapBean mapBean = new MapBean(dataBean.getArea(), dataBean.getConfirm());
            result.add(mapBean);
        }
        model.addAttribute("mapData", new Gson().toJson(result));
        return "map";
    }

}
