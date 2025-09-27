package com.itxiaodao.controller;

import com.itxiaodao.pojo.IntCircle;
import com.itxiaodao.pojo.PageBean;
import com.itxiaodao.pojo.Result;
import com.itxiaodao.service.IntcirclrService;
import com.itxiaodao.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/intcircle")
//兴趣圈相关接口
public class IntcircleController {

    @Autowired
    private IntcirclrService intcircleService;

    //创建兴趣圈
    @PostMapping
    public Result addCircle(@RequestParam("name") String name,
                            @RequestParam("introduce") String introduce,
                            @RequestParam("categoryId") Integer categoryId,
                            @RequestParam(value = "address", required = false) String address,
                            @RequestParam(value = "longitude", required = false) Double longitude,
                            @RequestParam(value = "latitude", required = false) Double latitude,
                            @RequestParam("avatar") MultipartFile avatarFile) throws IOException {

        IntCircle intcircle = new IntCircle();
        intcircle.setName(name);
        intcircle.setIntroduce(introduce);
        intcircle.setCategoryId(categoryId);
        intcircle.setAddress(address);
        intcircle.setLongitude(longitude);
        intcircle.setLatitude(latitude);
        intcircle.setCreateTime(LocalDateTime.now());
        intcircle.setUpdateTime(LocalDateTime.now());

        // 处理头像上传
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String originalFilename = avatarFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;

            // 保存到本地磁盘（比如 D:/code/java/sencom/uploads/intCircle）
            File dest = new File("D:D:/code/java/sencom/uploads/intCircle" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            avatarFile.transferTo(dest);

            // 拼接访问 URL
            String avatarUrl = "/uploads/intCircle/" + fileName;
            intcircle.setAvatar(avatarUrl);
        }

        intcircleService.addCircle(intcircle);
        return Result.success();
    }

    //添加用户
    @PostMapping("/adduser")
    public Result addUser(@RequestParam("id") Integer intcircleId){
        intcircleService.addUser(intcircleId);
        return Result.success();
    }

    //查询附近兴趣圈
    @GetMapping("/around")
    public Result getNearbyIntCircles(@RequestParam Double longitude,
                                      @RequestParam Double latitude) {
        List<IntCircle> data = intcircleService.findNearby(longitude, latitude);
        return Result.success(data);
    }

    //获取兴趣圈列表
    @GetMapping
    public Result<PageBean<IntCircle>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId
    ) {
        PageBean<IntCircle> pb = intcircleService.list(pageNum,pageSize,categoryId);
        return Result.success(pb);
    }

    //获取兴趣圈详情
    @GetMapping("/detail")
    public Result<IntCircle> detail(Integer id){
        IntCircle i = intcircleService.findById(id);
        return Result.success(i);
    }

    //获取用户已添加的兴趣圈列表
    @GetMapping("/mine")
    public Result getMyIntCircles(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ) {
        PageBean<IntCircle> pb = intcircleService.getMyIntCircles(pageNum, pageSize);
        return Result.success(pb);
    }

    //更新兴趣圈头像
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("id") Integer intCircleId,
                               @RequestParam("avatar") MultipartFile file) throws IOException {
        // 1.校验文件
        if (file.isEmpty()) {
            return Result.error(400,"文件不能为空");
        }

        // 2.生成保存路径
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;

        // 假设放到 D:/code/java/sencom/uploads
        File dest = new File("D:/code/java/sencom/uploads/intCircle" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);

        // 3. 拼接访问 URL
        String avatarUrl = "/uploads/intCircle" + fileName;

        // 4. 调用 Service 更新数据库
        intcircleService.updateAvatar(avatarUrl,intCircleId);
        return Result.success(avatarUrl); // 返回新头像地址
    }
}
















