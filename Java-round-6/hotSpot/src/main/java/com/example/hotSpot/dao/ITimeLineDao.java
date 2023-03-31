package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.TimeLine;
import org.springframework.stereotype.Repository;

@Repository
public interface ITimeLineDao extends BaseMapper<TimeLine> {
}
