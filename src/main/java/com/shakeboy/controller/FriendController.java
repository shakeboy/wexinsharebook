package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.shakeboy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    // addRequest 发送请求加好友
    @RequestMapping("/addRequest")
    private String addRequest(@PathParam("user_ido")int user_ido,@PathParam("user_idt")int user_idt,@PathParam("message_content")String message_content){
        return friendService.addRequest(user_ido,user_idt,message_content).toString();
    }
    //add friend
    @RequestMapping("/add")
    public String add(@PathParam("user_ido")int user_ido,@PathParam("user_idt")int user_idt){
        return friendService.add(user_ido,user_idt).toString();
    }
    @RequestMapping("/addNo")
    public String addNo(@PathParam("user_ido")int user_ido,@PathParam("user_idt")int user_idt){
        return friendService.addNo(user_ido,user_idt).toString();
    }
    //getMessageById
    @RequestMapping("/getMessageById")
    public String getMessageById(@PathParam("user_idt")int user_idt){
        return JSON.toJSONString(friendService.getMessageById(user_idt));
    }
}
