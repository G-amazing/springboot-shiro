package com.gamazing.springbootshirodemo.service;

import com.gamazing.springbootshirodemo.entity.User;

public interface UserService {
    User findOneByUsername(String username);
}
