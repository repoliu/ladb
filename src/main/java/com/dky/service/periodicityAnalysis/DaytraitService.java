package com.dky.service.periodicityAnalysis;

import com.dky.entity.*;
import com.dky.entity.vo.DaytraitResult;
import com.dky.entity.vo.YearTraitResult;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author YangSL
 * @PackageName: com.dky.service.periodicityAnalysis.impl
 * @Description: 接口
 * @date Create in 2017/10/16 16:13
 * <p>
 * ${tags}
 */

public interface DaytraitService {
    int insert(LoadDaytrait record);

    List<LoadDaytrait> selectQuery(LoadDaytraitKey key);

    List<LoadDaytrait> selectQuery2(LoadDaytraitKey key);//周符合预测在使用
    LoadDaytrait selectJinDuTiao (LoadDaytraitKey key);

    DaytraitResult findExtremaMdateByKey(LoadDaytraitKey loadDaytraitKey);

    YearTraitResult findExtremaYearByArea(int area);

    LoadYeartrait findYeartraitByKey(LoadYeartraitKey loadYeartraitKey);

    List<LoadDaytrait> findChartsDataByKey(LoadYeartraitKey loadYeartraitKey);

    LoadWeektrait selectForWeekIndex(LoadDaytraitKey loadDaytraitKey);

    String  getDayMaxDate (LoadDaytraitKey key);


    LoadDaytrait selectMaxMinAvgSum (LoadDaytraitKey key);//xly

    LoadDaytrait findDayTraitByParams(Map<String, Object> params);

    String createChartsByParams(Map<String, Object> params) throws ParseException;
    int selectLoadHisdataToLoadDaytrait(String areas, String mdate);
    String selectMaxDate(int area);
    String selectMaxDateString(int  area,String mdate);

}
