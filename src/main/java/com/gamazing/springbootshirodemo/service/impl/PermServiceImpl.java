package com.gamazing.springbootshirodemo.service.impl;

import com.gamazing.springbootshirodemo.dao.PermDao;
import com.gamazing.springbootshirodemo.entity.Perm;
import com.gamazing.springbootshirodemo.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermDao permDao;

    @Override
    public Perm findOneByUserId(Integer userId) {
        return permDao.findOneByUserId(userId);
    }
}
