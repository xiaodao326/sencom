package com.itxiaodao.controller;

import com.itxiaodao.pojo.Article;
import com.itxiaodao.pojo.Comments;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.Result;
import com.itxiaodao.service.ArticleService;
import com.itxiaodao.service.CommentsService;
import com.itxiaodao.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/article")
//文章相关接口
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private LikeService likeService;

    //添加(发布)
    @PostMapping("/add")
    public Result add(@RequestParam("title") String title,
                      @RequestParam("content") String content,
                      @RequestParam(value = "intcircleId", required = false) Integer intcircleId,
                      @RequestParam(value = "coverImg", required = false) MultipartFile coverImg) throws IOException {

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setIntcircleId(intcircleId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        // 如果有上传封面图
        if (coverImg != null && !coverImg.isEmpty()) {
            String originalFilename = coverImg.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;

            // 保存到本地磁盘（例如 D:/code/java/sencom/uploads/article/）
            File dest = new File("D:/code/java/sencom/uploads/article/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            coverImg.transferTo(dest);

            // 拼接可访问的 URL（配合 WebConfig 映射）
            String coverImgUrl = "/uploads/article/" + fileName;
            article.setCoverImg(coverImgUrl);
        }

        articleService.add(article);
        return Result.success();
    }

    //获取帖子列表
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer intcircleId
    ) {
        PageBean<Article> pb = articleService.list(pageNum,pageSize,intcircleId);
        return Result.success(pb);
    }

    //获取帖子详情
    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article a = articleService.findById(id);
        return Result.success(a);
    }

    //更新帖子
    @PostMapping("/update")
    public Result update(@RequestParam("id") Integer articleId,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam(value = "intcircleId", required = false) Integer intcircleId,
                         @RequestParam(value = "coverImg", required = false) MultipartFile coverImg) throws IOException {

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setIntcircleId(intcircleId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        // 如果有上传封面图
        if (coverImg != null && !coverImg.isEmpty()) {
            String originalFilename = coverImg.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;

            // 保存到本地磁盘（例如 D:/code/java/sencom/uploads/article/）
            File dest = new File("D:/code/java/sencom/uploads/article/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            coverImg.transferTo(dest);

            // 拼接可访问的 URL（配合 WebConfig 映射）
            String coverImgUrl = "/uploads/article/" + fileName;
            article.setCoverImg(coverImgUrl);
        }

        articleService.update(article,articleId);
        return Result.success();
    }

    //删除帖子
    @DeleteMapping
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }

    /*
    * 点赞
    * */

    //点赞
    @PutMapping("/like")
    public Result likeArtivle(Integer articleId, Integer like) {
        likeService.like(articleId, like);
        return Result.success();
    }

    /*
    * 评论功能
    * */

    //发布评论
    @PostMapping("/comments")
    public Result addComment(@RequestBody Comments comments) {
        commentsService.add(comments);
        return Result.success();
    }

    //评论列表
    @GetMapping("/comments")
    public Result listComments(@RequestParam Integer articleId) {
        return commentsService.commentsByArticleId(articleId);
    }
}












