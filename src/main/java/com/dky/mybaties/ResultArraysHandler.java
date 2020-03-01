package com.dky.mybaties;

import com.alibaba.fastjson.JSONArray;
import com.dky.util.DateUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * @Author: YangSL
 * @Description: 自定义结果集，将查询的数据，经过归一化处理返回
 * @Date: Create in 17:13 2018/3/5
 */
public class ResultArraysHandler implements ResultHandler {
    @SuppressWarnings("rawtypes")
    private final List<List<String>> mappedResults = new ArrayList<>();
    @Override
    public void handleResult(ResultContext resultContext) {
        @SuppressWarnings("rawtypes")
        Map map = (Map) resultContext.getResultObject();
        List<String> list = new ArrayList<>();
        list.add(DateUtils.format((Date) map.get("time"),DateUtils.HM_P));
        double value = (int)map.get("value")*1.0;
        double cValue = (int) map.get("cValue")*1.0;
        double avgValue = (int)map.get("minValue")*1.0;
        double r = (value-avgValue)/cValue;
        list.add(String.format("%.2f", r));
        mappedResults.add(list);
    }
    public List<List<String>> getMappedResults() {
        if (mappedResults.get(mappedResults.size()-1).get(0).equals("00:00")){
            mappedResults.get(mappedResults.size()-1).set(0,"24:00");
        }
        return mappedResults;
    }
}
