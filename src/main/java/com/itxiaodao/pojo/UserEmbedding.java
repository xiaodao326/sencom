package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 用户向量表pojo
public class UserEmbedding {
    private Integer userId;       // 用户ID
    private byte[] featureVector; // 向量数据
    private Integer dimension;    // 向量维度
    private LocalDateTime updateTime; // 更新时间
}