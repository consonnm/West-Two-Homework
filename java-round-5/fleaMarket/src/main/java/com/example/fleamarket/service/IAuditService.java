package com.example.fleamarket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Announcement;
import com.example.fleamarket.entity.Audit;

public interface IAuditService extends IService<Audit> {
    Boolean insert(Boolean status,int goodId,int adminId);
    IPage<Audit> findByPage(Page<Audit> page, LambdaQueryWrapper<Audit> userLambdaQueryWrapper);
}
