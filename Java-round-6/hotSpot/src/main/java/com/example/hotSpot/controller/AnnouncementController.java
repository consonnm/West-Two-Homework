package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Announcement;
import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IAnnouncementService;
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
@RequestMapping(value ="/announcement")
public class AnnouncementController {
    @Autowired
    IAnnouncementService iAnnouncementService;
    @ApiOperation("查询所有公告")
    @GetMapping("/getAllAnnouncement")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size){
        log.info("查询所有公告");
        Page<Announcement> page = new Page<>(current, size );
        return new ResultVo().setCode(200).setData(iAnnouncementService.findByPage(page,null));
    }
    //@RequiresRoles("admin")
    @ApiOperation("增加公告")
    @PostMapping("/insert")
    public ResultVo insert(@Valid @RequestBody Announcement announcement, BindingResult bindingResult) {
        log.info("增加公告");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                throw new ControllerException(e.getDefaultMessage());
            });
        }
            return new ResultVo().setCode(200).setData(iAnnouncementService.insert(announcement.getContext(), announcement.getTopic(), announcement.getTime()));

    }
    //@RequiresRoles("admin")
    @ApiOperation("删除公告")
    @DeleteMapping("/remove")
    @ApiImplicitParam(value = "公告id",name = "announcementId")
    public ResultVo remove(int announcementId){
        log.info("删除公告");
        if(iAnnouncementService.remove(announcementId)){
        return new ResultVo().setCode(200);}
        else throw new ControllerException("此公告不存在");
    }
}
