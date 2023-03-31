package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.User;
import com.example.hotSpot.entity.Vote;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteDao extends BaseMapper<Vote> {
}