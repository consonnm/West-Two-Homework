package com.example.hotSpot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Message;

public interface IMessageService extends IService<Message> {
    boolean insertMessage(int userId, int spotId, String content,int category);
}
