package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IMessageDao;
import com.example.fleamarket.entity.Message;

import com.example.fleamarket.service.IMessageService;

import com.example.fleamarket.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageService extends ServiceImpl<IMessageDao, Message> implements IMessageService {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;


    @Override
    public boolean insertTextMessage(int user1Id, int user2Id, String content) {

        Message message = new Message();
        message.setUser1Id(user1Id);
        message.setUser2Id(user2Id);
        message.setDate(LocalDate.now());
        message.setContent(content);

        return save(message);
    }

    @Override
    public boolean insertPic(int user1Id, int user2Id, MultipartFile file) {
        String url = aliyunOSSUtil.upload(file);
        Message message = new Message();
        message.setUser1Id(user1Id);
        message.setUser2Id(user2Id);
        message.setDate(LocalDate.now());
        message.setContent(url);

        return save(message);
    }


    @Override
    public List<Message> queryForMessageList(int user1Id, int user2Id) {
        List<Message> arr = this.list(
                new LambdaQueryWrapper<Message>()
                        .eq(Message::getUser1Id, user1Id).eq(Message::getUser2Id, user2Id));
        arr.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));

        return arr;


    }
}
