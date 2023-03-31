package com.example.ibookreader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ibookreader.dao.IUserDao;
import com.example.ibookreader.entity.User;
import com.example.ibookreader.service.IUserService;
import com.example.ibookreader.utils.Datee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
// lombok 的这个注解把你要注入的类都给注入了
// 比如这个 mapper
@AllArgsConstructor
public class UserService extends ServiceImpl<IUserDao,User> implements IUserService {
    @Autowired
    private Datee date;

    private IUserDao iUserDao;
    @Override
    public User user(String userName, String userPassword) {
        return iUserDao.user(userName, userPassword);
    }

    // 条件查询
    @Override
    public User queryById(String userName) {
        return getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserId,userName)
        );
    }
    @Override
    public String insertUser(String userName, String passWord) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(passWord);
        User one = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName)
        );
        if(ObjectUtils.isNotEmpty(one)){
            return "用户名已被占用";
        }
        if(save(user))
            return "注册成功";
        else
            return "注册失败";
    }

    @Override
    public Boolean login(String userName, String passWord) {
        User one = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName)
                .eq(User::getUserPassword, passWord)
        );
        return ObjectUtils.isNotEmpty(one);
    }
    @Override
    public  void updategg() throws MalformedURLException {
        date.get();
    }
}

