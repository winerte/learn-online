package com.qf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.CourseMapper;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Course;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:20
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public BaseResp findCourseAll(Integer page, Integer size) {
        BaseResp baseResp = new BaseResp();
        PageHelper.startPage(page, size);
        List<Course>  list  =  courseMapper.findCourseAll();
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        if(coursePageInfo != null){
            baseResp.setData(coursePageInfo.getList());
            baseResp.setCode(200);
            baseResp.setMessage("查询课程所有成功");
            baseResp.setTotal(coursePageInfo.getTotal());
        }else{
            baseResp.setCode(2001);
            baseResp.setMessage("查询课程所有成功");
        }
        return baseResp;
    }
}
