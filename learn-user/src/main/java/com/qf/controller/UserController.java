package com.qf.controller;


import com.qf.pojo.req.UserReq;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Wang on 2021/1/8 14:44
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody UserReq userReq){
        return userService.login(userReq);

    }

    @RequestMapping(value = "/sendMail")
    private BaseResp sendMail(@RequestParam("email") String email){
        return userService.sendMail(email);
    }

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    private BaseResp registry(@RequestBody UserReq userReq){
        return userService.registry(userReq);
    }





}
