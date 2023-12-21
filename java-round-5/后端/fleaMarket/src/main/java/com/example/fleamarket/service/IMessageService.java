package com.example.fleamarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Message;
import com.example.fleamarket.entity.Reply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMessageService extends IService<Message> {
    boolean insertTextMessage(int user1Id, int User2Id, String content);
    boolean insertPic(int user1Id, int user2Id,MultipartFile file);
    List<Message> queryForMessageList(int user1Id,int user2Id);
}
