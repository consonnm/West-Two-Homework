package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Announcement;

public interface IAnnouncementService extends IService<Announcement> {
    Boolean insert(String context,String topic,String time);
    Boolean remove(int announcementId);
    IPage<Announcement> findByPage(Page<Announcement> page, LambdaQueryWrapper<Announcement> userLambdaQueryWrapper);
}
