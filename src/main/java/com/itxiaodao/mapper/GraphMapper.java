package com.itxiaodao.mapper;

import com.itxiaodao.pojo.Graph;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GraphMapper {

    // 获取全图关系
    @Select("SELECT * FROM sys_graph WHERE node_type IN ('user', 'circle', 'tag') OR related_node_type IN ('user', 'circle', 'tag')")
    List<Graph> getGraph();
}
