package com.dky.mybaties;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description: 自定义结果集返回key，value
 * @Date: Create in 14:56 2018/2/5
 */
public class ResultMapHandler implements ResultHandler{
    @SuppressWarnings("rawtypes")
    private final Map mappedResults = new LinkedHashMap();

    @Override
    public void handleResult(ResultContext context) {
        @SuppressWarnings("rawtypes")
        Map map = (Map) context.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));
    }

    public Map getMappedResults() {
        return mappedResults;
    }
}
