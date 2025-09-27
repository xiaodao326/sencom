package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.AlgorithmMapper;
import com.itxiaodao.pojo.UserLabel;
import com.itxiaodao.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    private AlgorithmMapper algorithmMapper;

    // 获取用户标签
    public List<UserLabel> getUserTags(Integer userId) {
        return algorithmMapper.getUserTags(userId);
    }
}
