package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.LoadRainInfo;

import java.util.List;

public interface LoadRainInfoService {

    void selectSL(String date, String areaname);
    List<LoadRainInfo> select (LoadRainInfo loadRainInfo);
    List<LoadRainInfo> selectCopy (String areaname, String  rainLeve,String stringDate);

    List<LoadRainInfo> selectDayNumber(LoadRainInfo loadRainInfo);

    void delete (LoadRainInfo loadRainInfo);

    void insert (List<LoadRainInfo> list);
}
