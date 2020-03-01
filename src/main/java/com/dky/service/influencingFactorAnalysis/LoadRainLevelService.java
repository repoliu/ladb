package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.LoadRainLevel;

import java.util.List;

public interface LoadRainLevelService {
    LoadRainLevel select(String levelName);
    List<LoadRainLevel> selectAll();
    void insert(LoadRainLevel loadRainLevel);
    LoadRainLevel selectMin(String string);

}
