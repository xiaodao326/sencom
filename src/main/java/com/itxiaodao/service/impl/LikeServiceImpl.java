package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.LikeMapper;
import com.itxiaodao.pojo.Result;
import com.itxiaodao.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public Result like(Integer articleId, Integer like) {
        if (articleId == null || like == null) {
            return Result.error(400,"Article ID cannot be null.");
        }

        if (like < 0 || like > 1) {
            return Result.error(400,"Like must be between 0 and 1.");
        }

        if (like == 1) {
            likeMapper.like(articleId); // 执行点赞
        } else if (like == 0) {
            likeMapper.unlike(articleId); // 执行取消点赞
        }
        return Result.success();
    }
}
