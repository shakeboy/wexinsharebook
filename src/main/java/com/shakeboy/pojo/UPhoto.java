package com.shakeboy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("uphotos")
public class UPhoto {
    @TableId(type = IdType.AUTO)
    private int uphotoId;
    private String uphotoName;
    private String uphotoAddress;
    private int userId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
