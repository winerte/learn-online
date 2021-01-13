package com.qf.vo;

import lombok.Data;

import java.util.Date;

/**
 * @USER: Tian
 * @DATE: 2021/1/12 10:16
 */
@Data
public class Comment {

    private Integer id;
    private Integer uid;
    private Integer cid;
    private Double score;
    private String des;
    private Date date;
}
