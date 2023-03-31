package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Announcement;
import com.example.fleamarket.entity.GoodReport;
import com.example.fleamarket.entity.Goods;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IGoodReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value ="/goodReport")
public class GoodReportController {
    @Autowired
    IGoodReportService iGoodReportService;
    @RequiresRoles("admin")
    @ApiOperation("查询所有商品举报记录")
    @GetMapping("/getAllGoodReport")
    public ResultVo all(@ApiParam("当前页")int current, @ApiParam("大小") int size){
        Page<GoodReport> page = new Page<>(current , size );
        LambdaQueryWrapper<GoodReport> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(GoodReport::getStatus, "未审核");
        return new ResultVo().setData(iGoodReportService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加举报记录")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("内容")String context,int goodId){
        return new ResultVo().setData(iGoodReportService.insert(context,goodId));
    }
    @RequiresRoles("admin")
    @ApiOperation("修改审核状态")
    @GetMapping("/update")
    public ResultVo update(String status,int reportId){
        return new ResultVo().setData(iGoodReportService.update(status,reportId));
    }

}
