package com.example.hotSpot.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotSpot.entity.Message;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IMessageService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {
    @Autowired
    WebSocketServer webSocketServer;
    @Autowired
    IMessageService iMessageService;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @ApiOperation("发送文本消息接口")
    @GetMapping("/insertReplyWithoutPic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "spotId", value = "热点Id"),
            @ApiImplicitParam(name = "content", value = "消息内容"),
    })
    public ResultVo insert1(int userId, int spotId, String content) {
        log.info("文本消息接口");
        Message msg = new Message();
        msg.setSpotId(spotId);
        msg.setContent(content);
        msg.setCategory(1);
        LocalDate localDate = LocalDate.now();
        String now = String.valueOf(localDate);
        msg.setDate(now);
        msg.setUserId(userId);
        iMessageService.insertMessage(userId, spotId, content,1);
        webSocketServer.sendAllMessage(JSON.toJSONString(msg));
        return new ResultVo().setCode(200);
    }

    @ApiOperation("发送图片消息接口")
    @PostMapping("/insertReplyWithPic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "spotId", value = "热点Id"),
            @ApiImplicitParam(name = "file", value = "图片文件"),
    })
    public ResultVo insert2(int userId, int spotId, MultipartFile file) {
        log.info("图片消息接口");
        Message msg = new Message();
        String content = aliyunOSSUtil.upload(file);
        msg.setSpotId(spotId);
        msg.setContent(content);
        msg.setCategory(2);
        msg.setUserId(userId);
        LocalDate localDate = LocalDate.now();
        String now = String.valueOf(localDate);
        iMessageService.insertMessage(userId, spotId, content,2);
        webSocketServer.sendAllMessage(JSON.toJSONString(msg));
        return new ResultVo().setCode(200);
    }
}
       