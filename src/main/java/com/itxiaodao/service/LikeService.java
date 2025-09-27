package com.itxiaodao.service;

import com.itxiaodao.pojo.Result;

public interface LikeService {
    //点赞
    Result like(Integer articleId, Integer like);
}
