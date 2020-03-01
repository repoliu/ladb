package com.dky.mapper.influ;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CityforecastMapper {
    String findWeatherText24ByTimeAndStcd(Map<String, Object> params);

    String findWeatherText24ByParams(Map<String, Object> params);
}