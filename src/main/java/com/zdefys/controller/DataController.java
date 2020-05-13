package com.zdefys.controller;

import com.zdefys.bean.DataBean;
import com.zdefys.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
