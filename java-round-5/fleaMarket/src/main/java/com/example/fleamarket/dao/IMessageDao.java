package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Message;
import com.example.fleamarket.entity.Reply;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDao extends BaseMapper<Message> {
}