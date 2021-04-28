package com.shakeboy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakeboy.mapper.UArticleMapper;
import com.shakeboy.pojo.UArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UArticleService {
    @Autowired
    private UArticleMapper uArticleMapper;
    // 根据用户id查询所有文章
    public List<UArticle> getAllArticleById(int user_id){
        QueryWrapper<UArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return uArticleMapper.selectList(queryWrapper);
    }
    // 用户写文章
    public Boolean writeArticle(int user_id,String uarticle_name,String uarticle_content){
        UArticle article = new UArticle();
        article.setUserId(user_id);
        article.setUarticleName(uarticle_name);
        article.setUarticleContent(uarticle_content);
        int i = uArticleMapper.insert(article);
        return i!=0;
    }
}
