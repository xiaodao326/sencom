package com.itxiaodao.mapper;

import com.itxiaodao.pojo.BehaviorLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BehaviorMapper {

    // 插入行为日志
    @Insert("INSERT INTO sys_user_behavior (user_id, event_type, target_type, target_id, content, timestamp) " +
            "VALUES (#{userId}, #{eventType}, #{targetType}, #{targetId}, #{content}, #{timestamp})")
    void insertBehaviorLog(BehaviorLog behaviorLog);

    // 获取用户行为日志
    @Select("SELECT * FROM sys_user_behavior WHERE user_id = #{userId} ORDER BY timestamp ASC")
    List<BehaviorLog> getBehaviorLogsByUserId(Integer userId);
}
