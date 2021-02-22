package com.njust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njust.domain.User;



public interface UserMapper extends BaseMapper<User> {
    String selectExit(String phone);

    void insertSelective(User user);
}