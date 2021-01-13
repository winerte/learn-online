package com.qf.dao;

import com.qf.pojo.req.UserReq;
import com.qf.pojo.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Wang on 2021/1/11 14:39
 */
@Mapper
public interface UserMapper {

    User findByEmail(@Param("email") String email);

    User findUserName(@Param("username") String userName);

    int registry(User user);
}
