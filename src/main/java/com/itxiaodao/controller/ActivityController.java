package com.itxiaodao.controller;

import com.itxiaodao.pojo.Activity;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.Result;
import com.itxiaodao.pojo.UserActivity;
import com.itxiaodao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/active")
//活动相关接口
public class ActivityController {

    @Autowired
    ActivityService activityService;

    //添加(发布)
    @PostMapping
    public Result add(@RequestBody @Validated Activity activity){
        activityService.add(activity);
        return Result.success();
    }

    //获取活动列表
    @GetMapping
    public Result<PageBean<Activity>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer intcircleId
    ){
        PageBean<Activity> pb = activityService.list(pageNum,pageSize,intcircleId);
        return Result.success(pb);
    }

    //获取活动详情
    @GetMapping("/detail")
    public Result<Activity> detail(Integer id) {
        Activity a = activityService.findById(id);
        return Result.success(a);
    }

    //更新活动
    @PutMapping
    public Result update(@RequestBody @Validated Activity activity ) {
        activityService.update(activity);
        return Result.success();
    }

    //删除活动
    @DeleteMapping
    public Result delete(Integer id) {
        activityService.delete(id);
        return Result.success();
    }

    //报名
    @PutMapping("/adduser")
    public Result addUser(@RequestParam("activeId") Integer activityId) {
        activityService.addUser(activityId);
        return Result.success();
    }

    //取消报名
    @PutMapping("/ccluser")
    public Result cclUser(@RequestParam("activeId") Integer activityId) {
        activityService.cclUser(activityId);
        return Result.success();
    }
}
