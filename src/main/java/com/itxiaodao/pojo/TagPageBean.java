package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
//标签分页相关
public class TagPageBean<T>{
    private Long total;//总条数
    private List<T> items;//当前页数据集合
    private List<Tag> tags; // 仅兴趣标签相关接口才有的字段
}
