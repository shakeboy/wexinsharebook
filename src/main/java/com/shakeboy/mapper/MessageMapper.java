package com.shakeboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shakeboy.pojo.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
