package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.CategoryMapper;
import com.itxiaodao.pojo.Category;
import com.itxiaodao.service.CategoryService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        //补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        category.setCreateUser(userid);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");

        return categoryMapper.List(userid);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

}
