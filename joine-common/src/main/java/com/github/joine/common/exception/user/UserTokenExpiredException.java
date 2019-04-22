package com.github.joine.common.exception.user;

/**
 * 用户token过期或未认证异常类
 *
 * @author JenphyJohn
 */
public class UserTokenExpiredException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserTokenExpiredException() {
        super("user.token.expired.error", null);
    }
}
