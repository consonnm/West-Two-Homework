package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Comment;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value ="/commment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    public CommentController(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }
    @RequiresRoles("user::user")
    @ApiOperation("查询所有评论")
    @GetMapping("/getAllComment")
    public ResultVo all(@ApiParam("用户id")int userId, @ApiParam("当前页")int current, @ApiParam("大小")int size){
        log.info("查询所有评论");
        Page<Comment> page = new Page<>(current , size );
        LambdaQueryWrapper<Comment> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Comment::getBeCommentedUserId,userId);
        return new ResultVo().setData(iCommentService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加评论")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("评论者id")int commentUserId,@ApiParam("被评论者id")int beCommentUserId,@ApiParam("评论内容")String context){
        log.info("增加评论");
        return new ResultVo().setData(iCommentService.insert(commentUserId,beCommentUserId,context));
    }
    @RequiresRoles("usr::user")
    @ApiOperation("删除评论")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("评论id")int commentId){
        log.info("删除评论");
        return new ResultVo().setData(iCommentService.remove(commentId));
    }


}
