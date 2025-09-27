package com.itxiaodao.service;

import com.itxiaodao.pojo.UserEmbedding;

public interface UserEmbeddingService {
    void uploadUserEmbedding(Integer userId, UserEmbedding userEmbedding);

    UserEmbedding getUserEmbedding(Integer userId);
}
