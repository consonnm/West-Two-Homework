package com.example.fleamarket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.GoodReport;

public interface IGoodReportService extends IService<GoodReport> {
    IPage<GoodReport> findByPage(Page<GoodReport> page, LambdaQueryWrapper<GoodReport> userLambdaQueryWrapper);
    Boolean insert(String context,int goodId);
    Boolean update(String status, int reporId);
}
