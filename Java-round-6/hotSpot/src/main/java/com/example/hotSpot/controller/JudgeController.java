package com.example.hotSpot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Judge;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IJudgeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value ="/judge")
public class JudgeController {
    @Autowired
    IJudgeService iJudgeService;
    @ApiOperation("模糊查询庭审")
    @GetMapping("/getGood")
    @ApiImplicitParams({
            @ApiImplicitParam(name="judgeName",value="庭审名称"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo list(String judgeName,int current,int size) {
        log.info("模糊查询庭审");
        Page<Judge> page = new Page<>(current , size );
        LambdaQueryWrapper<Judge> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Judge::getJudgeName, judgeName).eq(Judge::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iJudgeService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("查询所有庭审")
    @GetMapping("/getAllGood")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size) {
        log.info("查询所有庭审");
        Page<Judge> page = new Page<>(current , size );
        LambdaQueryWrapper<Judge> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Judge::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iJudgeService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据热点查询庭审")
    @GetMapping("/getGoodBySort")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="热点"),
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo sort(int id,int current,int size) {
        log.info("根据热点查询庭审");
        Page<Judge> page = new Page<>(current,size);
        LambdaQueryWrapper<Judge> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Judge::getSpotId, id).eq(Judge::getApproved,"已审核");
        return new ResultVo().setCode(200).setData(iJudgeService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据id查询庭审的详细信息")
    @GetMapping("/getGoodById")
    @ApiImplicitParam(name="judgeId",value="庭审id")
    public ResultVo list(int judgeId) {
        log.info("根据id查询庭审的详细信息");
        return new ResultVo().setCode(200).setData(iJudgeService.queryById(judgeId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("庭审基础信息修改")
    @PutMapping("/baseMessageUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotName",value="庭审名称"),
            @ApiImplicitParam(name="summary",value="庭审概述"),
            @ApiImplicitParam(name="detail",value="庭审详细介绍"),
            @ApiImplicitParam(name="judgeId",value="庭审Id"),
            @ApiImplicitParam(name="id",value="热点id"),
    })
    public ResultVo baseUpdate(String spotName,String summary,String detail ,int id,int judgeId) {
        log.info("庭审基础信息修改");
        return new ResultVo().setData(iJudgeService.update(spotName, summary, detail, id,judgeId));
    }
    //@RequiresRoles("admin")
    @ApiOperation("查询所有未审核庭审")
    @GetMapping("/select")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo select(int current,int size) {
        log.info("查询所有未审核庭审");
        Page<Judge> page = new Page<>(current , size );
        LambdaQueryWrapper<Judge> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Judge::getApproved, "未审核");
        return new ResultVo().setCode(200).setData(iJudgeService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("admin")
    @ApiOperation("庭审审核状态修改接口")
    @PutMapping("/approved")
    @ApiImplicitParams({
            @ApiImplicitParam(name="judgeId",value="庭审id"),
            @ApiImplicitParam(name="approved",value="审核状态"),
    })
    public ResultVo approved(String approved,int judgeId) {
        log.info("庭审审核状态修改接口");
        return new ResultVo().setCode(200).setData(iJudgeService.approved(approved,judgeId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("庭审图片修改接口")
    @PostMapping("/photoUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name="judgeId",value="庭审id"),
            @ApiImplicitParam(name="file",value="图片"),
    })
    public ResultVo photoUpdate(MultipartFile file,int judgeId) {
        log.info("庭审图片修改接口");
        return new ResultVo().setCode(200).setData(iJudgeService.updatePhoto(file, judgeId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("增加庭审接口")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name="spotName",value="庭审名称"),
            @ApiImplicitParam(name="summary",value="庭审概述"),
            @ApiImplicitParam(name="detail",value="庭审详细介绍"),
            @ApiImplicitParam(name="id",value="热点Id"),
            @ApiImplicitParam(name="userId",value="用户id"),
    })
    public ResultVo insert(String spotName,String summary,String detail,int id,int userId){
        log.info("增加庭审接口");
        return new ResultVo().setCode(200).setData(iJudgeService.insert(spotName,summary,detail,id,userId));
    }
    //@RequiresRoles("user::user")
    @ApiOperation("删除庭审接口")
    @DeleteMapping("/remove")
    @ApiImplicitParam(name="judgeId",value="庭审id")
    public ResultVo remove(int judgeId) {
        log.info("删除庭审接口");
        return new ResultVo().setCode(200).setData(iJudgeService.remove(judgeId));
    }
}
