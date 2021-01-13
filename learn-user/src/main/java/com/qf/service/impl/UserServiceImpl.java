package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.req.UserReq;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;
import com.qf.service.UserService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import com.qf.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by Wang on 2021/1/8 14:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RedisUtils redisUitls;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public BaseResp login(UserReq userReq) {
        //声明返回值
        BaseResp baseResp = new BaseResp();
        //获取到用户名
        String userName = userReq.getUsername();
        User byUserName = userRepository.findByUsername(userName);
        //判断
        if (byUserName == null) {
            baseResp.setCode(20001);
            baseResp.setMessage("用户未找到");
            return baseResp;
        }
        if (!byUserName.getPassword().equals(userReq.getPassword())) {
            baseResp.setCode(20002);
            baseResp.setMessage("密码错误");
            return baseResp;
        }
        //使用jwt进行加密
        JWTUtils jwtUtils = new JWTUtils();
        //放置在 jwt中的负载部分
        Map map = new HashMap<>();
        map.put("username", byUserName.getUsername());
        map.put("id", byUserName.getId());
        String token = jwtUtils.token(map);
        baseResp.setCode(200);
        baseResp.setMessage("登录成功");
        baseResp.setData(token);
        baseResp.setName(byUserName.getUsername());
        return baseResp;
    }

    @Override
    public BaseResp sendMail(String email) {
        BaseResp baseResp = new BaseResp();
        if (email != null) {
            User user = userMapper.findByEmail(email);
            if (user != null) {
                baseResp.setCode(201);
                baseResp.setMessage("该邮箱已被使用");
                return baseResp;
            }
            Random random = new Random();
            StringBuffer code = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                int i1 = random.nextInt(10);
                code.append(i1);
            }
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("验证码");
            simpleMailMessage.setText(code.toString());
            javaMailSender.send(simpleMailMessage);
            redisUitls.set(email, code.toString());
            redisUitls.expire(email, 300);
            baseResp.setCode(200);
            baseResp.setMessage("验证码发送成功");
            return baseResp;
        }
        baseResp.setCode(203);
        baseResp.setMessage("邮箱不能为空");

        return baseResp;
    }

    @Override
    public BaseResp registry(UserReq userReq) {
        BaseResp baseResp = new BaseResp();
        String userName = userReq.getUsername();
        User user = userMapper.findUserName(userName);
        if (user != null) {
            baseResp.setCode(201);
            baseResp.setMessage("账户已存在");
            return baseResp;
        }
        String code = userReq.getToken();
        String email = userReq.getEmail();
        String s = (String) redisUitls.get(email);
        if (s != null && code.equals(s)) {
            User user1 = new User();
            BeanUtils.copyProperties(userReq, user1);
            userMapper.registry(user1);
            baseResp.setCode(200);
            baseResp.setMessage("用户注册成功");
            return baseResp;
        }

        baseResp.setCode(205);
        baseResp.setMessage("用户注册失败");
        ;
        return baseResp;
    }
}
