package com.shakeboy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("uarticles")
public class UArticle {
    private int uarticleId;
    private String uarticleName;
    private String uarticleContent;
    private int userId;
}
