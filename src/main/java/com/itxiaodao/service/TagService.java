package com.itxiaodao.service;

import com.itxiaodao.pojo.Tag;
import com.itxiaodao.pojo.TagPageBean;
import com.itxiaodao.pojo.UserTag;

import java.util.List;

public interface TagService {
    //兴趣标签列表
    TagPageBean<Tag> list(Integer pageNum, Integer pageSize);

    //更新兴趣标签
    void update(List<Integer> tagIds);

    //新增兴趣标签
    void addTag(List<Integer> tagIds);
}
