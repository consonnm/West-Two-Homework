package com.example.fleamarket.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.GoodsCollection;
import com.example.fleamarket.entity.PostsCollection;


public interface IPostsCollectionService extends IService<PostsCollection> {
    Boolean insert(int userId,int goodId);
    Boolean remove(int userId,int goodId);
    IPage<PostsCollection> findByPage(Page<PostsCollection> page, LambdaQueryWrapper<PostsCollection> userLambdaQueryWrapper);
}
