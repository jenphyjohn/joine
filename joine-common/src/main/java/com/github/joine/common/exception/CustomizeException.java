package com.github.joine.common.exception;

import com.github.joine.common.exception.base.BaseException;

/**
 * 自定义消息异常
 *
 * @Author: JenphyJohn
 * @Date: 2019/5/28 4:08 PM
 */
public class CustomizeException extends BaseException {
    private static final long serialVersionUID = 1L;

    public CustomizeException(String code, Object[] args) {
        super(code, args);
    }

    public CustomizeException(String code) {
        this(code, null);
    }

}
