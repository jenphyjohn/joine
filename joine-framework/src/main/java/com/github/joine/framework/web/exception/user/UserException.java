package com.github.joine.framework.web.exception.user;

import com.github.joine.framework.web.exception.base.BaseException;
import com.github.joine.framework.web.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author JenphyJohn
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
