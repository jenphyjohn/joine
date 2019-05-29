package com.github.joine.common.core.domain;

import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author JenphyJohn
 */
public class ResponseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 状态类型
     */
    private Type type;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ResponseResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态类型
     * @param msg  返回内容
     */
    public ResponseResult(Integer code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }


    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public ResponseResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResponseResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param responseEnum 响应枚举
     */
    public ResponseResult(ResponseEnum responseEnum) {
        super.put(CODE_TAG, responseEnum.code());
        super.put(MSG_TAG, responseEnum.msg());
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param responseEnum 响应枚举
     * @param data         数据对象
     */
    public ResponseResult(ResponseEnum responseEnum, Object data) {
        super.put(CODE_TAG, responseEnum.code());
        super.put(MSG_TAG, responseEnum.msg());
        super.put(DATA_TAG, data);
    }

    /**
     * 返回响应消息
     *
     * @return 响应消息
     */
    public static ResponseResult response(Integer code, String msg) {
        return new ResponseResult(code, msg);
    }

    /**
     * 返回响应消息
     *
     * @return 响应消息
     */
    public static ResponseResult response(ResponseEnum responseEnum) {
        return new ResponseResult(responseEnum);
    }

    /**
     * 返回消息
     *
     * @return 消息
     */
    public static ResponseResult response(ResponseEnum responseEnum, Object data) {
        return new ResponseResult(responseEnum, data);
    }

    /**
     * 返回成功响应消息
     *
     * @return 成功响应消息
     */
    public static ResponseResult successResponse() {
        return response(ResponseEnum.REQUEST_SUCCESS);
    }

    /**
     * 返回成功响应消息
     *
     * @param data 数据对象
     * @return 成功响应消息
     */
    public static ResponseResult successResponse(Object data) {
        return response(ResponseEnum.REQUEST_SUCCESS, data);
    }

    /**
     * 返回失败响应消息
     *
     * @return 失败响应消息
     */
    public static ResponseResult errorResponse() {
        return response(ResponseEnum.SERVER_ERROR);
    }

    /**
     * 返回成功消息数据
     *
     * @return 成功消息数据
     */
    public static ResponseResult success(Object data) {
        return ResponseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg) {
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult warn(String msg) {
        return ResponseResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult warn(String msg, Object data) {
        return new ResponseResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseResult error() {
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(Type.ERROR, msg, data);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getCode() {
        return super.get(CODE_TAG);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return super.get(MSG_TAG);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return super.get(DATA_TAG);
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("msg", getMsg())
                .append("data", getData())
                .toString();
    }
}
