package com.github.joine.common.constant;

import com.github.joine.common.core.domain.BaseEnum;
import com.github.joine.common.utils.MessageUtils;
import com.github.joine.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/18 4:24 PM
 */
public enum ResponseEnum implements BaseEnum {
    /**
     * 请求成功
     */
    REQUEST_SUCCESS(0, "request.success"),
    /**
     * 请求失败
     */
    REQUEST_FAILURE(10001, "request.failure"),
    /**
     * 缺少认证信息
     */
    USER_TOKEN_NOT_EXISTS(10400, "user.token.not.exists.error"),
    /**
     * 未认证或认证信息过期
     */
    USER_TOKEN_EXPIRED(10401, "user.token.expired.error"),
    /**
     * 认证信息非法
     */
    USER_TOKEN_INVALID(10402, "user.token.invalid.error"),
    /**
     * 请求方式不支持
     */
    REQUEST_METHOD_NOT_SUPPORT(10405, "request.method.not.support"),
    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(20001, "user.not.exists"),
    /**
     * 密码错误
     */
    USER_PASSWORD_NOT_MATCH(20002, "user.password.not.match"),
    /**
     * 服务器错误
     */
    SERVER_ERROR(99999, "server.error");

    private Integer code;

    private String msg;

    private static Map<Integer, String> allMap = new HashMap<>();

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    static {
        for (ResponseEnum enums : ResponseEnum.values()) {
            allMap.put(enums.code, enums.msg);
        }
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String msg() {
        String message = null;
        if (!StringUtils.isEmpty(msg)) {
            message = MessageUtils.message(msg);
        }
        if (message == null) {
            message = msg;
        }
        return message;
    }

    public String msg(String code) {
        return allMap.get(code);
    }
}
