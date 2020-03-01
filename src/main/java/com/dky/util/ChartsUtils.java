package com.dky.util;

import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:43 2018/2/9
 */
public class ChartsUtils {
    public static ChartsOption createChartsOption(String[] data, String[] hengZhou, String name, String type) {
        ChartsOption chartsOption = new ChartsOption();
        Series series = new Series();
        series.setData(data);
        series.setName(name);
        series.setSmooth(true);
        series.setType(type);
        chartsOption.setHengZhou(hengZhou);
        chartsOption.setLegend(name);
        chartsOption.setSeries(series);
        return chartsOption;
    }

    public static Map<String,Object> setSeries(String name, boolean smooth, String[] data, String type, int yAxisIndex){
        Map<String,Object> result = new HashMap<>();
        result.put("name",name);
        result.put("smooth",smooth);
        result.put("data", data);
        result.put("type",type);
        result.put("yAxisIndex",yAxisIndex);
        return result;
    }
}
