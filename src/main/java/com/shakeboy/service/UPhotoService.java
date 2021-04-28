package com.shakeboy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakeboy.mapper.UPhotoMapper;
import com.shakeboy.mapper.UserMapper;
import com.shakeboy.pojo.UPhoto;
import com.shakeboy.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UPhotoService {
    @Autowired
    private UPhotoMapper uPhotoMapper;
    @Autowired
    private UserMapper userMapper;
    // 根据用户id获取所有历史图片还有时间
    public List<UPhoto> getAllPhoto(int user_id){
        QueryWrapper<UPhoto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return uPhotoMapper.selectList(queryWrapper);
    }
    // 使用历史图片
    public Boolean toUse(int uphoto_id,String uphoto_address,int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",user_id);
        User user = new User();
        user.setUserId(user_id);
        user.setUserAvator(uphoto_address);
        return userMapper.update(user,userQueryWrapper)!=0;
    }
    // 删除历史图片
    public Boolean toDelete(int uphoto_id){
        QueryWrapper<UPhoto> uPhotoQueryWrapper = new QueryWrapper<>();
        uPhotoQueryWrapper.eq("uphoto_id",uphoto_id);
        return uPhotoMapper.delete(uPhotoQueryWrapper)!=0;
    }
}
