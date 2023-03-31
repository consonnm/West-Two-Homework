package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Audit;
import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IAuditService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value ="/audit")
public class AuditController {
    @Autowired
    IAuditService iAuditService;
    //@RequiresRoles("admin")
    @ApiOperation("查询所有审核记录")
    @GetMapping("/getAllAudit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size,int sort){
        log.info("查询所有审核记录");
        Page<Audit> page = new Page<>(current , size );
        LambdaQueryWrapper<Audit> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Audit::getSort,sort);
        return new ResultVo().setCode(200).setData(iAuditService.findByPage(page,userLambdaQueryWrapper));
    }
    //@RequiresRoles("admin")
    @ApiOperation("增加审核记录")
    @PostMapping ("/insert")
    public ResultVo insert(@Valid @RequestBody Audit audit, BindingResult bindingResult){
        log.info("增加审核记录");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                throw new ControllerException(e.getDefaultMessage());
            });
        }
        return new ResultVo().setCode(200).setData(iAuditService.insert(audit.getStatus(),audit.getId(),audit.getAdminId()));
    }
}
