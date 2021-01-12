package com.qf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtModified;

    private String pic;

    private Integer subjectParentId;

}
