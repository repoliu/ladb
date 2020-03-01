package com.dky.service.periodicityAnalysis;

import com.dky.entity.period.Holiday;

import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:42 2018/2/27
 */
public interface HolidayService {
    List<String> findAllTypeOfHoliday();

    List<Holiday> findHolidayByParams(String startYear, String endYear, String holiday);

    Map<String,Object> findAllLoadOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays);

    Map<String,Object> findLoadOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays, String maxload);

    Map<String,Object> findLoadUpOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays, String maxload);

    Map<String,String> findStartAndEndYear();
}
