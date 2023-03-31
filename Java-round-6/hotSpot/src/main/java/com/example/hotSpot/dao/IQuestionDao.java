package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionDao extends BaseMapper<Question> {
}
