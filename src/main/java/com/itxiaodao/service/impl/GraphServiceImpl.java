package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.GraphMapper;
import com.itxiaodao.pojo.Graph;
import com.itxiaodao.service.GraphService;
import com.itxiaodao.service.UserEmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    private GraphMapper graphMapper;

    // 获取全图关系
    public List<Graph> getGraph() {
        return graphMapper.getGraph();
    }
}
