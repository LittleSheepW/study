package com.ww.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Sun
 * @create: 2019-11-14 10:36
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

    /**
     * ID
     */
    private Integer id;

    /**
     * 颜色
     */
    private String color;

    /**
     * 开始售卖时间
     */
    private Long beginTime;

}
