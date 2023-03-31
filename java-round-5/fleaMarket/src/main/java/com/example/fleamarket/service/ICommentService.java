package com.example.fleamarket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Comment;

public interface ICommentService extends IService<Comment> {
    Boolean insert(int commentId,int becommentedId,String Context);
    Boolean remove(int commentId);
    IPage<Comment> findByPage(Page<Comment> page, LambdaQueryWrapper<Comment> userLambdaQueryWrapper);
}
