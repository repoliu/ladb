package com.dky.thread.influ;

import com.dky.entity.influ.Autovalue;
import com.dky.mapper.influ.AutovalueMapper;
import com.dky.mybaties.ResultArraysHandler;
import com.dky.mybaties.ResultMapHandler;
import com.dky.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 16:17 2018/3/5
 */
public class DataOfWeatherInfoThread implements Callable<List<List<String>>> {
    private Integer area;
    private String mdate;
    private String name;
    private AutovalueMapper autovalueMapper;
    public final SimpleDateFormat YMDN_FMT = new SimpleDateFormat("yyyyMMdd");

    public DataOfWeatherInfoThread(Integer area, String mdate, String name, AutovalueMapper autovalueMapper) {
        this.area = area;
        this.mdate = mdate;
        this.name = name;
        this.autovalueMapper = autovalueMapper;
    }

    @Override
    public List<List<String>> call() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("area",area);
        long startdate = YMDN_FMT.parse(mdate).getTime();
        long enddate = startdate + 86400000L;
        params.put("startdate", new Date(startdate));
        params.put("enddate", new Date(enddate));
        params.put("name",name);
        ResultArraysHandler handler = new ResultArraysHandler();
        autovalueMapper.findOnesByParams(params,handler);
        return handler.getMappedResults();
    }
}
