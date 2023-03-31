package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Comment;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ICommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value ="/commment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    public CommentController(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }
    //@RequiresRoles("user::user")
    @ApiOperation("查询所有评论")
    @GetMapping("/getAllComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
            @ApiImplicitParam(name="id",value="热点id"),
    })
    public ResultVo all(int id,int current,int size,int sort){
        log.info("查询所有评论");
        Page<Comment> page = new Page<>(current , size );
        LambdaQueryWrapper<Comment> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Comment::getId,id).eq(Comment::getSort,sort);
        return new ResultVo().setCode(200).setData(iCommentService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加评论")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="context",value="评论内容"),
            @ApiImplicitParam(name="userId",value="评论者id"),
            @ApiImplicitParam(name="id",value="id"),
            @ApiImplicitParam(name="sort",value="评论的类型：0为热点,1为庭审"),
    })
    public ResultVo insert(int id,int userId,String context,int sort){
        log.info("增加评论");
        return new ResultVo().setCode(200).setData(iCommentService.insert(id,userId,context,sort));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("删除评论")
    @DeleteMapping("/remove")
    @ApiImplicitParam(name="commentId",value="评论id")
    public ResultVo remove(int commentId){
        log.info("删除评论");
        return new ResultVo().setCode(200).setData(iCommentService.remove(commentId));
    }


}
