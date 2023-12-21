package com.example.fleamarket.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService extends IService<User> {
    User queryById(String id);

    Boolean insertUser(String userName, String passWord);

    Boolean updateUer(int userId,String nickname,String phone,String age,String qq);

    Boolean updatePhoto(MultipartFile file,int userId);

    Boolean unqualifiedGoodsUpdate(String userName);

    IPage<User> findByPage(Page<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper);

    Boolean scoreUpdate(String userName,double score);
    String changeUser(int userId, String password1,String password2,String password3);
}
