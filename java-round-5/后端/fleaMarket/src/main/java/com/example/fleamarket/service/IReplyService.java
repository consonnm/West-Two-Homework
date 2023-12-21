package com.example.fleamarket.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Reply;

import java.util.List;

public interface IReplyService extends IService<Reply>{
    int insertWithPic(int floor,int postId,int sellManId,String pic,String description);
    int insertWithoutPic(int floor,int postId,int sellManId,String description);
    Boolean remove(int postId);
    List<Reply> queryForReplyList(int postid);
}
