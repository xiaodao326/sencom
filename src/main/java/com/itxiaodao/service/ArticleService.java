package com.itxiaodao.service;

import com.itxiaodao.pojo.Article;
import com.itxiaodao.pojo.PageBean;

public interface ArticleService {

    //添加(发布)
    void add(Article article);

    //获取帖子列表
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer intcircleId);

    //获取帖子详情
    Article findById(Integer id);

    //更新帖子
    void update(Article article,  Integer articleId);

    //删除帖子
    void delete(Integer id);

    //获取我发布的帖子
    PageBean<Article> articlepostlist(Integer pageNum, Integer pageSize);
}
