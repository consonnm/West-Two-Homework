package com.example.hotSpot.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.SpotCollection;


public interface ISpotCollectionService extends IService<SpotCollection> {
    Boolean insert(int userId,int spotId);
    Boolean remove(int userId,int spotId);
    IPage<SpotCollection> findByPage(Page<SpotCollection> page, LambdaQueryWrapper<SpotCollection> userLambdaQueryWrapper);
}
