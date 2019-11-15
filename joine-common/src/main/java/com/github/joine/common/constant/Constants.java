package com.github.joine.common.constant;

/**
 * 通用常量信息
 *
 * @author JenphyJohn
 */
public interface Constants {
    /**
     * UTF-8 字符集
     */
    String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    String FAIL = "1";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    String IS_ASC = "isAsc";

    /**
     * 删除标志（0代表存在）.
     */
    String DEL_FLAG_FALSE = "0";

    /**
     * 删除标志（2代表删除）.
     */
    String DEL_FLAG_TRUE = "2";

    /**
     * 终端类型 APP
     */
    String APP_TYPE_MOBILE = "0";

    /**
     * 终端类型 微信小程序
     */
    String APP_TYPE_MP = "1";

    /**
     * app类型 ios
     */
    int APP_TYPE_IOS = 1;
}
