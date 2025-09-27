package com.itxiaodao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaodao.mapper.ActivityMapper;
import com.itxiaodao.pojo.Activity;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.UserActivity;
import com.itxiaodao.service.ActivityService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    //添加(发布)
    @Override
    public void add(Activity activity) {
        //补充属性值
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        activity.setCreateUser(userid);

        activityMapper.add(activity);
    }

    //获取活动列表
    @Override
    public PageBean<Activity> list(Integer pageNum, Integer pageSize, Integer intcircleId) {
        //创建PageBean对象
        PageBean<Activity> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Activity> as = activityMapper.list(userid,intcircleId);
        Page<Activity> p = (Page<Activity>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //获取活动详情
    @Override
    public Activity findById(Integer id) {
        Activity a = activityMapper.findById(id);
        return a;
    }

    //更新活动
    @Override
    public void update(Activity activity) {
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.update(activity);
    }

    //删除活动
    @Override
    public void delete(Integer id) {
        activityMapper.delete(id);
    }

    //报名
    @Override
    public void addUser(Integer activityId) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);

        activityMapper.addUser(userId,activityId);
    }

    //取消报名
    @Override
    public void cclUser(Integer activityId) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);

        activityMapper.cclUser(userId,activityId);
    }

    //获取我发布的活动
    @Override
    public PageBean<Activity> activepostlist(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<Activity> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Activity> as = activityMapper.activepostlist(userid);
        Page<Activity> p = (Page<Activity>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //获取我参加的活动
    @Override
    public PageBean<Activity> activejoinlist(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<Activity> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Activity> as = activityMapper.activejoinlist(userid);
        Page<Activity> p = (Page<Activity>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
