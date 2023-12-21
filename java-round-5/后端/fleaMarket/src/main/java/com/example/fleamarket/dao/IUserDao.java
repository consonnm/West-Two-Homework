package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseMapper<User> {
}
