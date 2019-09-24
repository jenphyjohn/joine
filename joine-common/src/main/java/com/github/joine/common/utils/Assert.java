package com.github.joine.common.utils;

import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.exception.CustomizeException;

/**
 * @Author: JenphyJohn
 * @Date: 2019/9/24 12:49 PM
 */
public class Assert {

    public static void isBlank(String str, ResponseEnum responseEnum) {
        if (StringUtils.isBlank(str)) {
            throw new CustomizeException(responseEnum);
        }
    }

    public static void isNull(Object object, ResponseEnum responseEnum) {
        if (object == null) {
            throw new CustomizeException(responseEnum);
        }
    }
}
