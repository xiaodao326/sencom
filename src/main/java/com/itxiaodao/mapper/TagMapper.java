package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Tag;
import com.itxiaodao.pojo.UserTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
    //兴趣标签列表
    @Select("SELECT * FROM sys_tag")
    List<Tag> list(Integer userId);

    //获取已选标签
    @Select("SELECT t.id, t.name FROM sys_tag t " +
            "JOIN sys_user_tag ut ON t.id = ut.tag_id " +
            "WHERE ut.user_id = #{userId}")
    List<Tag> listSelectedTags(Integer userId);

    //新增兴趣标签
    @Insert("INSERT INTO sys_user_tag(user_id, tag_id) " +
            "VALUES (#{userId},#{tagId})")
    void addTag(Integer tagId,  Integer userId);

    //删除某个用户的所有标签
    @Delete("DELETE FROM sys_user_tag WHERE user_id = #{userId}")
    void deleteUserTagsByUserId(Integer userId);

}
