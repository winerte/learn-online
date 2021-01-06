package com.qf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 11:33
 */
@Data
public class Course {

    private Integer id;

    private Integer teacherId;

    private Integer subjectId;

    private String title;

    private Double price;

    private Integer lessonNum;

    private String cover;

    private Integer buyCount;

    private Integer viewCount;

    private String version;

    private String status;

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer subjectParentId;

}
