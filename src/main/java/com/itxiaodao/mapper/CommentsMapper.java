package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {
    //发布评论
    @Insert("INSERT INTO sys_comments(content, article_id, create_user, create_time, update_time) " +
            "VALUES (#{content},#{articleId},#{createUser},now(),now())")
    void add(Comments comments);

    //评论列表
    @Select("SELECT id, content, article_id, create_user, create_time FROM sys_comments WHERE article_id = #{articleId}")
    List<Comments> findCommentsByArticleId(Integer articleId);
}
