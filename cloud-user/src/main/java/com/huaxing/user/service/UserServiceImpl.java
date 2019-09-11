package com.huaxing.user.service;

import com.huaxing.user.mapper.UserMapper;
import com.huaxing.user.model.User;
import com.huaxing.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUserByID(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }
}
