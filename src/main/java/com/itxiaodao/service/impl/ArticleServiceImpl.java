package com.itxiaodao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaodao.mapper.ArticleMapper;
import com.itxiaodao.pojo.Article;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.service.ArticleService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    //添加(发布)
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    //获取帖子列表
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer intcircleId) {
        //创建PageBean对象
        PageBean<Article> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userid,intcircleId);
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //获取帖子详情
    @Override
    public Article findById(Integer id) {
        Article a = articleMapper.findById(id);
        return a;
    }

    //更新帖子
    @Override
    public void update(Article article, Integer articleId) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article,articleId);
    }

    //删除帖子
    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    //获取我发布的帖子
    @Override
    public PageBean<Article> articlepostlist(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<Article> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.articlepostlist(userId);
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
