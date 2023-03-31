package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseMapper<User> {
}
