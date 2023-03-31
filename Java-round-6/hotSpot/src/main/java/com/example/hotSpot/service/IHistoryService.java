package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.History;

public interface IHistoryService extends IService<History> {
    Boolean insert(int userId,int goodId);
    Boolean remove(int userId,int goodId);
    IPage<History> findByPage(Page<History> page, LambdaQueryWrapper<History> userLambdaQueryWrapper);
    Boolean find(int userId,int goodId);
}
