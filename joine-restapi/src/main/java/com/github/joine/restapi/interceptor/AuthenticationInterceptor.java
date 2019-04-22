package com.github.joine.restapi.interceptor;

import com.github.joine.business.domain.User;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.exception.user.UserNotExistsException;
import com.github.joine.common.exception.user.UserTokenExpiredException;
import com.github.joine.common.exception.user.UserTokenInvalidException;
import com.github.joine.common.exception.user.UserTokenNotExistsException;
import com.github.joine.common.utils.StringUtils;
import com.github.joine.restapi.annotation.PassAuth;
import com.github.joine.restapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/22 10:42 AM
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    public static final String USER_KEY = "userId";

    @Autowired
    private IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有AuthPass注解，有则跳过认证
        if (method.isAnnotationPresent(PassAuth.class)) {
            PassAuth passToken = method.getAnnotation(PassAuth.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 执行认证
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new UserTokenNotExistsException();
        }
        String loginName = JWTUtil.getLoginName(token);
        if (loginName == null) {
            throw new UserTokenInvalidException();
        }
        List<User> users = iUserService.selectUserList(new User());
        if (CollectionUtils.isEmpty(users)) {
            throw new UserNotExistsException();
        }
        User user = users.get(0);
        // 验证 token
        if (!JWTUtil.verify(token, user.getLoginName(), user.getPassword())) {
            throw new UserTokenExpiredException();
        }
        // 设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, user.getUserId());
        return true;
    }
}
