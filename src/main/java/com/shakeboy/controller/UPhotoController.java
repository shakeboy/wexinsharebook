package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.shakeboy.pojo.UPhoto;
import com.shakeboy.service.UPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.javascript.JSObject;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class UPhotoController {
    @Autowired
    private UPhotoService uPhotoService;
    // 根据id获取所有图片和时间
    @RequestMapping("/getAllPhotoById")
    public String getAllPhotoById(@PathParam("user_id")int user_id){
        List<UPhoto> photos = uPhotoService.getAllPhoto(user_id);
        return JSON.toJSONString(photos);
    }

    //使用历史图片
    @PostMapping("/toUse")
    public String toUse(@PathParam("uphoto_id")int uphoto_id,@PathParam("uphoto_address")String uphoto_address,@PathParam("user_id")int user_id){
        return uPhotoService.toUse(uphoto_id,uphoto_address,user_id).toString();
    }
    //使用历史图片
    @PostMapping("/toDelete")
    public String toDelete(@PathParam("uphoto_id")int uphoto_id){
        return uPhotoService.toDelete(uphoto_id).toString();
    }
}
