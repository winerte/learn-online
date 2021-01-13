package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.CourseMapper;
import com.qf.dao.OrdersMapper;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Course;
import com.qf.pojo.vo.Menu;
import com.qf.pojo.vo.Orders;
import com.qf.service.CourseService;
import com.qf.utils.AlipayUtils;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: yanjia
 * @DATE: 2021/1/6 14:20
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public BaseResp findCourseAll(Integer page, Integer size) {
        BaseResp baseResp = new BaseResp();
        PageHelper.startPage(page, size);
        List<Course> list = courseMapper.findCourseAll();
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        if (coursePageInfo != null) {
            baseResp.setData(coursePageInfo.getList());
            baseResp.setCode(200);
            baseResp.setMessage("查询课程所有成功");
            baseResp.setTotal(coursePageInfo.getTotal());
        } else {
            baseResp.setCode(2001);
            baseResp.setMessage("查询课程所有成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp findCourseMenuAll(Map map) {
        Integer level = (Integer) map.get("level");
        BaseResp baseResp = new BaseResp();
        List<Menu> list = courseMapper.findCourseMenuAll(level);
        if (list != null) {
            baseResp.setData(list);
            baseResp.setCode(200);
            baseResp.setMessage("查询一级菜单成功");
        } else {
            baseResp.setCode(2002);
            baseResp.setMessage("查询一级菜单失败");
        }
        return baseResp;
    }

    @Override
    public BaseResp findYiJiMenuMing(Map map) {
        BaseResp baseResp = new BaseResp();
        Integer id = (Integer) map.get("id");
        Menu menu = courseMapper.findYiJiMenuMing(id);
        if (menu != null) {
            baseResp.setCode(200);
            baseResp.setMessage("一级菜单查找成功");
            baseResp.setData(menu);
        } else {
            baseResp.setCode(2002);
            baseResp.setMessage("一级菜单查找失败");
        }
        return baseResp;
    }

    @Override
    public BaseResp findErJiMenuMing(Map map) {
        BaseResp baseResp = new BaseResp();
        Integer parent = (Integer) map.get("parent");
        List<Menu> list = courseMapper.findErJiMenuMing(parent);
        if (list != null) {
            baseResp.setData(list);
            baseResp.setCode(200);
            baseResp.setMessage("查询二级菜单成功");
        } else {
            baseResp.setCode(2002);
            baseResp.setMessage("查询二级菜单失败");
        }
        return baseResp;
    }


    @Override
    public BaseResp findCourseByYiJiMenu(Map map) {
        Integer subjectParentId = (Integer) map.get("subjectParentId");
        Integer page = (Integer) map.get("page");
        Integer size = (Integer) map.get("size");
        PageHelper.startPage(page,size);
        List<Course> list = courseMapper.findCourseByYiJiMenu(subjectParentId);
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        BaseResp baseResp = new BaseResp();
        if(list != null){
            baseResp.setMessage("根据一级菜单查询课程成功");
            baseResp.setTotal(coursePageInfo.getTotal());
            baseResp.setData(list);
            baseResp.setCode(200);
        }else{
            baseResp.setMessage("根据一级菜单查询课程失败");
            baseResp.setCode(2005);
        }
        return baseResp;
    }

    @Override
    public BaseResp findCourseByErjIMenu(Map map) {
        Integer subjectId = null;
        if( map.get("subjectId").toString()!=""){
            subjectId = Integer.valueOf(map.get("subjectId").toString()) ;
        }

        Integer page = (Integer) map.get("page");
        Integer size = (Integer) map.get("size");
        PageHelper.startPage(page,size);
        List<Course> list = courseMapper.findCourseByErjIMenu(subjectId);
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        BaseResp baseResp = new BaseResp();
        if(list != null){
            baseResp.setMessage("根据二级菜单查询课程成功");
            baseResp.setTotal(coursePageInfo.getTotal());
            baseResp.setData(list);
            baseResp.setCode(200);
        }else{
            baseResp.setMessage("根据二级菜单查询课程失败");
            baseResp.setCode(2006);
        }
        return baseResp;
    }

    @Override
    public BaseResp findFreeCourseAll(Integer page, Integer size) {

        BaseResp baseResp = new BaseResp();
        PageHelper.startPage(page, size);
        List<Course> list = courseMapper.findFreeCourseAll();
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        if (coursePageInfo != null) {
            baseResp.setData(coursePageInfo.getList());
            baseResp.setCode(200);
            baseResp.setMessage("查询免费课程所有成功");
            baseResp.setTotal(coursePageInfo.getTotal());
        } else {
            baseResp.setCode(2004);
            baseResp.setMessage("查询免费课程所有成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp findFeeCourseAll(Integer page, Integer size) {

        BaseResp baseResp = new BaseResp();
        PageHelper.startPage(page, size);
        List<Course> list = courseMapper.findFeeCourseAll();
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        if (coursePageInfo != null) {
            baseResp.setData(coursePageInfo.getList());
            baseResp.setCode(200);
            baseResp.setMessage("查询收费课程所有成功");
            baseResp.setTotal(coursePageInfo.getTotal());
        } else {
            baseResp.setCode(2006);
            baseResp.setMessage("查询收费课程所有成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp findCourseById(Integer id) {
        BaseResp baseResp = new BaseResp();
        Course course = courseMapper.findCourseById(id);
        if(course != null){
            baseResp.setCode(200);
            baseResp.setMessage("根据id查询课程成功");
            baseResp.setData(course);

        }else{
            baseResp.setCode(2008);
            baseResp.setMessage("根据id查询课程失败");
        }
        return baseResp;
    }

    @Override
    public BaseResp toPay(Map map, HttpServletRequest request, HttpServletResponse response) {
        UUID uuid = UUID.randomUUID();
        String string  = uuid.toString();

        String tradno = string.replace("-", "");
        Orders orders = new Orders();
        Integer cid = (Integer) map.get("cid");

        Cookie[] cookies = request.getCookies();
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer userId = (Integer)verify.get("id");
        Course courseById = courseMapper.findCourseById(cid);
        orders.setClassName(courseById.getTitle());
        orders.setTransferId(tradno);
        orders.setPrice(courseById.getPrice());
        orders.setStatus("未支付");
        orders.setUserId(userId);
        ordersMapper.insertOrders(orders);
        AlipayUtils alipayUtils = new AlipayUtils();
//        AlipayUtils alipayUtils = new AlipayUtils();
        String pay = null;
        try {
            pay = alipayUtils.pay(request, response, orders);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseResp baseResp = new BaseResp();
        baseResp.setData(pay);
        return baseResp;



    }


}
