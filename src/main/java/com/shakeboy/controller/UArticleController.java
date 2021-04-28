package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.shakeboy.pojo.UArticle;
import com.shakeboy.service.UArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/uarticle")
public class UArticleController {
    @Autowired
    private UArticleService uArticleService;

    //根据用户id获取所写文章
    @RequestMapping("/getAllArticleById")
    public String getAllArticleById(@PathParam("user_id")int user_id){
        List<UArticle> articles = uArticleService.getAllArticleById(user_id);
        return JSON.toJSONString(articles);
    }
    // 用户写文章
    @RequestMapping("/writeArticle")
    public String writeArticle(@PathParam("user_id")int user_id,@PathParam("uarticle_name")
            String uarticle_name,@PathParam("uarticle_content")String uarticle_content){
        return uArticleService.writeArticle(user_id,uarticle_name,uarticle_content).toString();
    }
}
