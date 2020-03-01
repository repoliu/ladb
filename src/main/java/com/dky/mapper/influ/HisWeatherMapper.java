package com.dky.mapper.influ;

import com.dky.entity.influ.ForWeather;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface HisWeatherMapper {
    int isExist(Map<String, Object> param);

    int updateByKey(ForWeather forWeather);

    int insert(ForWeather forWeather);
}