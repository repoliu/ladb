package com.dky.mapper.influ;

import com.dky.entity.influ.ForHourWeather;
import com.dky.entity.influ.HisHourWeather;
import com.dky.entity.influ.vo.WeatherResult;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface HisHourWeatherMapper {
    HisHourWeather findHisHourWeatherByKey(Map<String, Object> param);

    int isExist(Map<String, Object> param);

    int updateByKey(ForHourWeather forHourWeather);

    int insert(ForHourWeather forHourWeather);

    WeatherResult findAreaAndMdate();
}