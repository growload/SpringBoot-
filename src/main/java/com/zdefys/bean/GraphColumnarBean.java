package com.zdefys.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zdefys
 * @date: 2020/5/16 16:56
 * @version: v1.0
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphColumnarBean implements Comparable<GraphColumnarBean>{

    private String area;
    private int fromAbroad;


    @Override
    public int compareTo(GraphColumnarBean o) {
        return o.getFromAbroad() - this.getFromAbroad();
    }
}
