package com.dky.thread.influ;

import com.dky.entity.influ.vo.WeatherResult;
import com.dky.service.influencingFactorAnalysis.WeatherService;

import java.text.ParseException;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 14:24 2017/12/18
 */
public class FindRstCrtsOptThread implements Runnable {
    private WeatherService weatherService;
    private WeatherResult result;
    private String mdate;
    public FindRstCrtsOptThread(WeatherService weatherService,
                                WeatherResult result,String mdate){
        this.mdate = mdate;
        this.weatherService = weatherService;
        this.result = result;
    }
    @Override
    public void run() {
        String areaname = weatherService.findAreaname(result.getArea());
        result.setAreaname(areaname);
        try {
            weatherService.findResultChartsOption(mdate,areaname,result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
