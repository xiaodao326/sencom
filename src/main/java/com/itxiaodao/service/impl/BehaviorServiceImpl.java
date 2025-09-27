package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.BehaviorMapper;
import com.itxiaodao.pojo.BehaviorLog;
import com.itxiaodao.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BehaviorServiceImpl implements BehaviorService {

    @Autowired
    private BehaviorMapper behaviorMapper;

    // 记录用户行为
    public void logBehavior(BehaviorLog behaviorLog) {
        behaviorMapper.insertBehaviorLog(behaviorLog);
    }

    // 查询用户行为日志
    public List<BehaviorLog> getBehaviorLogs(Integer userId) {
        return behaviorMapper.getBehaviorLogsByUserId(userId);
    }
}
