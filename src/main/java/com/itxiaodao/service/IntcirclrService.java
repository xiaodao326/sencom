package com.itxiaodao.service;

import com.itxiaodao.pojo.IntCircle;
import com.itxiaodao.pojo.PageBean;
import org.springframework.data.geo.Circle;

import java.util.List;

public interface IntcirclrService {
    //创建兴趣圈
    void addCircle(IntCircle intcircle);
    //添加用户
    void addUser(Integer intcircleId);
    //查询附近兴趣圈
    List<IntCircle> findNearby(Double longitude, Double latitude);
    //条件分页列表查询(获取兴趣圈列表)
    PageBean<IntCircle> list(Integer pageNum, Integer pageSize, Integer categoryId);
    //根据id查询(获取兴趣圈详情)
    IntCircle findById(Integer id);
    //获取用户已添加的兴趣圈列表
    PageBean<IntCircle> getMyIntCircles(Integer pageNum, Integer pageSize);
    //更新兴趣圈头像
    void updateAvatar(String avatarUrl, Integer intCircleId);
}
