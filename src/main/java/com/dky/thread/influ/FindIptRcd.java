package com.dky.thread.influ;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.influ.WeatherInfo;
import com.dky.entity.influ.vo.WeatherResult;
import com.dky.entity.vo.TimeSelectButtonOfDayLoad;
import com.dky.service.influencingFactorAnalysis.WeatherService;
import com.dky.util.TimePoint;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 14:46 2017/12/18
 */
public class FindIptRcd implements Runnable {
    private WeatherResult result;
    private WeatherService weatherService;
    private String mdate;
    public FindIptRcd(WeatherService weatherService,
                      WeatherResult result,String mdate){
        this.mdate = mdate;
        this.weatherService = weatherService;
        this.result = result;
    }
    @Override
    public void run() {
        List<TimeSelectButtonOfDayLoad> timeSelectButton = null;
        try {
            timeSelectButton = TimePoint.createTimeButtonValue(mdate.replace("-", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.setTimeButton(timeSelectButton);
        List<WeatherInfo> iList = null;
        try {
            iList = weatherService.findImportRecord();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.setIList(JSONArray.toJSONString(iList));
    }
}
