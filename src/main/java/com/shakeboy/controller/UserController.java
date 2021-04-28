package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.shakeboy.pojo.Signature;
import com.shakeboy.pojo.User;
import com.shakeboy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    // 获取所有用户
    @RequestMapping("/getAllUser")
    public String getAllUser(){
        return userService.getAllUser().toString();
    }
    // 用户登录，接受前端的username和password
    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("username")String username,@RequestParam("password")String password){
        System.out.println(username);
        System.out.println(password);
        User user = userService.userLogin(username, password);
        return JSON.toJSONString(user);
    }
    // 用户登录，接受表单数据，并设置相关默认值
    @PostMapping("/userRegister")
    public String userRegister(@RequestParam("user_account")String user_account,@RequestParam("user_password")String user_password){
        User user = new User();
        user.setUserAccount(user_account);
        user.setUserPassword(user_password);
        // 设置默认值
        user.setUserAvator("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=192101978,4077513094&fm=26&gp=0.jpg");
        user.setUserName("微信书城");
        user.setUserRole("common");
        user.setUserSignature("共享书城造就你我");
        return userService.userRegister(user).toString();
    }
    // 用户编辑个性签名会与自己的id绑定
    @RequestMapping("/editSignature")
    public String editSignature(@PathParam("user_id")int user_id,@PathParam("signature_content")String signature_content){
        return userService.editSignature(user_id,signature_content).toString();
    }
    // 获取对于用户的历史签名
    @RequestMapping("/getSignature")
    public String getSignature(@PathParam("user_id")int user_id){
        List<Signature> signatures = userService.getSignature(user_id);
        return JSON.toJSONString(signatures);
    }
    // 根据signature_id删除个性签名
    @RequestMapping("/deleteSignature")
    public String deleteSignature(@PathParam("signature_id")int signature_id){
        return userService.deleteSignature(signature_id).toString();
    }
    // 文件上传
    @PostMapping("/upload")
    public String upload (@RequestParam("file") MultipartFile file,@RequestParam("user_id")int user_id){
        System.out.println(user_id);
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = "C:/Users/27267/Desktop/project/wexinsharebook/src/main/resources/static/uploadFile/";
        // 文件重命名，防止重复
        String fileName_catch = UUID.randomUUID() + fileName;
        fileName = filePath + fileName_catch;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中,返回访问地址
            String address = "http://localhost:8888/uploadFile/"+fileName_catch;
            // 将地址保存到数据库中
            userService.storageImgAddress(user_id,address);
            file.transferTo(dest);
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    // 获取用户基本信息
    @RequestMapping("/getAUserById")
    public String getAUserById(@RequestParam("user_id")int user_id){
        System.out.println(user_id);
        User user = userService.getAUserById(user_id);
        return JSON.toJSONString(user);
    }
    // 根据用户id修改密码和名称
    @RequestMapping("/updateUser")
    public String updateUser(@PathParam("user_id")int user_id,@PathParam("user_name")String
            user_name,@PathParam("user_password")String user_password){
        System.out.println(user_id);
        return userService.updateUser(user_id,user_name,user_password).toString();

    }
    // 获取好友列表
    @RequestMapping("/getFriendList")
    public String getFriendList(@PathParam("user_id")int user_id){
        return JSON.toJSONString(userService.getFriendList(user_id));
    }
}
