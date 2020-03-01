package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.influ.LoadRainLevel;
import com.dky.mapper.influ.LoadRainLevelMapper;
import com.dky.service.influencingFactorAnalysis.LoadRainLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loadRainLevelService")
public class LoadRainLevelServiceImpl implements LoadRainLevelService{
    @Autowired
    private LoadRainLevelMapper loadRainLevelMapper;


    @Override
    public LoadRainLevel select(String levelName) {
        return loadRainLevelMapper.select(levelName);
    }

    @Override
    public List<LoadRainLevel> selectAll() {
        return loadRainLevelMapper.selectAll();
    }

    @Override
    public void insert(LoadRainLevel loadRainLevel) {
        loadRainLevelMapper.insert(loadRainLevel);
    }

    @Override
    public LoadRainLevel selectMin(String string) {
        return loadRainLevelMapper.selectMin(string);
    }
}
