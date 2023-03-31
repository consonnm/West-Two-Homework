package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.SpotReport;

public interface ISpotReportService extends IService<SpotReport> {
    IPage<SpotReport> findByPage(Page<SpotReport> page, LambdaQueryWrapper<SpotReport> userLambdaQueryWrapper);
    Boolean insert(String context,int goodId);
    Boolean update(String status, int reporId);
}
