package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fleamarket.entity.Conversation;
import com.example.fleamarket.entity.Posts;
import com.example.fleamarket.entity.User;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IConversationService;
import com.example.fleamarket.service.IMessageService;
import com.example.fleamarket.utils.AliyunOSSUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {

    @ApiOperation("聊天内容查询接口")
    @GetMapping("/getPostsContent")
    public ResultVo queryPostContent(@ApiParam("用户1ID") int user1ID,@ApiParam("用户2ID")int user2ID) {
        log.info("聊天内容查询接口");
        Subject subject = SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserId()!=user1ID||user.getUserId()!=user2ID){
            throw new AuthenticationException();
        }
        Conversation P = iConversationService.queryById(user1ID,user2ID);
        if(P==null){
            this.insert( user1ID, user2ID); //如果没有对话则创建
        }
        P.setMessages(
                iMessageService.queryForMessageList(user1ID,user2ID)
        );
        return new ResultVo().setData(P);

    }

    @ApiOperation("增加对话接口")
    @GetMapping("/insert")
    public ResultVo insert( @ApiParam("用户1ID") int user1ID,@ApiParam("用户2ID")int user2ID) {
        log.info("增加对话接口");
        Subject subject = SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserId()!=user1ID||user.getUserId()!=user2ID){
            throw new AuthenticationException();
        }
        return new ResultVo().setData(iConversationService.insert( user1ID, user2ID));
    }

    @ApiOperation("用户拥有聊天查询接口")
    @GetMapping("/getUserPost")
    public ResultVo query(@ApiParam("用户1ID") int user1ID,@ApiParam("用户2ID")int user2ID) {
        log.info("用户拥有聊天查询接口");
        Subject subject = SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserId()!=user1ID||user.getUserId()!=user2ID){
            throw new AuthenticationException();
        }
        List<Conversation> posts;

        posts = iConversationService.list(
                new LambdaQueryWrapper<Conversation>()
                        .eq(Conversation::getUser1Id, user1ID).eq(Conversation::getUser2Id, user2ID));
        return new ResultVo().setData(posts);

    }

    @Autowired
    IConversationService iConversationService;
    IMessageService iMessageService;
    @ApiOperation("发送文本消息接口")
    @GetMapping("/insertReplyWithoutPic")
    public ResultVo insert1(@ApiParam("用户1ID")int user1Id,@ApiParam("用户2ID")int user2Id,@ApiParam("消息内容")String content) {
        log.info("文本消息接口");
        Subject subject = SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserId()!=user1Id||user.getUserId()!=user2Id){
            throw new AuthenticationException();
        }
        iMessageService.insertTextMessage(user1Id, user2Id, content);
        return new ResultVo().setCode(200);

    }
    @ApiOperation("发送图片消息接口")
    @GetMapping("/insertReplyWithPic")
    public ResultVo insert2(@ApiParam("用户1ID")int user1Id,@ApiParam("用户2ID")int user2Id, @ApiParam("照片") MultipartFile file) {
        log.info("图片消息接口");
        Subject subject = SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        if(user.getUserId()!=user1Id||user.getUserId()!=user2Id){
            throw new AuthenticationException();
        }
        iMessageService.insertPic(user1Id, user2Id, file);
        return new ResultVo().setCode(200);

    }

}
