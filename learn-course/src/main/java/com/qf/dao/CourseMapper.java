package com.qf.dao;

import com.qf.pojo.vo.Course;
import com.qf.pojo.vo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:23
 */
@Mapper
public interface CourseMapper {



    List<Course> findCourseAll();

    List<Menu> findCourseMenuAll(@Param("level") Integer level);

    Menu findYiJiMenuMing(@Param("id") Integer id);

    List<Menu> findErJiMenuMing(@Param("parent") Integer parent);

    List<Course> findCourseByYiJiMenu(@Param("subjectParentId") Integer subjectParentId);

    List<Course> findCourseByErjIMenu(@Param("subjectId") Integer subjectId);

    List<Course> findFreeCourseAll();

    List<Course> findFeeCourseAll();

    Course findCourseById(@Param("id") Integer id);
}
