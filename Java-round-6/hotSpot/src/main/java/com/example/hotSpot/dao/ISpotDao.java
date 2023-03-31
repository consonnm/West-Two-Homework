package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Spot;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpotDao extends BaseMapper<Spot> {
}
