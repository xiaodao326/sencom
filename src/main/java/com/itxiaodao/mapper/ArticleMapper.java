package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    //添加(发布)
    @Insert("insert into sys_article(title, content, cover_img, create_user, intcircle_id, create_time, update_time)" +
            "VALUES(#{title},#{content},#{coverImg},#{createUser},#{intcircleId},#{createTime},#{updateTime}) ")
    void add(Article article);

    //获取帖子列表
    List<Article> list(Integer userid, Integer intcircleId);

    //获取帖子详情
    Article findById(Integer id);

    //更新帖子
    @Update("update sys_article set title=#{title},content=#{content},cover_img=#{coverImg},intcircle_id=#{intcircleId},update_time=now() WHERE id=#{articleId}")
    void update(Article article, Integer articleId);

    //删除帖子
    @Delete("delete from sys_article where id=#{id}")
    void delete(Integer id);

    //获取我发布的帖子
    List<Article> articlepostlist(Integer userId);

}
