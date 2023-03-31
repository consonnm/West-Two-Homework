package com.example.ibookreader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ibookreader.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseMapper<User> {
    User user(@Param("userName")String userName, @Param("userPassword")String userPassword);
}
