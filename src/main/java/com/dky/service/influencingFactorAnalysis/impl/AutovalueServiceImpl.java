package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.influ.Autovalue;
import com.dky.mapper.influ.AutovalueMapper;
import com.dky.service.influencingFactorAnalysis.AutovalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("autovalueService")
public class AutovalueServiceImpl implements AutovalueService {



    @Autowired
    private AutovalueMapper autovalueMapper;

    @Override
    public List<Autovalue> selectRain(Autovalue autovalue) {
        return autovalueMapper.selectRain(autovalue);
    }

    @Override
    public List<Autovalue> selectRainDay(Autovalue autovalue) {
        return autovalueMapper.selectRainDay(autovalue);
    }

    @Override
    public String selectRainNumber(Autovalue autovalue) {
        return autovalueMapper.selectRainNumber(autovalue);
    }


    @Override
    public List<Autovalue> select() {
        return autovalueMapper.select();
    }

    @Override
    public void insert(Autovalue autovalue) {
        autovalueMapper.insert(autovalue);
    }

    @Override
    public List<Autovalue> selectDateTime(List<String> listArea, String stcd) {
        return autovalueMapper.selectDateTime(listArea,stcd);
    }

}
