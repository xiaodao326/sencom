package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 用户行为日志pojo
public class BehaviorLog {
    private Integer userId;
    private String eventType;
    private String targetType;
    private Integer targetId;
    private String content;
    private String timestamp;
}