package com.itxiaodao.mapper;

import com.itxiaodao.pojo.UserLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlgorithmMapper {

    // 获取用户标签及权重
    @Select("SELECT tag_id AS labelId, tag_name AS labelName, weight FROM sys_user_label WHERE user_id = #{userId}")
    List<UserLabel> getUserTags(Integer userId);
}
