package com.itheima.intercepters;

import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);
            //放行
            return true;
        } catch (Exception e) {
            //不放行且设置状态码为401
            response.setStatus(401);
            return false;
        }

    }
}
