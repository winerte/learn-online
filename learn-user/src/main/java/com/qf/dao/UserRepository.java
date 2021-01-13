package com.qf.dao;

import com.qf.pojo.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wang on 2021/1/8 16:46
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String userName);
}
