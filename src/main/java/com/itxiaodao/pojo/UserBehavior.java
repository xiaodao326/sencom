package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 用户行为日志表pojo
public class UserBehavior {
    private Integer id;           // ID
    private Integer userId;       // 用户ID
    private String eventType;     // 行为类型
    private String targetType;    // 目标类型
    private Integer targetId;     // 目标ID
    private String content;       // 可选：评论内容/搜索词
    private LocalDateTime timestamp; // 行为时间
}