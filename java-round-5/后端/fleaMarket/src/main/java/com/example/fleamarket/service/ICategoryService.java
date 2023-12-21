package com.example.fleamarket.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.entity.Posts;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    int insert(String name,String describe);
    Boolean remove(int id);
    IPage<Category> findByPage(Page<Category> page, LambdaQueryWrapper<Category> userLambdaQueryWrapper);
    List<Posts> findPostsByCategory(int categoryId);
}
