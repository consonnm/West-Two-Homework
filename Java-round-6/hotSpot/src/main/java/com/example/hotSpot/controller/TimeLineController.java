package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.TimeLine;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ITimeLineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/time")
public class TimeLineController {
    @Autowired
    ITimeLineService iTimeLineService;
    //@RequiresRoles("user::user")
    @ApiOperation("热点时间线查询接口")
    @GetMapping("/time")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "热点Id"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo time(int id, int current, int size) {
        log.info("热点时间线查询接口");
        Page<TimeLine> page = new Page<>(current,size);
        LambdaQueryWrapper<TimeLine> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(TimeLine::getSpotId,id);
        return new ResultVo().setCode(200).setData(iTimeLineService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("用户删除时间线")
    @DeleteMapping("/cancel")
    @ApiImplicitParam(name = "timeId",value = "时间Id")
    public ResultVo cancel(int timeId) {
        log.info("用户删除时间线");
        return new ResultVo().setCode(200).setData(iTimeLineService.remove(timeId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("用户修改时间线接口")
    @PutMapping("/baseUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "热点Id"),
            @ApiImplicitParam(name="time_now",value="时间2022-04-05的格式"),
            @ApiImplicitParam(name="describe",value="描述"),
            @ApiImplicitParam(name="timeId",value="时间id"),
    })
    public ResultVo baseUpdate(int id,String time,String describe,int timeId) {
        log.info("用户修改时间线接口");
        return new ResultVo().setCode(200).setData(iTimeLineService.baseUpdate(id,time,describe,timeId));
    }
    @ApiOperation("增加时间线接口")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "热点Id"),
            @ApiImplicitParam(name="time",value="时间2022-04-05的格式"),
            @ApiImplicitParam(name="describe",value="描述"),
    })
    public ResultVo insert(int id,String time,String describe) {
        log.info("用户添加时间线接口");
        return new ResultVo().setCode(200).setData(iTimeLineService.insert(id,time,describe));
    }

}
