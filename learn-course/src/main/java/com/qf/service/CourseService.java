package com.qf.service;

import com.qf.pojo.resp.BaseResp;

import java.util.Map;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:20
 */

public interface CourseService {
    BaseResp findCourseAll(Integer page, Integer size);

    BaseResp findCourseMenuAll(Map map);

    BaseResp findYiJiMenuMing(Map map);

    BaseResp findErJiMenuMing(Map map);

    BaseResp findCourseByYiJiMenu(Map map);

    BaseResp findCourseByErjIMenu(Map map);

}
