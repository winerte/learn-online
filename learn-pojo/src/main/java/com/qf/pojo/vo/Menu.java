package com.qf.pojo.vo;

import lombok.Data;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 17:17
 */
@Data
public class Menu {

    private Integer id;

    private String name;

    private Integer level;

    private Integer parent;
}
