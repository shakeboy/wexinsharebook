package com.shakeboy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakeboy.mapper.UBookMapper;
import com.shakeboy.pojo.UBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UBookService {
    @Autowired
    private UBookMapper uBookMapper;
    // 随即查询新插入的十条数据
    // 方法：先查出所有数据，然后渲染后十条
    public List<UBook> getAllUBook(){
        return uBookMapper.selectList(null);
    }
    // 通过关键字搜索书籍并渲染
    public List<UBook> getAllUBookByKeyValue(String keyValue){
        QueryWrapper<UBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("ubook_name",keyValue);
        return uBookMapper.selectList(queryWrapper);
    }
    // 用户点赞功能
    public Boolean ubookGood(int ubook_id, int ubook_good, JSONObject jsonObject){
        UBook uBook = new UBook();
        uBook.setUbookId(ubook_id);
        uBook.setUbookGood(ubook_good+1);
        uBook.setUbookComment((Integer) jsonObject.get("ubook_comment"));
        uBook.setUbookTransfer((Integer) jsonObject.get("ubook_transfer"));
        return uBookMapper.updateById(uBook)!=0;
    }
    // 用户转发功能
    public Boolean ubookTransfer(int ubook_id, int ubook_good, JSONObject jsonObject){
        UBook uBook = new UBook();
        uBook.setUbookId(ubook_id);
        uBook.setUbookGood(ubook_good);
        uBook.setUbookComment((Integer) jsonObject.get("ubook_comment"));
        uBook.setUbookTransfer((Integer) jsonObject.get("ubook_transfer")+1);
        return uBookMapper.updateById(uBook)!=0;
    }
    // 用户评论功能
    public Boolean ubookComment(int ubook_id, int ubook_good, JSONObject jsonObject){
        UBook uBook = new UBook();
        uBook.setUbookId(ubook_id);
        uBook.setUbookGood(ubook_good);
        uBook.setUbookComment((Integer) jsonObject.get("ubook_comment")+1);
        uBook.setUbookTransfer((Integer) jsonObject.get("ubook_transfer"));
        return uBookMapper.updateById(uBook)!=0;
    }
    // 用户发表功能
    public Boolean writeUBook(JSONObject jsonObject,int user_id){
        UBook uBook = new UBook();
        uBook.setUbookName((String) jsonObject.get("ubook_name"));
        uBook.setUbookContent((String) jsonObject.get("ubook_content"));
        uBook.setUbookImage((String) jsonObject.get("ubook_address"));
        uBook.setUbookDescription((String) jsonObject.get("ubook_description"));
        uBook.setUserId(user_id);
        return uBookMapper.insert(uBook)!=0;
    }
}
