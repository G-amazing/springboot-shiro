package com.gamazing.springbootshirodemo.service;

import com.gamazing.springbootshirodemo.entity.Perm;

public interface PermService {
    Perm findOneByUserId(Integer userId);
}
