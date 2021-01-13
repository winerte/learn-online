package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    /**
     * 查询所有免费课程
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/findFreeCourseAll",method = RequestMethod.GET)
    public BaseResp findFreeCourseAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        return courseService.findFreeCourseAll(page,size);
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/findFeeCourseAll",method = RequestMethod.GET)
    public BaseResp findFeeCourseAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        return courseService.findFeeCourseAll(page,size);
    }



    @RequestMapping(value = "/findCourseMenuAll",method =RequestMethod.POST)
    public BaseResp findCourseMenuAll(@RequestBody Map map){
        return courseService.findCourseMenuAll(map);
    }

    @RequestMapping(value = "/findYiJiMenuMing",method = RequestMethod.POST)
    public BaseResp findYiJiMenuMing (@RequestBody Map map){
        return courseService.findYiJiMenuMing(map);
    }
    @RequestMapping(value = "/findErJiMenuMing",method = RequestMethod.POST)
    public BaseResp findErJiMenuMing (@RequestBody Map map){
        return courseService.findErJiMenuMing(map);
    }

    /**
     *
     * @param map  size  page  分页  +  一级菜单的id
     * @return
     */
    @RequestMapping(value = "/findCourseByYiJiMenu",method = RequestMethod.POST)
    public BaseResp findCourseByYiJiMenu(@RequestBody Map map){
        return courseService.findCourseByYiJiMenu(map);
    }


    /**
     *
     * @param map  size  page  分页  +  二级菜单的id
     * @return
     */
    @RequestMapping(value = "/findCourseByErjIMenu",method = RequestMethod.POST)
    public BaseResp findCourseByErjIMenu(@RequestBody Map map){
        return courseService.findCourseByErjIMenu(map);
    }


    /**
     *
     *
     * @param id   课程的id号
     * @return
     */
    @RequestMapping(value = "/findCourseById",method = RequestMethod.GET)
    public BaseResp findCourseById(@Param("id")Integer id){
        return courseService.findCourseById(id);
    }

    @RequestMapping(value = "/toPay",method = RequestMethod.POST)
    public BaseResp toPay(@RequestBody Map map, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        return courseService.toPay(map,request,response);
    }
}
