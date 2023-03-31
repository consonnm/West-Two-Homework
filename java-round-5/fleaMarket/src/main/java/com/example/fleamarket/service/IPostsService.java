package com.example.fleamarket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.entity.Goods;
import com.example.fleamarket.entity.Posts;

import java.util.List;

public interface IPostsService extends IService<Posts> {
    Posts queryById(int PostId);
     int insert(int buyerId,String description);
     Boolean remove(int postId);
     List<Category> findCategorylist(int postId);
     boolean addCategory(int postId,List<Category>categoryList);
    IPage<Posts> findByPage(Page<Posts> page, LambdaQueryWrapper<Posts> userLambdaQueryWrapper);

}
