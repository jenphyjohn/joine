package com.github.joine.restapi.datasource;

import com.github.joine.common.config.datasource.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * @author JenphyJohn
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSourceType();
    }
}