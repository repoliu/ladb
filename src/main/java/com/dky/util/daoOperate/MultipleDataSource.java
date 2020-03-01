package com.dky.util.daoOperate;

/**
 * @author liuhaijian
 * @date Create in 2017/11/13
 */

public class MultipleDataSource {
/*切换数据源方法，在调用service前使用
MultipleDataSource.setDataSourceKey(datasource);
*/
    public static final String datasource = "dataSource1";
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    public static String getDataSourceKey() {
        return ((String) dataSourceKey.get());
    }

    public static void clearDBType() {
        dataSourceKey.remove();
    }


}
