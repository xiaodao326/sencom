package com.itxiaodao.service;

import com.itxiaodao.pojo.User;
import org.hibernate.validator.constraints.URL;

public interface UserService {
    //根据账号查询用户
    User findByAccount(String account);
    //注册
    void register(String account, String password);
    //更新用户详细信息
    void update(User user);
    //更新用户头像
    void updateAvatar(@URL String avatarUrl);
    //更新用户密码
    void updatePwd(String newPwd);
    //根据账号查询用户带tag
    User findByAccountWithTag(String account);
}
