package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Follow;

public interface IFollowService extends IService<Follow> {
    Boolean remove(int followerId,int followedId);
    Boolean insert(int followerId,int followedId);
    IPage<Follow> findByPage(Page<Follow> page, LambdaQueryWrapper<Follow> userLambdaQueryWrapper);
}
