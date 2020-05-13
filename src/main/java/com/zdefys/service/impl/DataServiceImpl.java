package com.zdefys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdefys.bean.DataBean;
import com.zdefys.mapper.DataMapper;
import com.zdefys.service.DataService;
import org.springframework.stereotype.Service;

/**
 * @author: zdefys
 * @date: 2020/5/11 20:59
 * @version: v1.0
 * @description:
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,DataBean> implements DataService {









   /* @Override
    public List<DataBean> list() {
        List<DataBean> result = null;
        try {
            result = DataHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DataBean> listById(int id) {
        if (id==2) {
            return JsoupHandler.getData();
        }
        return list();
    }*/
}
