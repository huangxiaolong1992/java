package com.example.myblog.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWTUtil;
import com.example.myblog.common.R;
import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${token.key}")
    private String key;

    @PostMapping("/register")
    @ApiOperation("注册")
    public R<String> Register(@RequestBody User user) {
        String loginName = user.getLoginName();
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User userData = userService.GetUser(loginName);
        if(userData == null){
            user.setCreateTime(DateUtil.now());
            userService.Register(user);
            return R.success("注册成功", null);
        }else {
            return R.error("账号已存在");
        }
    }

    @GetMapping("/login")
    @ApiOperation("登录")
    public R<String> login(User user) {
        String token;
        String loginName = user.getLoginName();
        String pwd = user.getPassword();

        User userData = userService.GetUser(loginName);
        if(userData == null) {
            return R.error("账号不存在");
        }else if(!userData.getPassword().equals(pwd)){
            return R.error("密码错误");
        }

        if(redisTemplate.opsForValue().get(loginName) != null){
            token = redisTemplate.opsForValue().get(loginName).toString();
        }else{
            Map<String, Object> payload = new HashMap<>();
            payload.put("loginName", loginName);
            payload.put("password", pwd);
            payload.put("time",  DateUtil.current());
            token = JWTUtil.createToken(payload, key.getBytes());

            redisTemplate.opsForValue().set(loginName, token, 60 * 6, TimeUnit.MINUTES);
        }

        return  R.success("登录成功", token);
    }

}
