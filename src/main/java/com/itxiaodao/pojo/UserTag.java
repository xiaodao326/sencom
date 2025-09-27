package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//兴趣标签相关pojo
public class UserTag {
    private Integer id;
    private Integer userId;
    private Integer tagId;
}
