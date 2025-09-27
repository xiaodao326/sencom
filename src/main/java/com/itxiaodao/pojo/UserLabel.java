package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 用户标签pojo
public class UserLabel {
    private Integer id;
    private Integer userId;  // 用户ID
    private Integer tagId;   // 标签ID
    private String tagName;  // 标签名称
    private Integer weight;    // 标签权重
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}