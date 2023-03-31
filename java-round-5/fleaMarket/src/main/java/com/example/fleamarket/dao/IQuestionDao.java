package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Posts;
import com.example.fleamarket.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionDao extends BaseMapper<Question> {
}
