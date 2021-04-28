package com.shakeboy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("books")
public class Book {
    @TableId(type = IdType.AUTO)
    private int bookId;
    private String bookName;
    private String bookDescription;
    private String bookImage;
    private String bookContent;
    private String bookType;
    private String bookAuthor;
}
