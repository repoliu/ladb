package com.dky.util.daoOperate;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author liuhaijian
 * @date Create in 2017/11/14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSource.getDataSourceKey();
    }
}