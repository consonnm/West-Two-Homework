package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Announcement;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementDao extends BaseMapper<Announcement> {
}
