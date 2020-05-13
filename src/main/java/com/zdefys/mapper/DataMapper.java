package com.zdefys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdefys.bean.DataBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zdefys
 * @date: 2020/5/13 14:27
 * @version: v1.0
 * @description:
 */
@Mapper
public interface DataMapper extends BaseMapper<DataBean> {
}
