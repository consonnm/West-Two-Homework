package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDao extends BaseMapper<Message> {
}