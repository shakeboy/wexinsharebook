package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shakeboy.pojo.UBook;
import com.shakeboy.service.UBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/ubook")
public class UBookController {
    @Autowired
    private UBookService uBookService;
    // 查询所有用户数据信息
    @RequestMapping("/getAllUBook")
    public String getAllUBook(){
        List<UBook> uBooks = uBookService.getAllUBook();
        return JSON.toJSONString(uBooks);
    }
    // 通过关键字模糊查询并渲染
    @RequestMapping("/getAllUBookByKeyValue")
    public String getAllUBookByKeyValue(@PathParam("keyValue")String keyValue){
        List<UBook> uBooks = uBookService.getAllUBookByKeyValue(keyValue);
        return JSON.toJSONString(uBooks);
    }
    // 点赞功能
    @PostMapping("/ubookGood")
    public String ubookGood(@PathParam("ubook_id")int ubook_id,@PathParam("ubook_good")int ubook_good,@PathParam("ubook")String ubook){
        System.out.println(ubook);
        // 将jsonString转化称json对象
        JSONObject jsonObject = JSONObject.parseObject(ubook);
        System.out.println(jsonObject.get("ubook_id"));
        System.out.println(ubook_id+"sss"+ubook_good);
        return uBookService.ubookGood(ubook_id,ubook_good,jsonObject).toString();
    }
    // 转发功能
    @PostMapping("/ubookTransfer")
    public String ubookTransfer(@PathParam("ubook_id")int ubook_id,@PathParam("ubook_good")int ubook_good,@PathParam("ubook")String ubook){
        System.out.println(ubook);
        // 将jsonString转化称json对象
        JSONObject jsonObject = JSONObject.parseObject(ubook);
        System.out.println(jsonObject.get("ubook_id"));
        System.out.println(ubook_id+"sss"+ubook_good);
        return uBookService.ubookTransfer(ubook_id,ubook_good,jsonObject).toString();
    }
    // 评论功能
    @PostMapping("/ubookComment")
    public String ubookComment(@PathParam("ubook_id")int ubook_id,@PathParam("ubook_good")int ubook_good,@PathParam("ubook")String ubook){
        System.out.println(ubook);
        // 将jsonString转化称json对象
        JSONObject jsonObject = JSONObject.parseObject(ubook);
        System.out.println(jsonObject.get("ubook_id"));
        System.out.println(ubook_id+"sss"+ubook_good);
        return uBookService.ubookComment(ubook_id,ubook_good,jsonObject).toString();
    }
    // 发表功能
    @PostMapping("/writeUBook")
    public String writeUBook(@PathParam("ubook")String ubook,@PathParam("user_id")int user_id){
        JSONObject jsonObject = JSONObject.parseObject(ubook);
        System.out.println(jsonObject);
        return uBookService.writeUBook(jsonObject,user_id).toString();
    }
}
