package com.example.fleamarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Conversation;
import com.example.fleamarket.entity.Message;


public interface IConversationService extends IService<Conversation> {
    Conversation queryById(int user1Id,int user2Id);
    boolean insert(int user1Id,int user2Id);
}
