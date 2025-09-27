package com.itxiaodao.service;

import com.itxiaodao.pojo.BehaviorLog;

import java.util.List;

public interface BehaviorService {
    void logBehavior(BehaviorLog behaviorLog);

    List<BehaviorLog> getBehaviorLogs(Integer userId);
}
