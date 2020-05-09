package com.zdefys.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhangdeen
 * @date: 2020/5/9 12:36
 * @version: v1.0
 * @descrption:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBean {

    /**
     * 区域名字
     */
    private String area;

    /**
     * 现有确诊
     */
    private int nowConfirm;

    /**
     * 累计确诊
     */
    private int confirm;

    /**
     * 治愈
     */
    private int heal;

    /**
     * 死亡
     */
    private int dead;
}
