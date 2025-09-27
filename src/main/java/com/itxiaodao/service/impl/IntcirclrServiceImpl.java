package com.itxiaodao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaodao.mapper.IntcircleMapper;
import com.itxiaodao.pojo.IntCircle;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.UserIntcircle;
import com.itxiaodao.service.IntcirclrService;
import com.itxiaodao.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class IntcirclrServiceImpl implements IntcirclrService {

    @Autowired
    private IntcircleMapper intcircleMapper;

    //创建兴趣圈
    @Override
    public void addCircle(IntCircle intcircle) {
        //补充属性值
        intcircle.setCreateTime(LocalDateTime.now());
        intcircle.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        intcircle.setCreateUser(userid);

//        System.out.println("longitude = " + intcircle.getLongitude());
//        System.out.println("latitude = " + intcircle.getLatitude());

        intcircleMapper.addCircle(intcircle);
    }

    //添加用户
    @Override
    public void addUser(Integer intcircleId) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        UserIntcircle userIntcircle = new UserIntcircle();
        userIntcircle.setUserId(userid);

        intcircleMapper.addUser(userid,intcircleId);
    }

    //查询附近兴趣圈
    @Override
    public List<IntCircle> findNearby(Double longitude, Double latitude) {
        return intcircleMapper.selectNearby(longitude, latitude);
    }

    //获取兴趣圈列表
    @Override
    public PageBean<IntCircle> list(Integer pageNum, Integer pageSize, Integer categoryId) {
        //创建PageBean对象
        PageBean<IntCircle> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<IntCircle> as = intcircleMapper.list(userid,categoryId);
        Page<IntCircle> p = (Page<IntCircle>) as;
        //把数据存到PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //根据id查询(获取兴趣圈详情)
    @Override
    public IntCircle findById(Integer id) {
        IntCircle i = intcircleMapper.findById(id);
        return i;
    }

    //获取用户已添加的兴趣圈列表
    @Override
    public PageBean<IntCircle> getMyIntCircles(Integer pageNum, Integer pageSize) {
        //创建PageBean对象
        PageBean<IntCircle> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<IntCircle> as = intcircleMapper.getMyIntCircles(userid);
        Page<IntCircle> p = (Page<IntCircle>) as;
        //把数据存到PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //更新兴趣圈头像
    @Override
    public void updateAvatar(String avatarUrl, Integer intCircleId) {
        intcircleMapper.updateAvatar(avatarUrl,intCircleId);
    }
}
