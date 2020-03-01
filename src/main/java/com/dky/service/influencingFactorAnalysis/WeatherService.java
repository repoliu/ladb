package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.ForWeather;
import com.dky.entity.influ.WeatherInfo;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.influ.vo.WeatherResult;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WeatherService {
    WeatherResult findAreaAndMdate();

    WeatherResult findExtremeMdateByArea(Integer area);

    List<ChartsOption> findTemperatureListByKey(Map<String, Object> param);

    List<ChartsOption> findRainfallListByKey(Map<String, Object> param);

    List<ChartsOption> findSupportListByKey(Map<String, Object> param);

    ForWeather findForWeatherByKey(Map<String, Object> param);

    List<WeatherInfo> findImportRecord() throws ParseException;

    String findAreaname(Integer area);

    void findResultChartsOption(String mdate, String areaname, WeatherResult result) throws ParseException;

    Date findMdateByArea(Integer area);

    String findGraphDataByParams(Map<String, Object> params);
}
