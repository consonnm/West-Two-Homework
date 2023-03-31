package com.example.ibookreader.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ibookreader.entity.User;

import java.net.MalformedURLException;

public interface IUserService extends IService<User> {
    User user(String username,String userPassword);

    User queryById(String id);

    String insertUser(String userName, String passWord);

    Boolean login(String userName,String passWord);
    void updategg() throws MalformedURLException;
}
