package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.CircleEmbeddingMapper;
import com.itxiaodao.pojo.CircleEmbedding;
import com.itxiaodao.service.CircleEmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CircleEmbeddingServiceImpl implements CircleEmbeddingService {

    @Autowired
    private CircleEmbeddingMapper circleEmbeddingMapper;

    // 上传圈子向量
    public void uploadCircleEmbedding(Integer circleId, CircleEmbedding circleEmbedding) {
        circleEmbedding.setCircleId(circleId);
        circleEmbedding.setUpdateTime(LocalDateTime.now());
        circleEmbeddingMapper.insertCircleEmbedding(circleEmbedding);
    }

    //获取圈子向量
    @Override
    public CircleEmbedding getCircleEmbedding(Integer circleId) {
        return circleEmbeddingMapper.getCircleEmbeddingByCircleId(circleId);
    }
}
