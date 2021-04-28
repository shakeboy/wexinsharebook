package com.shakeboy.controller;

import com.alibaba.fastjson.JSON;
import com.shakeboy.pojo.Book;
import com.shakeboy.pojo.Star;
import com.shakeboy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    // 获取所有书籍，最后渲染十本
    @RequestMapping("/getAllBooks")
    public String getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return JSON.toJSONString(bookList);
    }
    // 关键字搜索书籍
    @RequestMapping("/searchByBookName")
    public String searchByBookName(@PathParam("value")String value){
        List<Book> books = bookService.searchByBookName(value);
        return JSON.toJSONString(books);
    }
    // 关键字搜索书籍
    @RequestMapping("/search")
    public String search(@PathParam("value")String value){
        List<Book> books = bookService.search(value);
        return JSON.toJSONString(books);
    }
    // 通过种类搜索
    @RequestMapping("/searchByType")
    public String searchByType(@PathParam("book_type") String book_type){
        List<Book> books = bookService.searchByType(book_type);
        return JSON.toJSONString(books);

    }
    // 收藏star
    @PostMapping("/star")
    public String star(@PathParam("user_id")int user_id,@PathParam("book_id")int book_id){
        System.out.println(user_id+"==="+book_id);
        return bookService.star(user_id,book_id).toString();
    }
    // 收藏star
    @PostMapping("/cancelStar")
    public String cancelStar(@PathParam("user_id")int user_id,@PathParam("book_id")int book_id){
        System.out.println(user_id+"==="+book_id);
        return bookService.cancelStar(user_id,book_id).toString();
    }
    // getStarId
    @RequestMapping("/getStarId")
    public String getStarId(@PathParam("user_id")int user_id){
        List<Star> stars = bookService.getStarId(user_id);
        return JSON.toJSONString(stars);
    }
    //getBookById
    @RequestMapping("/getBookById")
    public String getBookById(@PathParam("book_id")int book_id){
        Book book = bookService.getBookById(book_id);
        return JSON.toJSONString(book);
    }
}
