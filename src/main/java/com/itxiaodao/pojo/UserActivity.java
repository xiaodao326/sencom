package com.itxiaodao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户报名活动相关pojo
public class UserActivity {
    private Integer id;
    private Integer userId;
    private Integer activityId;
    private LocalDateTime signupTime;
}
