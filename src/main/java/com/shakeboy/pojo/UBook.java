package com.shakeboy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ubooks")
public class UBook {
    @TableId(type = IdType.AUTO)
    private int ubookId;
    private String ubookName;
    private String ubookDescription;
    private String ubookImage;
    private String ubookContent;
    private int ubookGood;
    private int ubookComment;
    private int ubookTransfer;
    private int userId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
