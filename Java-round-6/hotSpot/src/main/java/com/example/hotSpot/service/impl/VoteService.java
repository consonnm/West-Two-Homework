package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IVoteDao;
import com.example.hotSpot.entity.History;
import com.example.hotSpot.entity.Vote;
import com.example.hotSpot.service.IVoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteService extends ServiceImpl<IVoteDao, Vote> implements IVoteService {
    @Override
    public Boolean insert(int userId,int spotId){
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setSpotId(spotId);
        return save(vote);
    }
    @Override
    public Boolean find(int userId,int spotId){
        Vote one = getOne(new LambdaQueryWrapper<Vote>()
                .eq(Vote::getSpotId,spotId).eq(Vote::getUserId,userId)
        );
        if(ObjectUtils.isNotEmpty(one)){
            return true;
        }
        return false;
    }

}
