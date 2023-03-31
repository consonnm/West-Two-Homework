package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IMessageDao;
import com.example.hotSpot.entity.Message;
import com.example.hotSpot.service.IMessageService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MessageService extends ServiceImpl<IMessageDao, Message> implements IMessageService {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @Override
    public boolean insertMessage(int userId, int spotId, String content,int category) {
        Message message = new Message();
        message.setUserId(userId);
        message.setSpotId(spotId);
        message.setContent(content);
        LocalDate localDate = LocalDate.now();
        String now = String.valueOf(localDate);
        message.setDate(now);
        message.setCategory(category);
        return save(message);
    }
}
