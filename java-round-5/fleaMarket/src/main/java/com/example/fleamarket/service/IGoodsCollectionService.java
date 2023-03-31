package com.example.fleamarket.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.GoodsCollection;
import org.springframework.stereotype.Service;


public interface IGoodsCollectionService extends IService<GoodsCollection> {
    Boolean insert(int userId,int goodId);
    Boolean remove(int userId,int goodId);
    IPage<GoodsCollection> findByPage(Page<GoodsCollection> page, LambdaQueryWrapper<GoodsCollection> userLambdaQueryWrapper);
}
