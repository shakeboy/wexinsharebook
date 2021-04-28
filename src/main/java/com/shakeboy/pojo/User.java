package com.shakeboy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userAccount;
    private String userPassword;
    private String userSignature;
    private String userAvator;
    private String userName;
    private String userRole;
}
