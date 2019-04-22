package com.github.joine.restapi.exception;

import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.exception.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/22 1:26 PM
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 服务器错误
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseResult.errorResponse();
    }

    /**
     * 未认证或认证信息过期
     */
    @ExceptionHandler(UserTokenExpiredException.class)
    public ResponseResult handleException(UserTokenExpiredException e) {
        log.error(e.getMessage());
        return ResponseResult.response(ResponseEnum.USER_TOKEN_EXPIRED);
    }

    /**
     * 缺少认证信息
     */
    @ExceptionHandler(UserTokenNotExistsException.class)
    public ResponseResult handleException(UserTokenNotExistsException e) {
        log.error(e.getMessage());
        return ResponseResult.response(ResponseEnum.USER_TOKEN_NOT_EXISTS);
    }

    /**
     * 认证信息非法
     */
    @ExceptionHandler(UserTokenInvalidException.class)
    public ResponseResult handleException(UserTokenInvalidException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.response(ResponseEnum.USER_TOKEN_INVALID);
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return ResponseResult.response(ResponseEnum.REQUEST_METHOD_NOT_SUPPORT);
    }

    /**
     * 用户不存在
     */
    @ExceptionHandler({UserNotExistsException.class})
    public ResponseResult handleException(UserNotExistsException e) {
        log.error(e.getMessage());
        return ResponseResult.response(ResponseEnum.USER_NOT_EXISTS);
    }

    /**
     * 密码错误
     */
    @ExceptionHandler({UserPasswordNotMatchException.class})
    public ResponseResult handleException(UserPasswordNotMatchException e) {
        log.error(e.getMessage());
        return ResponseResult.response(ResponseEnum.USER_PASSWORD_NOT_MATCH);
    }
}
