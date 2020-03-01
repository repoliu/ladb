package com.dky.mapper.influ;

import com.dky.entity.influ.Autovalue;
import com.dky.entity.influ.vo.RelationValue;
import com.dky.entity.influ.vo.WeatherInfo;
import com.dky.mybaties.ResultArraysHandler;
import com.dky.mybaties.ResultFileHandler;
import com.dky.mybaties.ResultMapHandler;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AutovalueMapper {
    Date findMaxMdateByArea(Integer area);

    List<Autovalue> findListByTimeAndStcd(Map<String, Object> params);

    List<RelationValue> findAssignTypeListByParams(Map<String, Object> params);

    WeatherInfo findOneDayWeatherByParams(Map<String, Object> params);

    List<WeatherInfo> findWeatherInfosByParams(Map<String, Object> params);
    List<Autovalue> selectRain(Autovalue autovalue);
    List<Autovalue> selectRainDay(Autovalue autovalue);

    //XLY用来查询某个雨量的总条数
    String selectRainNumber(Autovalue autovalue);

    void findValueMapByParams(Map<String, Object> params, ResultMapHandler handler);

    void findOnesByParams(Map<String, Object> params, ResultArraysHandler handler);

    List<Autovalue>  select();
    void  insert(Autovalue autovalue);

    void insertIntoFile(ResultFileHandler r);

    List<Autovalue> selectDateTime(@Param("listArea")List<String> listArea,@Param("stcd")String stcd);

    List<Autovalue> selerain();

}