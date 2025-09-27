package com.itxiaodao.mapper;

import com.itxiaodao.pojo.CircleEmbedding;
import com.itxiaodao.pojo.UserEmbedding;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserEmbeddingMapper {

    // 插入用户向量
    @Insert("INSERT INTO sys_user_embedding (user_id, feature_vector, dimension, update_time) " +
            "VALUES (#{userId}, #{featureVector}, #{dimension}, #{updateTime})")
    void insertUserEmbedding(UserEmbedding userEmbedding);

    // 获取圈子向量
    @Select("SELECT * FROM sys_user_embedding WHERE user_id = #{userId}")
    UserEmbedding getUserEmbeddingByCircleId(Integer userId);
}
