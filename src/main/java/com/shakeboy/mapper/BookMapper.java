package com.shakeboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shakeboy.pojo.Book;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface BookMapper extends BaseMapper<Book> {
    List<Book> searchByKeyvalue(@PathParam("keyvalue") String keyvalue);
}
