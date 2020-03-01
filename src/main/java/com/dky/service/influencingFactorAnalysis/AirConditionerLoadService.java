package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.vo.WeatherResult;
import com.dky.entity.vo.ChartsOption;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface AirConditionerLoadService {

    String findLastMdateByArea(Integer area);

    void setResultByParams(WeatherResult result, Map<String, String> params) throws ParseException;

    List<ChartsOption> setMaxProportion(WeatherResult result, Map<String, String> params) throws ParseException;

    List<ChartsOption> setMaxEnergy(Map<String, String> params);

    String selectLoadByLoadDaytrait(Map<String, String> params,int choose);

    String selectMaxLoad(Map<String, String> params);
}
