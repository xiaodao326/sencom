package com.itxiaodao.mapper;

import com.itxiaodao.pojo.IntCircle;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface IntcircleMapper {

    //创建兴趣圈
    @Insert("insert into sys_intcircle(name, introduce, create_user, category_id, address, location, create_time, update_time) " +
            "VALUES(#{name},#{introduce},#{createUser},#{categoryId},#{address}," +
            "ST_GeomFromText(CONCAT('POINT(', #{latitude}, ' ', #{longitude}, ')'), 4326)," +
            "#{createTime},#{updateTime})")
    void addCircle(IntCircle intcircle);

    //添加用户
    @Insert("insert ignore into sys_user_intcircle(user_id, intcircle_id) " +
            "VALUES(#{userId},#{circleId})")
    void addUser(Integer userId, Integer circleId);

    //查询附近兴趣圈
    /*@Select("SELECT id, name, introduce, avatar, category_id AS categoryId, create_time AS createTime, address, " +
            "ST_Y(location) AS longitude, ST_X(location) AS latitude, " +
            "ST_Distance_Sphere(location, ST_GeomFromText(CONCAT('POINT(', #{latitude}, ' ', #{longitude}, ')'), 4326)) AS distance " +
            "FROM sys_intcircle " +
            "WHERE ST_Distance_Sphere(location, ST_GeomFromText(CONCAT('POINT(', #{latitude}, ' ', #{longitude}, ')'), 4326)) <= 5000 " +
            "ORDER BY distance ASC")*/


    /*@Select("SELECT c.id, c.name, c.introduce, c.avatar, c.category_id AS categoryId, c.create_time AS createTime, c.address, " +
            "ST_Y(c.location) AS longitude, ST_X(c.location) AS latitude, " +
            "u.nickname AS hostNickname, u.avatar AS hostAvatar, " +
            "ST_Distance_Sphere(c.location, ST_GeomFromText(CONCAT('POINT(', #{latitude}, ' ', #{longitude}, ')'), 4326)) AS distance " +
            "FROM sys_intcircle c " +
            "LEFT JOIN sys_user u ON c.create_user = u.id " +
            "WHERE ST_Distance_Sphere(c.location, ST_GeomFromText(CONCAT('POINT(', #{latitude}, ' ', #{longitude}, ')'), 4326)) <= 5000 " +
            "ORDER BY distance ASC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "introduce", column = "introduce"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "categoryId", column = "categoryId"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "address", column = "address"),
            @Result(property = "longitude", column = "longitude"),
            @Result(property = "latitude", column = "latitude"),
            @Result(property = "host.nickname", column = "hostNickname"),
            @Result(property = "host.avatar", column = "hostAvatar")
    })*/
    List<IntCircle> selectNearby(@Param("longitude") Double longitude, @Param("latitude") Double latitude);

    //获取兴趣圈列表
    List<IntCircle> list(Integer userid, Integer categoryId);

    //根据id查询(获取兴趣圈详情)
    IntCircle findById(Integer id);

    //获取用户已添加的兴趣圈列表
    List<IntCircle> getMyIntCircles(Integer userid);

    //更新兴趣圈头像
    @Update("update sys_intcircle set avatar=#{avatarUrl}.update_time=now() where id=#{intCircleId}")
    void updateAvatar(String avatarUrl, Integer intCircleId);
}
