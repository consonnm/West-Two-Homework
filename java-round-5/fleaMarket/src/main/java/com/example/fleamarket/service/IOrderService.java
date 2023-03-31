package com.example.fleamarket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Order;

public interface IOrderService extends IService<Order> {
    Boolean baseUpdate(String buyerName,String place,String pay,int orderId);
    Boolean statusUpdate(int orderId);
    int insert(int goodId,int solderId,int buyerId,String time,String buyerName,String place,String pay);
    IPage<Order> findByPage(Page<Order> page, LambdaQueryWrapper<Order> userLambdaQueryWrapper);
}