package com.dky.mapper.influ;

import com.dky.entity.influ.ForHourWeather;
import com.dky.entity.influ.vo.WeatherResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ForHourWeatherMapper {
    WeatherResult findAreaAndMdate();
    WeatherResult findExtremeMdateByArea(Integer area);
    ForHourWeather findForHourWeatherByKey(Map<String, Object> param);
    List<ForHourWeather> select();
    int isExist(Map<String, Object> param);
    int updateByKey(ForHourWeather forHourWeather);

    int insert(ForHourWeather forHourWeather);
}