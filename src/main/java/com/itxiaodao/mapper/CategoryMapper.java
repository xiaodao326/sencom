package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //新增
    @Insert("insert into sys_category (category_name, category_alias, create_user, create_time, update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime});")
    void add(Category category);

    //查询所有
    @Select("select * from sys_category where create_user=#{userid};")
    List<Category> List(Integer userid);

    //根据id查询
    @Select("select * from sys_category where id=#{id}")
    Category findById(Integer id);

    //更新
    @Update("update sys_category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    //删除
    @Delete("delete from sys_category where id=#{id}")
    void delete(Integer id);
}
