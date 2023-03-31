package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Spot;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value ="/spot")
public class SpotController {
    @Autowired
    ISpotService iSpotService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("模糊查询热点")
    @GetMapping("/getGood")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotName",value="热点名称"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo find(String spotName,int current,int size) {
        log.info("模糊查询热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Spot::getSpotName, spotName).eq(Spot::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("查询所有热点")
    @GetMapping("/getAllGood")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size) {
        log.info("查询所有热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据类别查询热点")
    @GetMapping("/getGoodBySort")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotSort",value="热点类别"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo sort(String spotSort,int current,int size) {
        log.info("根据类别查询热点");
        Page<Spot> page = new Page<>(current,size);
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getSpotSort, spotSort).eq(Spot::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据类别及关键字查询热点")
    @GetMapping("/getGoodBySortByKey")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotSort",value="热点类别"),
            @ApiImplicitParam(name="spotName",value="热点关键字"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo sortByKey(String spotSort,String spotName,int current,int size) {
        log.info("根据类别及关键字查询热点");
        Page<Spot> page = new Page<>(current,size);
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getSpotSort, spotSort).like(Spot::getSpotName,spotName).eq(Spot::getApproved,"已审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据id查询热点的详细信息")
    @GetMapping("/getGoodById")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点id"),
    })
    public ResultVo get(int id) {
        log.info("根据id查询热点的详细信息");
        return new ResultVo().setCode(200).setData(iSpotService.queryById(id));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("热点基础信息修改")
    @PutMapping("/baseMessageUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotName",value="热点名称"),
            @ApiImplicitParam(name="summary",value="热点概述"),
            @ApiImplicitParam(name="detail",value="热点详细介绍"),
            @ApiImplicitParam(name="spotSort",value="分类"),
            @ApiImplicitParam(name="id",value="热点id"),
    })
    public ResultVo baseUpdate(String spotName,String summary,String detail ,String spotSort,int id) {
        log.info("热点基础信息修改");
        return new ResultVo().setCode(200).setData(iSpotService.update(spotName, summary, detail, spotSort,id));
    }
    //@RequiresRoles("admin")
    @ApiOperation("查询所有未审核热点")
    @GetMapping("/select")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo select(int current,int size) {
        log.info("查询所有未审核热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getApproved, "未审核");
        return new ResultVo().setCode(200).setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("admin")
    @ApiOperation("热点审核状态修改接口")
    @PutMapping("/approved")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点id"),
            @ApiImplicitParam(name="approved",value="审核状态"),
    })
    public ResultVo approved(String approved,int id) {
        log.info("热点审核状态修改接口");
        return new ResultVo().setCode(200).setData(iSpotService.approved(approved,id));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("热点图片修改接口")
    @PostMapping("/photoUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点id"),
            @ApiImplicitParam(name="file",value="图片"),
    })
    public ResultVo photoUpdate(MultipartFile file, int id) {
        log.info("热点图片修改接口");
        return new ResultVo().setCode(200).setData(iSpotService.updatePhoto(file, id));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加热点接口")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotName",value="热点名称"),
            @ApiImplicitParam(name="summary",value="热点概述"),
            @ApiImplicitParam(name="detail",value="热点详细介绍"),
            @ApiImplicitParam(name="spotSort",value="分类"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo insert(String spotName, String summary, String detail, String spotSort, int userId){
        log.info("增加热点接口");
        try {
            iSpotService.insert(spotName,summary,detail,spotSort,userId);
            long hour=System.currentTimeMillis()/(1000*60*60);
            this.redisTemplate.opsForZSet().incrementScore("HOUR"+hour,spotName,0);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo().setCode(400).setMessage("分类不存在或，用户不存在");
        }
        return new ResultVo().setCode(200);
    }
    //@RequiresRoles("user::user")
    @ApiOperation("删除热点接口")
    @DeleteMapping("/remove")
    @ApiImplicitParam(name="id",value="热点id")
    public ResultVo remove(int id) {
        log.info("删除热点接口");
        return new ResultVo().setCode(200).setData(iSpotService.remove(id));
    }
    @ApiOperation("获取小时排行榜")
    @GetMapping ("/getHour")
    public ResultVo getHour() {
        long hour=System.currentTimeMillis()/(1000*60*60);
        Set<ZSetOperations.TypedTuple<Integer>> rang= this.redisTemplate.opsForZSet().reverseRangeWithScores("HOUR"+hour,0,20);
        return new ResultVo().setCode(200).setData(rang);
    }
    @ApiOperation("获取天排行榜")
    @GetMapping(value = "/getDay")
    public Set getDay() {
        Set<ZSetOperations.TypedTuple<Integer>> rang= this.redisTemplate.opsForZSet().reverseRangeWithScores("DAY",0,20);
        return rang;
    }
    @ApiOperation("获取周排行榜")
    @GetMapping(value = "/getWeek")
    public Set getWeek() {
        Set<ZSetOperations.TypedTuple<Integer>> rang= this.redisTemplate.opsForZSet().reverseRangeWithScores("WEEK",0,20);
        return rang;
    }


}
