package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.Autovalue;

import java.util.List;

public interface AutovalueService {
    List<Autovalue> selectRain(Autovalue autovalue);
    List<Autovalue> selectRainDay(Autovalue autovalue);

    //XLY
    String selectRainNumber(Autovalue autovalue);
    List<Autovalue>  select();
    void  insert(Autovalue autovalue);

    List<Autovalue> selectDateTime(List<String> listArea, String stcd);

}
