package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Question;
import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IQuestionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    IQuestionService iQuestionService;
    @ApiOperation("查询所有问题")
    @GetMapping("/getAllAnnouncement")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size){
        log.info("查询所有问题");
        Page<Question> page = new Page<>(current, size );
        return new ResultVo().setCode(200).setData(iQuestionService.findByPage(page,null));
    }
    @RequiresRoles("admin")
    @ApiOperation("增加问题")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="question",value="问题"),
            @ApiImplicitParam(name="answer",value="回答"),
    })
    public ResultVo insert(String question,String answer){
        log.info("增加问题");
        return new ResultVo().setCode(200).setData(iQuestionService.insert(question,answer));
    }
    //@RequiresRoles("admin")
    @ApiOperation("删除问题")
    @DeleteMapping("/remove")
    @ApiImplicitParam(name="questionId",value="问题id")
    public ResultVo remove(int questionId){
        log.info("删除问题");
        if(iQuestionService.remove(questionId)){
            return new ResultVo().setCode(200);
        }
        else throw new ControllerException("Id不存在");
    }
    //@RequiresRoles("admin")
    @ApiOperation("修改问题")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name="question",value="问题"),
            @ApiImplicitParam(name="answer",value="回答"),
            @ApiImplicitParam(name="questionId",value="问题id"),
    })
    public ResultVo baseUpdate(int questionId,String question,String answer) {
        log.info("修改问题");
        return new ResultVo().setCode(200).setData(iQuestionService.update(questionId,question,answer));
    }
}
