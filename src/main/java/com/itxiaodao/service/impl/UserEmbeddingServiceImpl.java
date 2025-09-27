package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.UserEmbeddingMapper;
import com.itxiaodao.pojo.CircleEmbedding;
import com.itxiaodao.pojo.UserEmbedding;
import com.itxiaodao.service.UserEmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEmbeddingServiceImpl implements UserEmbeddingService {

    @Autowired
    private UserEmbeddingMapper userEmbeddingMapper;

    // 上传用户向量
    @Override
    public void uploadUserEmbedding(Integer userId, UserEmbedding userEmbedding) {
        userEmbedding.setUserId(userId);
        userEmbeddingMapper.insertUserEmbedding(userEmbedding);
    }

    //获取用户向量
    @Override
    public UserEmbedding getUserEmbedding(Integer userId) {
        return userEmbeddingMapper.getUserEmbeddingByCircleId(userId);
    }
}
