package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//用户关注兴趣圈相关pojo
public class UserIntcircle {
    private Integer id;
    private Integer userId;
    private Integer intcircleId;
}
