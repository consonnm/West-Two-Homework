package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Announcement;
import com.example.fleamarket.entity.Audit;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IAuditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @RequiresRoles("admin")
    @ApiOperation("查询所有审核记录")
    @GetMapping("/getAllAudit")
    public ResultVo all(@ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有审核记录");
        Page<Audit> page = new Page<>(current ,size );
        return new ResultVo().setData(iAuditService.findByPage(page,null));
    }
    @RequiresRoles("admin")
    @ApiOperation("增加审核记录")
    @GetMapping("/insert")
    public ResultVo insert(@Valid @RequestBody Audit audit, BindingResult bindingResult){
        log.info("增加审核记录");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                throw new ControllerException(e.getDefaultMessage());
            });
        }
        return new ResultVo().setData(iAuditService.insert(audit.getStatus(),audit.getGoodId(),audit.getAdminId()));
    }
}
