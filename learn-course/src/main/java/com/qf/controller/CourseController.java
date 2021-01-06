package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:17
 */
@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/findCourseAll",method = RequestMethod.GET)
    public BaseResp findCourseAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        return courseService.findCourseAll(page,size);
    }
}
