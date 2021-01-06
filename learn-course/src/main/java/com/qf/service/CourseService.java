package com.qf.service;

import com.qf.pojo.resp.BaseResp;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:20
 */

public interface CourseService {
    BaseResp findCourseAll(Integer page, Integer size);

}
