package com.shakeboy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakeboy.mapper.BookMapper;
import com.shakeboy.mapper.StarMapper;
import com.shakeboy.pojo.Book;
import com.shakeboy.pojo.Message;
import com.shakeboy.pojo.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private StarMapper starMapper;
    // 获取推荐书籍
    // 获取前十本书进行推荐
    public List<Book> getAllBooks(){
        return bookMapper.selectList(null);
    }
    // 关键字搜索(根据书名来搜索)
    public List<Book> searchByBookName(String value){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name",value);
        return bookMapper.selectList(queryWrapper);
    }
    // 关键字搜索(全局)
    public List<Book> search(String value){
        return bookMapper.searchByKeyvalue(value);
    }
    // 通过种类搜索
    public List<Book> searchByType(String book_type){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_type",book_type);
        return bookMapper.selectList(queryWrapper);
    }
    // 收藏star
    public Boolean star(int user_id,int book_id){
        QueryWrapper<Star> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        queryWrapper.eq("book_id",book_id);
        Star star = new Star();
        star.setBookId(book_id);
        star.setUserId(user_id);
        // 先判断有没有收藏过
        if(starMapper.selectOne(queryWrapper)==null){
            return starMapper.insert(star)!=0;
        }else {
            return false;
        }
    }
    // 取消star
    public Boolean cancelStar(int user_id,int book_id){
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user_id);
        wrapper.eq("book_id",book_id);
        // 先判断有没有收藏过
        if(starMapper.selectOne(wrapper)==null){
            return false;
        }else {
            return starMapper.delete(wrapper)!=0;
        }
    }
    // 获取用户收藏地书籍id
    public List<Star> getStarId(int user_id){
        QueryWrapper<Star> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return starMapper.selectList(queryWrapper);
    }
    // getBookById
    public Book getBookById(int book_id){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id",book_id);
        return bookMapper.selectOne(queryWrapper);
    }


}
