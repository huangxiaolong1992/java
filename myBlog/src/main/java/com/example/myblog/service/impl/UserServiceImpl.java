package com.example.myblog.service.impl;

import com.example.myblog.entity.User;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int Register(User user) {
        return userMapper.Register(user);
    }

    @Override
    public User GetUser(String loginName) {
        return userMapper.GetUser(loginName);
    }
}
