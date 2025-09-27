package com.itxiaodao.service;

import com.itxiaodao.pojo.CircleEmbedding;

public interface CircleEmbeddingService {
    void uploadCircleEmbedding(Integer circleId, CircleEmbedding circleEmbedding);

    CircleEmbedding getCircleEmbedding(Integer circleId);
}
