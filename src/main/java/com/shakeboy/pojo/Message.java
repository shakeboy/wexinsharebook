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
@TableName("messages")
public class Message {
    @TableId(type = IdType.AUTO)
    private int messageId;
    private int userIdo;
    private int userIdt;
    private String userOt;
    private String messageContent;
}
