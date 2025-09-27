package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityMapper {

    //添加(发布)
    @Insert("insert into sys_activity(title, introduce, time, location, intcircle_id, create_user, create_time, update_time) " +
            "VALUES(#{title},#{introduce},#{time},#{location},#{intcircleId},#{createUser},now(),now())")
    void add(Activity activity);

    //获取活动列表
    List<Activity> list(Integer userid, Integer intcircleId);

    //获取活动详情
    Activity findById(Integer id);

    //更新活动
    @Update("UPDATE sys_activity SET title=#{title},introduce=#{introduce},`time`=#{time},location=#{location},intcircle_id=#{intcircleId},update_time=now() WHERE id=#{id}")
    void update(Activity activity);

    //删除活动
    @Delete("DELETE FROM sys_activity  WHERE id=#{id}")
    void delete(Integer id);

    //报名
    @Insert("INSERT sys_user_activity(user_id, activity_id, signup_time) " +
            "VALUES(#{userId},#{activityId},now())")
    void addUser(Integer userId, Integer activityId);

    //取消报名
    @Delete("DELETE FROM sys_user_activity WHERE user_id=#{userId} AND activity_id=#{activityId}")
    void cclUser(Integer userId, Integer activityId);

    //获取我发布的活动
    List<Activity> activepostlist(Integer userid);

    //获取我参加的活动
    List<Activity> activejoinlist(Integer userid);
}
