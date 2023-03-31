package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.SpotReport;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotReportService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value ="/goodReport")
public class SpotReportController {
    @Autowired
    ISpotReportService iSpotReportService;
    //@RequiresRoles("admin")
    @ApiOperation("查询所有热点举报记录")
    @GetMapping("/getAllGoodReport")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size){
        Page<SpotReport> page = new Page<>(current , size );
        LambdaQueryWrapper<SpotReport> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(SpotReport::getStatus, "未审核");
        return new ResultVo().setCode(200).setData(iSpotReportService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加热点举报记录")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "context",value = "内容"),
            @ApiImplicitParam(name="id",value="热点id"),
    })
    public ResultVo insert(String context,int id){
        return new ResultVo().setCode(200).setData(iSpotReportService.insert(context,id));
    }
    //@RequiresRoles("admin")
    @ApiOperation("修改热点审核状态")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "状态"),
            @ApiImplicitParam(name="reportId",value="举报id"),
    })
    public ResultVo update(String status,int reportId){
        return new ResultVo().setCode(200).setData(iSpotReportService.update(status,reportId));
    }

}
