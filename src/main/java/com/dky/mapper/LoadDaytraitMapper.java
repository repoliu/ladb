package com.dky.mapper;

import com.dky.entity.*;
import com.dky.entity.influ.LoadWeatherCluster;
import com.dky.entity.influ.vo.ClusterData;
import com.dky.entity.influ.vo.ClusterTableData;
import com.dky.entity.vo.DaytraitResult;
import com.dky.entity.vo.YearTraitResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Repository
public interface LoadDaytraitMapper {
    int insert(LoadDaytrait record);
    List<LoadDaytrait> selectQuery (LoadDaytraitKey key);
    LoadDaytrait selectMaxMinAvgSum (LoadDaytraitKey key);
    LoadDaytrait selectJinDuTiao (LoadDaytraitKey key);
    DaytraitResult findExtremaMdateByKey(LoadDaytraitKey loadDaytraitKey);//日、周 分析页面的时间选择框在使用
    YearTraitResult findExtremaYearByArea(int area);
    LoadYeartrait findYeartraitByKey(LoadYeartraitKey loadYeartraitKey);
    List<LoadDaytrait> findChartsDataByKey(LoadYeartraitKey loadYeartraitKey);

    //为周符合分析页面的周指标查询
    LoadWeektrait selectForWeekByKey(LoadDaytraitKey key);
    String  dayMaxDate (LoadDaytraitKey key);
    List<LoadDaytrait> selectQuery2 (LoadDaytraitKey key);

    LoadDaytrait findLastDayByArea(int area);

    LoadDaytrait selectByParams(Map<String, Object> params);

    List<LoadDaytrait> findResentListByParams(Map<String, Object> params);

    List<ClusterData> findClusterDatasByParams(Map<String, Object> params);

    List<LoadWeatherCluster> findClustersByParams(Map<String, Object> params);

    List<ClusterTableData> findClusterTableDatasByParams(Map<String, Object> params);

    int findClusterCountByParams(Map<String, Object> params);

    List<BigDecimal> findValuesByParams(Map<String,Object> params);

    Integer selectLoadDaytraitTF (@Param("area")int area,@Param("mdate")String mdate);
    //这个是根据时间和地区查询一个对象
    LoadDaytrait selectOne(@Param("area")int area,@Param("mdate")String mdate);

    String selectMaxDate(@Param("area")int area );

    String selectMaxDateString(@Param("area")int  area,@Param("mdate")String mdate);

}