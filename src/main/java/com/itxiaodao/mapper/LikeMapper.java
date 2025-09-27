package com.itxiaodao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LikeMapper {
    //点赞
    @Update("UPDATE sys_article SET `like` = `like` + 1 WHERE id = #{articleId}")
    void like(Integer articleId);

    //取消点赞
    @Update("UPDATE sys_article SET `like` = `like` - 1 WHERE id = #{articleId} AND `like` > 0")
    void unlike(Integer articleId);
}
