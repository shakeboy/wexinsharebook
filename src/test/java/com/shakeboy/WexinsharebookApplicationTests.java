package com.shakeboy;

import com.shakeboy.mapper.UserMapper;
import com.shakeboy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WexinsharebookApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        //参数是一个wrapper：条件构造器  可以为null
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
