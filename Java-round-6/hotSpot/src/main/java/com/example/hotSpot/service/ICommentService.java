package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Comment;

public interface ICommentService extends IService<Comment> {
    Boolean remove(int commentId);
    IPage<Comment> findByPage(Page<Comment> page, LambdaQueryWrapper<Comment> userLambdaQueryWrapper);
    Boolean insert(int commentId,int becommentedId,String Context,int sort);
}
