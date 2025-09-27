package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//算法相关 圈数据结构pojo
public class Graph {
    private String nodeId;          // 节点ID
    private String nodeType;        // 节点类型（user, circle, tag）
    private String relatedNodeId;   // 相关节点ID
    private String relatedNodeType; // 相关节点类型（user, circle, tag）
    private String relationType;    // 关系类型（user-tag, circle-tag, user-circle）
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间
}