package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Audit;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditDao extends BaseMapper<Audit> {
}
