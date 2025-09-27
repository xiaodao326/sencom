package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 圈子向量表pojo
public class CircleEmbedding {
    private Integer circleId;     // 圈子ID
    private byte[] featureVector; // 向量数据
    private Integer dimension; // 向量的维度
    private LocalDateTime updateTime; // 更新时间
}