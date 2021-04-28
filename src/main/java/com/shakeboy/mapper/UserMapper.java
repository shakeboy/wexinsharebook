package com.shakeboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shakeboy.pojo.User;
import org.springframework.stereotype.Repository;

// 在对应的mapper上继承基本的类BaseMapper
@Repository  //代表持久层
public interface UserMapper extends BaseMapper<User> {
    // 所有的CURD已经完成  JPA，tk-mapper
    // 不需要再配置以前的xml文件
}
