package com.dky.mapper.influ;

import com.dky.entity.influ.WeatherInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface WeatherInfoMapper {
    Date findLastVerTime();

    List<WeatherInfo> findImportListByVerTime(Map<String,String> condition);

    void insert(WeatherInfo info);
}