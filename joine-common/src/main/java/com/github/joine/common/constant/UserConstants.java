package com.github.joine.common.constant;

/**
 * 用户常量信息
 *
 * @author JenphyJohn
 */
public interface UserConstants {
    /**
     * 用户key
     */
    String USER_KEY = "userId";

    /**
     * 平台系统用户唯一标志
     */
    String SYS_USER = "SYS_USER";

    /**
     * 正常状态
     */
    String NORMAL = "0";

    /**
     * 异常状态
     */
    String EXCEPTION = "1";

    /**
     * 用户封禁状态
     */
    String USER_BLOCKED = "1";

    /**
     * 角色封禁状态
     */
    String ROLE_BLOCKED = "1";

    /**
     * 部门正常状态
     */
    String DEPT_NORMAL = "0";

    /**
     * 用户名长度限制
     */
    int USERNAME_MIN_LENGTH = 2;
    int USERNAME_MAX_LENGTH = 20;

    /**
     * 登录名称是否唯一的返回结果码
     */
    String USER_NAME_UNIQUE = "0";
    String USER_NAME_NOT_UNIQUE = "1";

    /**
     * 手机号码是否唯一的返回结果
     */
    String USER_PHONE_UNIQUE = "0";
    String USER_PHONE_NOT_UNIQUE = "1";

    /**
     * e-mail 是否唯一的返回结果
     */
    String USER_EMAIL_UNIQUE = "0";
    String USER_EMAIL_NOT_UNIQUE = "1";

    /**
     * 部门名称是否唯一的返回结果码
     */
    String DEPT_NAME_UNIQUE = "0";
    String DEPT_NAME_NOT_UNIQUE = "1";

    /**
     * 角色名称是否唯一的返回结果码
     */
    String ROLE_NAME_UNIQUE = "0";
    String ROLE_NAME_NOT_UNIQUE = "1";

    /**
     * 岗位名称是否唯一的返回结果码
     */
    String POST_NAME_UNIQUE = "0";
    String POST_NAME_NOT_UNIQUE = "1";

    /**
     * 角色权限是否唯一的返回结果码
     */
    String ROLE_KEY_UNIQUE = "0";
    String ROLE_KEY_NOT_UNIQUE = "1";

    /**
     * 岗位编码是否唯一的返回结果码
     */
    String POST_CODE_UNIQUE = "0";
    String POST_CODE_NOT_UNIQUE = "1";

    /**
     * 菜单名称是否唯一的返回结果码
     */
    String MENU_NAME_UNIQUE = "0";
    String MENU_NAME_NOT_UNIQUE = "1";

    /**
     * 字典类型是否唯一的返回结果码
     */
    String DICT_TYPE_UNIQUE = "0";
    String DICT_TYPE_NOT_UNIQUE = "1";

    /**
     * 参数键名是否唯一的返回结果码
     */
    String CONFIG_KEY_UNIQUE = "0";
    String CONFIG_KEY_NOT_UNIQUE = "1";

    /**
     * 密码长度限制
     */
    int PASSWORD_MIN_LENGTH = 5;
    int PASSWORD_MAX_LENGTH = 20;

    /**
     * 手机号码格式限制
     */
    String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 邮箱格式限制
     */
    String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
}
