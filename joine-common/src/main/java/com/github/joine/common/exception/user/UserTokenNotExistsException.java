package com.github.joine.common.exception.user;

/**
 * 用户缺少token异常类
 *
 * @author JenphyJohn
 */
public class UserTokenNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserTokenNotExistsException() {
        super("user.token.not.exists.error", null);
    }
}
