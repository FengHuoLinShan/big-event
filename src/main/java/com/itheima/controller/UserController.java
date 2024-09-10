package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        //查询用户
        User u = userService.findByUserName(username);
        //注册
        if (u == null) {
            //未占用，注册
            userService.register(username, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        //根据用户名查询用户
        User u = userService.findByUserName(username);
        //判断该用户是否存在
        if (u == null) {
            return Result.error("用户名错误");
        }
        //判断密码是否正确 password为密文格式
        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
             claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户名查询用户
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
