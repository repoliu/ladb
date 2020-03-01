package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.OutputValue;
import com.dky.entity.vo.ChartsOption;

import java.util.List;
import java.util.Map;

public interface OutPutValueService {

    int insert (OutputValue outputValue);


    List<ChartsOption> selectQuery(String area,String date,String nianXian);

    List<ChartsOption> selectQueryIndex(String area,String date,String nianXian);

    Map selectMaxDate(String areaName);

}
