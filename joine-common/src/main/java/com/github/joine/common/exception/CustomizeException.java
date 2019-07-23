package com.github.joine.common.exception;

import com.github.joine.common.constant.ResponseEnum;

/**
 * 自定义消息异常
 *
 * @Author: JenphyJohn
 * @Date: 2019/5/28 4:08 PM
 */
public class CustomizeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码枚举
     */
    private ResponseEnum responseEnum;

    /**
     * 错误消息
     */
    private String defaultMessage = ResponseEnum.CUSTOMIZE_ERROR.msg();

    public CustomizeException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public CustomizeException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        if (responseEnum == null) {
            return defaultMessage;
        }
        return responseEnum.msg();
    }

    public Integer getCode() {
        if (responseEnum == null) {
            return ResponseEnum.CUSTOMIZE_ERROR.code();
        }
        return responseEnum.code();
    }

}
