package com.qf.dao;

import com.qf.pojo.vo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:23
 */
@Mapper
public interface CourseMapper {

    List<Course> findCourseAll();
}
