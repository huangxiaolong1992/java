package com.example.myblog.service;

import com.example.myblog.entity.User;

public interface UserService {
    int Register(User user);

    User GetUser(String loginName);

}
