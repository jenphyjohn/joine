package com.github.joine.framework.web.page;

import com.github.joine.common.constant.Constants;
import com.github.joine.framework.util.ServletUtils;

/**
 * 表格数据处理
 *
 * @author JenphyJohn
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain buildPageRequest() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }
}
