package com.gamazing.springbootshirodemo.service.impl;

import com.gamazing.springbootshirodemo.dao.UserDao;
import com.gamazing.springbootshirodemo.entity.User;
import com.gamazing.springbootshirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findOneByUsername(String username) {
        return userDao.findOneByUsername(username);
    }
}
