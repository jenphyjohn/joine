package com.github.joine.common.core.controller;

import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.constant.UserConstants;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.core.page.PageDomain;
import com.github.joine.common.core.page.TableDataInfo;
import com.github.joine.common.core.page.TableSupport;
import com.github.joine.common.utils.DateUtils;
import com.github.joine.common.utils.ServletUtils;
import com.github.joine.common.utils.StringUtils;
import com.github.joine.common.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author JenphyJohn
 */
public class BaseController {
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes" , "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ResponseResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected ResponseResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public ResponseResult success() {
        return ResponseResult.success();
    }

    /**
     * 返回失败消息
     */
    public ResponseResult error() {
        return ResponseResult.error();
    }

    /**
     * 返回成功消息
     */
    public ResponseResult successMsg(String message) {
        return ResponseResult.successMsg(message);
    }

    /**
     * 返回成功消息
     */
    public ResponseResult success(Object data) {
        return ResponseResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public ResponseResult error(String message) {
        return ResponseResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public ResponseResult response(ResponseEnum responseEnum) {
        return ResponseResult.response(responseEnum);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}" , url);
    }

    /**
     * 获取用户ID
     */
    public static Long getCurrentUserId() {
        return (Long) ServletUtils.getRequest().getAttribute(UserConstants.USER_KEY);
    }
}
