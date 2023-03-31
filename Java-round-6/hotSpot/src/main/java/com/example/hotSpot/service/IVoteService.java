package com.example.hotSpot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.User;
import com.example.hotSpot.entity.Vote;

public interface IVoteService extends IService<Vote> {
    Boolean insert(int userId,int spotId);
    Boolean find(int userId,int spotId);
}
