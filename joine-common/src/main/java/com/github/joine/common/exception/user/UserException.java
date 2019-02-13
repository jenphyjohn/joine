package com.github.joine.common.exception.user;


import com.github.joine.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author JenphyJohn
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
