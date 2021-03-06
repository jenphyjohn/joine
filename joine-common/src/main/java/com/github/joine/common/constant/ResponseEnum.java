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
     * 手机号不能为空
     */
    MOBILE_NOT_EMPTY(10002, "mobile.not.empty"),
    /**
     * 验证码未失效，请失效后再次申请
     */
    VERIFY_CODE_EFFECTIVE(10003, "verify.code.effective"),
    /**
     * 验证码已过期，请重新获取
     */
    EXPIRED_VERIFY_CODE(10004, "expired.verify.code"),
    /**
     * 验证码错误，请重新输入
     */
    ERROR_VERIFY_CODE(10005, "error.verify.code"),
    /**
     * 手机号格式不正确
     */
    MOBILE_FORMAT_INCORRECT(10006, "mobile.format.incorrect"),
    /**
     * 用户昵称已存在
     */
    USER_NAME_EXISTS(10007, "user.name.exists"),
    /**
     * 手机号已注册
     */
    LOGIN_NAME_EXISTS(10008, "login.name.exists"),
    /**
     * 账号已被锁定
     */
    USER_ACCOUNT_LOCKED(10009, "user.account.locked"),
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
     * 缺少必要参数
     */
    REQUIRED_PARAMS_MISSING(10403, "required.params.missing.error"),
    /**
     * 请求方式不支持
     */
    REQUEST_METHOD_NOT_SUPPORT(10404, "request.method.not.support"),
    /**
     * 请求格式不正确
     */
    REQUEST_FORMAT_NOT_SUPPORT(10405, "request.format.not.support"),
    /**
     * 校验失败
     */
    VALIDATION_FAILED(10999, "validation.failed"),
    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(20001, "user.not.exists"),
    /**
     * 密码错误
     */
    USER_PASSWORD_NOT_MATCH(20002, "user.password.not.match"),
    /**
     * 微信用户信息校验失败
     */
    WX_USERINFO_ERROR(30000, "wechat.userinfo.error"),
    /**
     * 系统警告
     */
    SYSTEM_WARNING(99997, "system.warning"),
    /**
     * 自定义异常
     */
    CUSTOMIZE_ERROR(99998, "system.error"),
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
