package com.dky.service.forecastAnalysis;

import com.dky.entity.LoadForesult;

import java.util.List;


public interface LoadForesultService {
    LoadForesult select(LoadForesult loadForesult);
    int  selectInt(LoadForesult loadForesult);
    List<LoadForesult> selectAll();


    int update(String [] strings,String area,String mdate);
    int update(LoadForesult loadForesult);

    int insert(LoadForesult loadForesult);
}
