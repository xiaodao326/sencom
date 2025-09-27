package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.CommentsMapper;
import com.itxiaodao.pojo.Comments;
import com.itxiaodao.pojo.Result;
import com.itxiaodao.service.CommentsService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    //发布评论
    @Override
    public void add(Comments comments) {
        //补充属性值
        comments.setCreateTime(LocalDateTime.now());
        comments.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        comments.setCreateUser(userid);

        commentsMapper.add(comments);
    }

    //评论列表
    @Override
    public Result commentsByArticleId(Integer articleId) {
        if (articleId == null) {
            return Result.error(400,"Article ID cannot be null.");
        }

        List<Comments> comments = commentsMapper.findCommentsByArticleId(articleId);
        if (comments.isEmpty()) {
            return Result.error(400,"No comments found for this article.");
        }

        return Result.success(comments);
    }
}
