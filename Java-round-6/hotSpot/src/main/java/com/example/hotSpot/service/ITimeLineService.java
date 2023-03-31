package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.TimeLine;

public interface ITimeLineService extends IService<TimeLine> {
    Boolean baseUpdate(int spotId,String time,String describe,int timeId);
    int insert(int spotId,String time,String describe);
    IPage<TimeLine> findByPage(Page<TimeLine> page, LambdaQueryWrapper<TimeLine> userLambdaQueryWrapper);
    Boolean remove(int timeId);
}