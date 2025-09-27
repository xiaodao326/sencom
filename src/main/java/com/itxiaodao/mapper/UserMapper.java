package com.itxiaodao.mapper;

import com.itxiaodao.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    //根据账号查询用户
    @Select("select id, account, password, nickname, phone, avatar, gender, address, birth, create_time, update_time from sys_user where account = #{account}")
    User findByAccount(String account);

    //添加
    @Insert("insert into sys_user(account, password, create_time, update_time)" +
            "values (#{account},#{password},now(),now())")
    void add(String account, String password);

    //更新用户详细信息
    @Update("update sys_user set nickname=#{nickname},phone=#{phone},gender=#{gender},address=#{address},birth=#{birth},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //更新用户头像
    @Update("update sys_user set avatar=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //更新用户密码
    @Update("update sys_user set password=#{password},update_time=now() where id=#{id}")
    void updatePwd(String password, Integer id);

    User findByAccountWithTag(String account);
}
