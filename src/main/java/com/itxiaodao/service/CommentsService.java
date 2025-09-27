package com.itxiaodao.service;

import com.itxiaodao.pojo.Comments;
import com.itxiaodao.pojo.Result;

public interface CommentsService {
    //发布评论
    void add(Comments comments);
    //评论列表
    Result commentsByArticleId(Integer articleId);
}
