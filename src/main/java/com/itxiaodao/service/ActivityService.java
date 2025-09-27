package com.itxiaodao.service;

import com.itxiaodao.pojo.Activity;
import com.itxiaodao.pojo.PageBean;

public interface ActivityService {
    //添加(发布)
    void add(Activity activity);

    //获取活动列表
    PageBean<Activity> list(Integer pageNum, Integer pageSize, Integer intcircleId);

    //获取活动详情
    Activity findById(Integer id);

    //更新活动
    void update(Activity activity);

    //删除活动
    void delete(Integer id);

    //报名
    void addUser(Integer activityId);

    //取消报名
    void cclUser(Integer activityId);

    //获取我发布的活动
    PageBean<Activity> activepostlist(Integer pageNum, Integer pageSize);

    //获取我参加的活动
    PageBean<Activity> activejoinlist(Integer pageNum, Integer pageSize);
}
