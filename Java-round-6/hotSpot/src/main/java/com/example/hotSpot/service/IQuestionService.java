package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Question;

public interface IQuestionService extends IService<Question> {
    Boolean update(int questionId,String question,String answer);
    IPage<Question> findByPage(Page<Question> page, LambdaQueryWrapper<Question> userLambdaQueryWrapper);
    Boolean remove(int questionId);
    Boolean insert(String question,String answer);
}
