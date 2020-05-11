package com.zdefys.service.impl;

import com.zdefys.bean.DataBean;
import com.zdefys.handler.DataHandler;
import com.zdefys.service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zdefys
 * @date: 2020/5/11 20:59
 * @version: v1.0
 * @description:
 */
@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<DataBean> list() {
        List<DataBean> result = null;
        try {
            result = DataHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
