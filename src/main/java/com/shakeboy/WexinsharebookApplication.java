package com.shakeboy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shakeboy.mapper") //扫描mapper文件夹
@SpringBootApplication
public class WexinsharebookApplication {
    public static void main(String[] args) {
        SpringApplication.run(WexinsharebookApplication.class, args);
    }

}
