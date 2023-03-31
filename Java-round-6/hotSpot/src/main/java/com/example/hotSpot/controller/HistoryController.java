package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.History;
import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IHistoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value ="/history")
public class HistoryController {
    @Autowired
    IHistoryService iHistoryService;
    @RequiresRoles("user::user")
    @ApiOperation("查询所有历史")
    @GetMapping("/getAllHistory")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo all(int userId,int current,int size){
        log.info("查询所有历史");
        Page<History> page = new Page<>(current,size);
        LambdaQueryWrapper<History> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(History::getUserId,userId);
        return new ResultVo().setCode(200).setData(iHistoryService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加历史")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点Id"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo insert(int userId,int id){
        log.info("增加历史");
        //如果历史存在就删除并更新
        if(iHistoryService.find(userId,id)){
            remove(userId,id);
        }
        return new ResultVo().setCode(200).setData(iHistoryService.insert(userId,id));
    }
    @ApiOperation("删除历史")
    @DeleteMapping("/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点Id"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo remove(int userId,int id){
        log.info("删除历史");
        if(iHistoryService.remove(userId, id)){
            return new ResultVo().setCode(200);
        }
        else throw new ControllerException("Id不存在");
    }
}
