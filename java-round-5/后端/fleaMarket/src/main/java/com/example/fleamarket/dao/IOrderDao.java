package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends BaseMapper<Order> {
}
