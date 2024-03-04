package com.example.myblog.config;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.myblog.common.ThreadLocalRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${token.key}")
    private String key;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.getWriter().write("未登录！");
            return false;
        }
        try{
            JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
            JSONObject payloads = jwt.getPayloads();

            String loginName = payloads.get("loginName").toString();

            ThreadLocalRequest.setInfo(loginName);
            String redisToken = redisTemplate.opsForValue().get(loginName).toString();

            if(!redisToken.equals(token)) {
                response.getWriter().write("token验证失败！");
                return false;
            }

        }catch (Exception err) {
            response.getWriter().write("token不合法！");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器处理结束...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("请求结束...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}