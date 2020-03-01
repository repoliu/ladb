package com.dky.mapper.period;

import com.dky.entity.period.Holiday;
import com.dky.mybaties.ResultMapHandler;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:47 2018/2/27
 */
@Repository
public interface CommonMapper {
    List<String> findAllTypeOfHoliday();

    List<Holiday> findHolidayByParams(Map<String, Object> params);

    Map<String,String> findStartAndEndYear();
}
