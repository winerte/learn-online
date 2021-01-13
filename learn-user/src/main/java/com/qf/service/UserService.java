package com.qf.service;

import com.qf.pojo.req.UserReq;
import com.qf.pojo.resp.BaseResp;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wang on 2021/1/8 14:45
 */
public interface UserService {

    BaseResp login(UserReq userReq);

    BaseResp sendMail(String email);

    BaseResp registry(UserReq userReq);
}
