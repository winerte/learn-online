package com.qf.pojo.vo;

import lombok.Data;

/**
 * @Author: yanjia
 * @DATE: 2021/1/12 19:58
 */
@Data
public class Orders {

    private Integer id;

    private String className;

    private Double price;

    private String status;

    private Integer userId;

    private String transferId;
}
