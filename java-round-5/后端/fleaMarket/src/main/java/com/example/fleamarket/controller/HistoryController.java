package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.History;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IHistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value ="/history")
public class HistoryController {
    @Autowired
    IHistoryService iHistoryService;
    @RequiresRoles("user::user")
    @ApiOperation("查询所有历史")
    @GetMapping("/getAllHistory")
    public ResultVo all(int userId, @ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有历史");
        Page<History> page = new Page<>(current,size);
        LambdaQueryWrapper<History> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(History::getUserId,userId);
        return new ResultVo().setData(iHistoryService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加历史")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("用户id")int userId,@ApiParam("商品id")int goodId){
        log.info("增加历史");
        if(iHistoryService.find(userId,goodId)){
            remove(userId,goodId);
        }
        return new ResultVo().setData(iHistoryService.insert(userId,goodId));
    }
    @ApiOperation("删除历史")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("用户id")int userId,@ApiParam("商品id")int goodId){
        log.info("删除历史");
        if(iHistoryService.remove(userId, goodId)){
            return new ResultVo().setCode(200);
        }
        else throw new ControllerException("Id不存在");
    }
}
