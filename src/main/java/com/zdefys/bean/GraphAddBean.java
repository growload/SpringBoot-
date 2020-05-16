package com.zdefys.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zdefys
 * @date: 2020/5/16 12:43
 * @version: v1.0
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphAddBean {

    private String date;
    private int addConfirm;
    private int addSuspect;
}
