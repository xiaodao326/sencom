package com.itxiaodao.service;

import com.itxiaodao.pojo.UserLabel;

import java.util.List;

public interface AlgorithmService {
    List<UserLabel> getUserTags(Integer userId);
}
