package com.atguigu.lease.common.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 轻量鉴权拦截器：
 * 只从 Gateway 透传的 user-id / username header 里读用户身份，
 * 不再解析 JWT（JWT 校验已经前置在 Gateway 完成）。
 * header 缺失时 ThreadLocal 为 null，业务层可按需自行校验。
 */
@Component
public class HeaderAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userIdHeader = request.getHeader("user-id");
        String usernameHeader = request.getHeader("username");
        if (userIdHeader != null) {
            Long userId = Long.valueOf(userIdHeader);
            LoginUserHolder.setLoginUser(new LoginUser(userId, usernameHeader));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LoginUserHolder.clear();
    }
}
