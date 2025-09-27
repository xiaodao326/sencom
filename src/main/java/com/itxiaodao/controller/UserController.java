package com.itxiaodao.controller;

import com.itxiaodao.pojo.Result;
import com.itxiaodao.pojo.User;
import com.itxiaodao.service.UserService;
import com.itxiaodao.utils.JwtUtil;
import com.itxiaodao.utils.Md5Util;
import com.itxiaodao.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
//用户相关接口
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
    * 注册
    * */
    @PostMapping("/register")
    public Result refister(@Pattern(regexp = "^\\S{5,16}$") String account, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByAccount(account);
        if (u == null) {
            //没有占用
            //注册
            userService.register(account,password);
            return Result.success();
        }else {
            //占用
            return Result.error(400,"用户名已被占用");
        }
    }

    /*
    * 登录
    * */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String account, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByAccount(account);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error(400,"用户名错误");
        }

        //判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登陆成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("account", loginUser.getAccount());
            String token = JwtUtil.genToken(claims);
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }

        return Result.error(400,"密码错误");
    }

    /*
    * 获取用户详细信息
    * */
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        /*//根据用户名查询用户
        Map<String, Object> map = JwtUtil.parseToken(token);
        String account = (String) map.get("account");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String account = (String) map.get("account");
        User user = userService.findByAccountWithTag(account);
        return Result.success(user);
    }

    /*
    * 更新用户详细信息
    * */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /*
    * 更新用户头像
    * */
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatar") MultipartFile file) throws IOException {
        // 1. 校验文件
        if (file.isEmpty()) {
            return Result.error(400,"文件不能为空");
        }

        // 2. 生成保存路径
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;

        // 假设放到 D:/code/java/sencom/uploads
        File dest = new File("D:/code/java/sencom/uploads/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);

        // 3. 拼接访问 URL
        String avatarUrl = "/uploads/" + fileName;

        // 4. 调用 Service 更新数据库
        userService.updateAvatar(avatarUrl);

        return Result.success(avatarUrl); // 返回新头像地址
    }


    /*
    * 更新用户密码
    * */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token) {
        //校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error(400,"缺少必要的参数");
        }

        //原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String account = (String) map.get("account");
        User loginUser = userService.findByAccount(account);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error(400,"原密码错误");
        }

        //newPwd与rePwd是否一样
        if (!rePwd.equals(newPwd)) {
            return Result.error(400,"两次填写密码不一样");
        }
        userService.updatePwd(newPwd);

        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    /*
     * 退出登录
     * */
    @DeleteMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        if (!StringUtils.hasLength(token)) {
            return Result.error(400, "缺少token");
        }

        //从Redis中删除token
        Boolean deleted = stringRedisTemplate.delete(token);
        if (Boolean.TRUE.equals(deleted)) {
            return Result.success("退出登录成功");
        } else {
            return Result.error(400, "退出登录失败或token不存在");
        }
    }

}
