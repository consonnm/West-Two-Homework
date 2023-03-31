package com.example.hotSpot.controller;


import com.alibaba.fastjson.JSON;
import com.example.hotSpot.entity.Message;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint("/websocket/{userId}/{spotId}")
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    public static final Map<Integer, Session> sessionMap = new ConcurrentHashMap<>();
    public static final Map<Session, Integer> map = new ConcurrentHashMap<>();
    @ApiOperation("聊天内容查询接口")
    //建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") int userId, @PathParam("spotId") int spotId) {
        sessionMap.put(userId, session);
        map.put(session,spotId);
        onlineCount++;
        log.info("当前连接数："+onlineCount);
        Message msg=new Message();
        msg.setNumber(onlineCount);
        msg.setSpotId(spotId);
        sendAllMessage(JSON.toJSONString(msg));
    }

    //关闭连接
    @OnClose
    public void onClose(@PathParam("userId") int  userId,@PathParam("spotId") int spotId) {
        sessionMap.remove(userId);
        map.remove(spotId);
        onlineCount--;
        log.info("当前连接数："+onlineCount);
        Message msg=new Message();
        msg.setNumber(onlineCount);
        msg.setSpotId(spotId);
        sendAllMessage(JSON.toJSONString(msg));
    }

    @OnMessage
    public void onMessage(String message) {
        //Message msg = JSON.parseObject(message, Message.class);
        sendAllMessage(message);
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    //   服务端发送消息给所有客户端
    public void sendAllMessage(String message){
        try {
            Message msg = JSON.parseObject(message, Message.class);
            for (Session session : sessionMap.values()) {
                if(map.get(session)==msg.getSpotId())
                    session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
