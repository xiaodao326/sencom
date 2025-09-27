package com.itxiaodao.service.impl;

import com.itxiaodao.mapper.UserMapper;
import com.itxiaodao.pojo.User;
import com.itxiaodao.service.UserService;
import com.itxiaodao.utils.Md5Util;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据账号查询用户
    @Override
    public User findByAccount(String account) {
        User u = userMapper.findByAccount(account);
        return u;
    }

    //注册
    @Override
    public void register(String account, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(account,md5String);
    }

    //更新用户详细信息
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    //更新用户头像
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    //更新用户密码
    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }

    //根据账户查询用户带tag
    @Override
    public User findByAccountWithTag(String account) {
        User u = userMapper.findByAccountWithTag(account);
        return u;
    }
}
