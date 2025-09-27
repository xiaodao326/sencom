package com.itxiaodao.controller;

import com.itxiaodao.pojo.*;
import com.itxiaodao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//算法相关接口
public class ApiController {

    @Autowired
    private BehaviorService behaviorService;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private UserEmbeddingService userEmbeddingService;

    @Autowired
    private CircleEmbeddingService circleEmbeddingService;

    @Autowired
    private GraphService graphService;

    // 记录用户行为
    @PostMapping("/behavior/log")
    public ResponseEntity<Result> logBehavior(@RequestBody BehaviorLog behaviorLog) {
        behaviorService.logBehavior(behaviorLog);
        return ResponseEntity.ok(Result.success());
    }

    // 查询用户行为日志
    @GetMapping("/behavior/{userId}")
    public ResponseEntity<Result> getBehaviorLogs(@PathVariable Integer userId) {
        List<BehaviorLog> logs = behaviorService.getBehaviorLogs(userId);
        return ResponseEntity.ok(Result.success(logs));
    }

    // 获取用户向量
    @GetMapping("/algorithm/user/{userId}/tags")
    public ResponseEntity<Result<UserEmbedding>> getUserTags(@PathVariable Integer userId) {
        UserEmbedding userEmbedding = userEmbeddingService.getUserEmbedding(userId);
        return ResponseEntity.ok(Result.success(userEmbedding));
    }

    // 上传用户向量
    @PostMapping("/algorithm/user/{userId}/embedding")
    public ResponseEntity<Result> uploadUserEmbedding(@PathVariable Integer userId, @RequestBody UserEmbedding userEmbedding) {
        userEmbeddingService.uploadUserEmbedding(userId, userEmbedding);
        return ResponseEntity.ok(Result.success());
    }

    // 获取圈子向量
    @GetMapping("/algorithm/circle/{circleId}/embedding")
    public ResponseEntity<Result<CircleEmbedding>> getCircleEmbedding(@PathVariable Integer circleId) {
        CircleEmbedding circleEmbedding = circleEmbeddingService.getCircleEmbedding(circleId);
        return ResponseEntity.ok(Result.success(circleEmbedding));
    }

    // 上传圈子向量
    @PostMapping("/algorithm/circle/{circleId}/embedding")
    public ResponseEntity<Result> uploadCircleEmbedding(@PathVariable Integer circleId, @RequestBody CircleEmbedding circleEmbedding) {
        circleEmbeddingService.uploadCircleEmbedding(circleId, circleEmbedding);
        return ResponseEntity.ok(Result.success());
    }

    // 获取全图关系
    @GetMapping("/algorithm/graph/export")
    public ResponseEntity<Result> exportGraph() {
        List<Graph> graph = graphService.getGraph();
        return ResponseEntity.ok(Result.success(graph));
    }

    // 获取用户行为序列
    @GetMapping("/algorithm/user/{userId}/sequence")
    public ResponseEntity<Result<List<BehaviorLog>>> getUserBehaviorSequence(@PathVariable Integer userId) {
        List<BehaviorLog> behaviorLogs = behaviorService.getBehaviorLogs(userId);
        return ResponseEntity.ok(Result.success(behaviorLogs));
    }
}
