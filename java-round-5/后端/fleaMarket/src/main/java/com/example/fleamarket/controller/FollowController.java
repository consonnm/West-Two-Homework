package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Comment;
import com.example.fleamarket.entity.Follow;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IFollowService;
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
@RequestMapping(value ="/follow")
public class FollowController {
    @Autowired
    IFollowService iFollowService;
    @ApiOperation("查询所有关注")
    @GetMapping("/getAllFollow")
    public ResultVo all(int userId, @ApiParam("当前页")int current, @ApiParam("大小")int size){
        log.info("查询所有关注");
        Page<Follow> page = new Page<>(current , size );
        LambdaQueryWrapper<Follow> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Follow::getFollowerId,userId);
        return new ResultVo().setData(iFollowService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加关注")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("关注者id")int followerId,@ApiParam("被关注者id")int followedId){
        log.info("查询所有关注");
        return new ResultVo().setData(iFollowService.insert(followerId, followedId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("删除关注")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("关注者id")int followerId,@ApiParam("被关注者id")int followedId){
        log.info("查询所有关注");

        return new ResultVo().setData(iFollowService.remove(followerId,followedId));
    }
}
