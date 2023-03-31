package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Announcement;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementDao extends BaseMapper<Announcement> {
}
