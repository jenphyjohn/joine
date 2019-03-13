package com.github.joine.common.utils.sql;

import com.github.joine.common.utils.StringUtils;

/**
 * @Author: JenphyJohn
 * @Date: 2019/3/12 5:25 PM
 */
public class SqlUtil {

    /**
     * 防止sql注入 替换危险字符
     */
    public static String escapeSql(String value) {
        if (StringUtils.isNotEmpty(value)) {
            value = value.replaceAll("\\(", "");
            value = value.replaceAll("\\)", "");
        }
        return value;
    }
}
