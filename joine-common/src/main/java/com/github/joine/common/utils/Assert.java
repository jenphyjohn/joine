package com.github.joine.common.utils;

import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.exception.CustomizeException;

import java.util.regex.Pattern;

/**
 * @Author: JenphyJohn
 * @Date: 2019/9/24 12:49 PM
 */
public class Assert {

    /**
     * 校验字符串不可为空
     *
     * @param str
     * @param responseEnum
     */
    public static void isBlank(String str, ResponseEnum responseEnum) {
        if (StringUtils.isBlank(str)) {
            throw new CustomizeException(responseEnum);
        }
    }

    /**
     * 校验对象不可为空
     *
     * @param object
     * @param responseEnum
     */
    public static void isNull(Object object, ResponseEnum responseEnum) {
        if (object == null) {
            throw new CustomizeException(responseEnum);
        }
    }

    /**
     * 校验手机号1开头11位
     *
     * @param mobile
     * @param responseEnum
     */
    public static void isNotMobile(String mobile, ResponseEnum responseEnum) {
        String pattern = "^1\\d{10}$";
        Pattern regexp = Pattern.compile(pattern);
        if (StringUtils.isBlank(mobile) || !regexp.matcher(mobile).matches()) {
            throw new CustomizeException(responseEnum);
        }
    }
}
