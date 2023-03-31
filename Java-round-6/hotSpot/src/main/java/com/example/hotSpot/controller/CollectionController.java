package com.example.hotSpot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.SpotCollection;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotCollectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value ="/spotCollection")
public class CollectionController {
    @Autowired
    ISpotCollectionService iSpotCollectionService;
    //@RequiresRoles("user::user")
    @ApiOperation("查询所有收藏")
    @GetMapping("/getAllFollow")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo all(int userId,int current,int size){
        log.info("查询所有收藏");
        Page<SpotCollection> page = new Page<>(current , size );
        LambdaQueryWrapper<SpotCollection> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(SpotCollection::getUserId,userId);
        return new ResultVo().setCode(200).setData(iSpotCollectionService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加收藏")
    @PostMapping ("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点Id"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo insert(int userId,int id){
        log.info("增加收藏");
        return new ResultVo().setCode(200).setData(iSpotCollectionService.insert(userId,id));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("删除收藏")
    @DeleteMapping("/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点Id"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo remove(int userId,int id){
        log.info("删除收藏");
        return new ResultVo().setCode(200).setData(iSpotCollectionService.remove(userId,id));
    }
}
