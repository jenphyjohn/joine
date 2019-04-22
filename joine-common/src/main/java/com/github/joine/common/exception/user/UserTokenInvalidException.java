package com.github.joine.common.exception.user;

/**
 * 用户token错误异常类
 *
 * @author JenphyJohn
 */
public class UserTokenInvalidException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserTokenInvalidException() {
        super("user.token.invalid.error", null);
    }
}
