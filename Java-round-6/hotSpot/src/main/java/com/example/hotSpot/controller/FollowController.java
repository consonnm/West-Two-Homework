package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Follow;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IFollowService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value ="/follow")
public class FollowController {
    @Autowired
    IFollowService iFollowService;
    @ApiOperation("查询所有关注")
    @GetMapping("/getAllFollow")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo all(int userId,int current,int size){
        log.info("查询所有关注");
        Page<Follow> page = new Page<>(current , size );
        LambdaQueryWrapper<Follow> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Follow::getFollowerId,userId);
        return new ResultVo().setCode(200).setData(iFollowService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加关注")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="followerId",value="关注者id"),
            @ApiImplicitParam(name="followedId",value="被关注者id"),
    })
    public ResultVo insert(int followerId,int followedId){
        log.info("查询所有关注");
        return new ResultVo().setCode(200).setData(iFollowService.insert(followerId, followedId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("删除关注")
    @DeleteMapping("/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name="followerId",value="关注者id"),
            @ApiImplicitParam(name="followedId",value="被关注者id"),
    })
    public ResultVo remove(int followerId,int followedId){
        log.info("查询所有关注");

        return new ResultVo().setCode(200).setData(iFollowService.remove(followerId,followedId));
    }
}
