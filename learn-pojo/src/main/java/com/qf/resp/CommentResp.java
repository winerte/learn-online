package com.qf.resp;

import lombok.Data;

import java.util.Date;

/**
 * @USER: Tian
 * @DATE: 2021/1/12 10:53
 */
@Data
public class CommentResp {
    private Integer id;
    private Integer uid;
    private Integer cid;
    private Double score;
    private String des;
    private Date date;

    private String username;
    private String head;


}
