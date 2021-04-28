package com.shakeboy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakeboy.mapper.FriendMapper;
import com.shakeboy.mapper.MessageMapper;
import com.shakeboy.pojo.Friend;
import com.shakeboy.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private MessageMapper messageMapper;
    // 加好友验证一：发送请求
    public Boolean addRequest(int user_ido,int user_idt, String message_content){
        // 看是否是好友
        QueryWrapper<Friend> friendQueryWrapper = new QueryWrapper<>();
        friendQueryWrapper.eq("user_ido",user_ido);
        friendQueryWrapper.eq("user_idt",user_idt);
        Friend one = friendMapper.selectOne(friendQueryWrapper);
        QueryWrapper<Friend> friendQueryWrapper1 = new QueryWrapper<>();
        friendQueryWrapper1.eq("user_ido",user_idt);
        friendQueryWrapper1.eq("user_idt",user_ido);
        Friend two = friendMapper.selectOne(friendQueryWrapper1);
        if(one==null&&two==null){ //说明数据库没有好友列表
            // 先看数据库是否有请求发出
            QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_ido",user_ido);
            queryWrapper.eq("user_idt",user_idt);
            queryWrapper.eq("message_content",message_content);
            if(messageMapper.selectList(queryWrapper).isEmpty()){
                // 数据库没有请求数据
                Message message = new Message();
                message.setUserIdo(user_ido);
                message.setUserIdt(user_idt);
                message.setUserOt(UUID.randomUUID().toString()); // 自动已生成标识
                message.setMessageContent(message_content);
                return messageMapper.insert(message)!=0;
            }else {
                // 数据库已有该字段，无法请求
                return false;
            }
        }else{
            return false;
        }
    }
    // 加好友验证二：确认请求
    // 加好友，相当于插入数据
    public Boolean add(int user_ido,int user_idt){
        // 添加好友
        Friend friend = new Friend();
        friend.setUserIdo(user_ido);
        friend.setUserIdt(user_idt);
        if(friendMapper.insert(friend)!=0){
            // 删除请求消息,
            QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_ido",user_ido);
            queryWrapper.eq("user_idt",user_idt);
            QueryWrapper<Message> queryWrapper1 = new QueryWrapper<>();
            queryWrapper.eq("user_ido",user_idt);
            queryWrapper.eq("user_idt",user_ido);
            messageMapper.delete(queryWrapper1);
            return true;
        }
        return false;
    }
    public Boolean addNo(int user_ido,int user_idt){
        // 加好友失败，删除请求消息
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_ido",user_ido);
        queryWrapper.eq("user_idt",user_idt);
        QueryWrapper<Message> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("user_ido",user_idt);
        queryWrapper.eq("user_idt",user_ido);
        messageMapper.delete(queryWrapper1);
        return true;
    }
    // 获取请求信息
    public List<Message> getMessageById(int user_idt){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_idt",user_idt);
        queryWrapper.eq("message_content","请求加好友");
        return messageMapper.selectList(queryWrapper);
    }
}
