package com.github.joine.common.core.domain;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/18 1:51 PM
 */
public interface BaseEnum<K, V> {
    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     *
     * @return 描述
     */
    V msg();
}
