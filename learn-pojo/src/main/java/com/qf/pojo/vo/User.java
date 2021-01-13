package com.qf.pojo.vo;

import lombok.Data;

import javax.persistence.*;


/**
 * Created by Wang on 2021/1/8 14:29
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String head;

    private String token;

    private Integer status;
}
