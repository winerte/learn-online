package com.qf.pojo.req;

import lombok.Data;

/**
 * Created by Wang on 2021/1/8 16:41
 */
@Data
public class UserReq {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String token;
}
