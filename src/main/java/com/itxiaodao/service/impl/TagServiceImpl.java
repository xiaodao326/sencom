package com.itxiaodao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaodao.mapper.TagMapper;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.Tag;
import com.itxiaodao.pojo.TagPageBean;
import com.itxiaodao.pojo.UserTag;
import com.itxiaodao.service.TagService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    //兴趣标签列表
    @Override
    public TagPageBean<Tag> list(Integer pageNum, Integer pageSize) {
        // 创建PageBean对象
        TagPageBean<Tag> pb = new TagPageBean<>();

        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 获取当前用户ID
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        // 查询所有标签和已选标签
        List<Tag> allTags = tagMapper.list(userId); // 获取所有标签
        List<Tag> selectedTags = tagMapper.listSelectedTags(userId); // 获取用户已选标签

        // 将已选标签放入一个集合中，以便在返回时标记
        Set<Integer> selectedTagIds = new HashSet<>();
        for (Tag tag : selectedTags) {
            selectedTagIds.add(tag.getId());
        }

        // 创建返回的数据结构
        List<Tag> items = new ArrayList<>();
        for (Tag tag : allTags) {
            // 在items中添加所有标签
            items.add(tag);
        }

        // 填充PageBean对象
        pb.setTotal(((Page<?>) allTags).getTotal()); // 设置总记录数
        pb.setItems(items); // 设置分页数据

        // 设置已选标签列表，只有被选中的标签
        pb.setTags(selectedTags); // 这里只返回已选标签
        return pb;
    }

    @Override
    public void update(List<Integer> tagIds) {
        //补充属性值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        UserTag userTag = new UserTag();
        userTag.setUserId(userId);

        // 删除该用户原有的标签
        tagMapper.deleteUserTagsByUserId(userId);

        //批量插入新的标签
        for (Integer tagId : tagIds) {
            tagMapper.addTag(tagId,userId);
        }

    }

    @Override
    public void addTag(List<Integer> tagIds) {
        //补充属性值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        UserTag userTag = new UserTag();
        userTag.setUserId(userId);

        for (Integer tagId : tagIds) {
            tagMapper.addTag(tagId,userId);
        }
    }
}
