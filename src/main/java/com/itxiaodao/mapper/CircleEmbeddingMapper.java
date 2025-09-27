package com.itxiaodao.mapper;

import com.itxiaodao.pojo.CircleEmbedding;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CircleEmbeddingMapper {

    // 插入圈子向量
    @Insert("INSERT INTO sys_circle_embedding (circle_id, feature_vector, update_time) " +
            "VALUES (#{circleId}, #{featureVector}, #{updateTime})")
    void insertCircleEmbedding(CircleEmbedding circleEmbedding);

    // 获取圈子向量
    @Select("SELECT * FROM sys_circle_embedding WHERE circle_id = #{circleId}")
    CircleEmbedding getCircleEmbeddingByCircleId(Integer circleId);
}
