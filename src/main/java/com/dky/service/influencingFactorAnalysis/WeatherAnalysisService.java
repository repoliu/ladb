package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.LoadDaytrait;
import com.dky.entity.influ.ForWeather;
import com.dky.entity.influ.vo.ClusterData;
import com.dky.entity.influ.vo.ClusterTableData;
import com.dky.entity.influ.vo.RelationValue;
import com.dky.entity.influ.vo.WeatherInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 11:27 2018/1/5
 */
public interface WeatherAnalysisService {
    List<LoadDaytrait> findListByParams(Map<String, Object> params);

    List<RelationValue> findValuesByParams(Map<String, Object> params);

    String createLoadCharts(List<LoadDaytrait> daytraits, List<RelationValue> values,String analysisType) throws ParseException;

    WeatherInfo setWeatherInfoByParams(Map<String, Object> params);

    List<ClusterData> findDatasByParams(Map<String, Object> params) throws ParseException;

    List<ClusterTableData> findClusterDatasByParams(Map<String, Object> params) throws ParseException;

    int findClusterCountByParams(Map<String, Object> params);

    Boolean setTempSelect(Map<String, Object> params);

    String findRainCLusterGraphByParams(Map<String, Object> params);

    Map<String,Object> findRainGraphsByParams(Integer area, String mdate) throws ExecutionException, InterruptedException;

    Map<String,Object> findDLoadSpotByParams(Integer area, String mdate);
}
