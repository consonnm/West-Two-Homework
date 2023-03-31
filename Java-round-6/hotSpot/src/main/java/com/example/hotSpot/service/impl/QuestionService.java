package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IQuestionDao;
import com.example.hotSpot.entity.Question;
import com.example.hotSpot.service.IQuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends ServiceImpl<IQuestionDao, Question> implements IQuestionService {
    public Boolean insert(String question,String answer){
        Question question1 = new Question();
        question1.setQuestion(question);
        question1.setAnswer(answer);
        return save(question1);
    }
    public Boolean remove(int questionId) {
        LambdaQueryWrapper<Question> lwq = Wrappers.lambdaQuery();
        lwq.eq(Question::getQuestionId,questionId);
        return remove(lwq);
    }
    public IPage<Question> findByPage(Page<Question> page, LambdaQueryWrapper<Question> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    public Boolean update(int questionId,String question,String answer){
        Question question1 = baseMapper.selectById(questionId);
        question1.setQuestion(question);
        question1.setAnswer(answer);
        return saveOrUpdate(question1);
    }
}
