package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Audit;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditDao extends BaseMapper<Audit> {
}
