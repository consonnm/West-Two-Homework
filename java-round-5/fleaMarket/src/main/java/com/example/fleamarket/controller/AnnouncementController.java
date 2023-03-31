package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Announcement;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IAnnouncementService;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping(value ="/announcement")
public class AnnouncementController {
    @Autowired
    IAnnouncementService iAnnouncementService;
    @ApiOperation("查询所有公告")
    @GetMapping("/getAllAnnouncement")
    public ResultVo all(@ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有公告");
        Page<Announcement> page = new Page<>(current, size );
        return new ResultVo().setData(iAnnouncementService.findByPage(page,null));
    }



    @RequiresRoles("admin")
    @ApiOperation("增加公告")
    @PostMapping("/insert")
    public ResultVo insert(@Valid @RequestBody Announcement announcement, BindingResult bindingResult) {

        log.info("增加公告");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                throw new ControllerException(e.getDefaultMessage());
            });
        }
            return new ResultVo().setData(iAnnouncementService.insert(announcement.getContext(), announcement.getTopic(), announcement.getTime()));

    }
    @RequiresRoles("admin")
    @ApiOperation("删除公告")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("公告id")int announcementId){
        log.info("删除公告");
        if(iAnnouncementService.remove(announcementId)==true){
        return new ResultVo().setCode(200);}
        else throw new ControllerException("此公告不存在");
    }
}
