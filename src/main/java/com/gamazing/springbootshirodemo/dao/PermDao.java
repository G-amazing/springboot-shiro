package com.gamazing.springbootshirodemo.dao;

import com.gamazing.springbootshirodemo.entity.Perm;
import org.springframework.stereotype.Repository;

@Repository
public interface PermDao {
    Perm findOneByUserId(Integer userId);
}
