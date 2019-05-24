package com.gamazing.springbootshirodemo.dao;

import com.gamazing.springbootshirodemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User findOneByUsername(String username);
}
