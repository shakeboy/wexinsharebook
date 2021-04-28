package com.shakeboy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.shakeboy.mapper.FriendMapper;
import com.shakeboy.mapper.SignatureMapper;
import com.shakeboy.mapper.UPhotoMapper;
import com.shakeboy.mapper.UserMapper;
import com.shakeboy.pojo.Friend;
import com.shakeboy.pojo.Signature;
import com.shakeboy.pojo.UPhoto;
import com.shakeboy.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SignatureMapper signatureMapper;
    @Autowired
    private UPhotoMapper uPhotoMapper;
    @Autowired
    private FriendMapper friendMapper;
    // 获取所有用户
    public List<User> getAllUser(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
        System.out.println("hello");
        return users;
    }
    // 登陆验证
    public User userLogin(String username,String password){
        HashMap<String,Object> map=new HashMap<>();
        map.put("user_account",username);
        map.put("user_password",password);
        List<User> userList = userMapper.selectByMap(map);
        if (userList.isEmpty()){
            return null;
        }
        System.out.println(userList);
        return userList.get(0);
    }
    // 注册功能
    public Boolean userRegister(User user){
        int i = userMapper.insert(user);
        return i!=0;
    }
    // 用户修改个性签名
    public Boolean editSignature(int user_id,String signature_content){
        // 更新当前的个性签名
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        User user=new User();
        user.setUserId(user_id);
        user.setUserSignature(signature_content);
        userMapper.update(user,queryWrapper);
        // 记录该用户的个性签名
        Signature signature = new Signature();
        signature.setSignatureContent(signature_content);
        signature.setUserId(user_id);
        int i = signatureMapper.insert(signature);
        return i!=0;
    }
    // 获取对应用户历史签名
    public List<Signature> getSignature(int user_id){
        QueryWrapper<Signature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return signatureMapper.selectList(queryWrapper);
    }
    // 根据获取的签名id删除个性签名
    public Boolean deleteSignature(int signature_id){
        QueryWrapper<Signature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("signature_id",signature_id);
        // 查询该对象，获取用户id
        Signature signature = signatureMapper.selectOne(queryWrapper);
        int userId = signature.getUserId();
        // 根据用户id滞空数据库的个性签名字段
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",userId);
        User user = new User();
        user.setUserSignature("");
        user.setUserId(userId);
        userMapper.update(user,userQueryWrapper);
        // 删除个性签名表数据
        int i = signatureMapper.delete(queryWrapper);
        return i!=0;
    }
    // 保存图片地址到数据库中
    public Boolean storageImgAddress(int user_id,String user_avator){
        // 插入uphotos表中
        UPhoto photo = new UPhoto();
        photo.setUphotoName(UUID.randomUUID().toString());
        photo.setUserId(user_id);
        photo.setUphotoAddress(user_avator);
        uPhotoMapper.insert(photo);
        // 保存图片地址到数据库中
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        User user = new User();
        user.setUserAvator(user_avator);
        user.setUserId(user_id);
        return userMapper.update(user, queryWrapper)!=0;
    }
    // 获取基本信息
    public User getAUserById(int user_id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return userMapper.selectOne(queryWrapper);
    }
    // 根据用户id修改密码和名称
    public Boolean updateUser(int user_id,String user_name,String user_password){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",user_id);
        User user= new User();
        System.out.println(user_name);
        System.out.println(user_password);
        if(!user_name.equals("")){
            System.out.println("用户名不为空");
            user.setUserName(user_name);
        }
        if(!user_password.equals("")){
            System.out.println("密码不为空");
            user.setUserPassword(user_password);
        }
        user.setUserId(user_id);
        return userMapper.update(user,userQueryWrapper)!=0;
    }
    // 获取所有好友id
    public List<Friend> getFriendList(int user_id) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_ido", user_id);
        List<Friend> friends = friendMapper.selectList(queryWrapper);
        QueryWrapper<Friend> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_idt", user_id);
        List<Friend> friends1 = friendMapper.selectList(queryWrapper1);
        friends.addAll(friends1);
        return friends;
    }
}
