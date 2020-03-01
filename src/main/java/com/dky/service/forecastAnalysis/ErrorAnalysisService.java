package com.dky.service.forecastAnalysis;


import com.dky.entity.LoadDayerrvalue;
import com.dky.entity.LoadShortdayerrAll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrorAnalysisService {

    Map selectContrastCurve(String area, String date) ;

    Map selectAreaDate(String area, String date) ;
    LoadDayerrvalue selectAreaDate(int area, String date);

    List<LoadShortdayerrAll> findLoadShortdayerr(@Param("area")int area, @Param("mdate")String mdate);

}
