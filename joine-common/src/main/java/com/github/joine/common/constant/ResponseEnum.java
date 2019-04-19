package com.github.joine.common.constant;

import com.github.joine.common.core.domain.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/18 4:24 PM
 */
public enum ResponseEnum implements BaseEnum {
    /**
     * 成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 错误
     */
    ERROR(99999, "服务器错误");

    private Integer code;

    private String msg;

    private static Map<Integer, String> allMap = new HashMap<>();

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    static {
        for (ResponseEnum enums : ResponseEnum.values()) {
            allMap.put(enums.code, enums.msg);
        }
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }

    public String msg(String code) {
        return allMap.get(code);
    }
}
