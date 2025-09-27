package com.itxiaodao.controller;

import com.itxiaodao.pojo.*;
import com.itxiaodao.service.ActivityService;
import com.itxiaodao.service.ArticleService;
import com.itxiaodao.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mine")
//`我的`相关接口
public class MineController {

    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ActivityService activityService;

    /*
    * 兴趣标签功能
    * */

    //兴趣标签列表
    @GetMapping("/tag")
    public Result<TagPageBean<Tag>> taglist(
            Integer pageNum,
            Integer pageSize
    ) {
        TagPageBean<Tag> pb = tagService.list(pageNum,pageSize);
        return Result.success(pb);
    }

    //新增兴趣标签
    @PostMapping("/tag")
    public Result addTag(@RequestBody List<Integer> tagIds){
        tagService.addTag(tagIds);
        return Result.success();
    }

    //更新兴趣标签
    @PutMapping("/tag")
    public Result updateTag(@RequestBody List<Integer> tagIds) {
        tagService.update(tagIds);
        return Result.success();
    }


    /*
    * 帖子相关功能
    * */

    //获取我发布的帖子
    @GetMapping("/article/post")
    public Result<PageBean<Article>> articlepostlist(
            Integer pageNum,
            Integer pageSize
    ) {
        PageBean<Article> pb = articleService.articlepostlist(pageNum,pageSize);
        return Result.success(pb);
    }


    /*
    * 活动相关接口
    * */

    //获取我发布的活动
    @GetMapping("/active/post")
    public Result<PageBean<Activity>> activepostlist(
            Integer pageNum,
            Integer pageSize
    ) {
        PageBean<Activity> pb = activityService.activepostlist(pageNum,pageSize);
        return Result.success(pb);
    }

    //获取我参加的活动
    @GetMapping("/active/join")
    public Result<PageBean<Activity>> activejoinlist(
            Integer pageNum,
            Integer pageSize
    ) {
        PageBean<Activity> pb = activityService.activejoinlist(pageNum,pageSize);
        return Result.success(pb);
    }
}









