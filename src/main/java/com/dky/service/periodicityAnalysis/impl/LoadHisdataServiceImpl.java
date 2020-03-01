package com.dky.service.periodicityAnalysis.impl;

import com.dky.entity.*;
import com.dky.mapper.LoadHisdataMapper;
import com.dky.mapper.LoadWeektypeload288Mapper;
import com.dky.mapper.LoadWeektypeload96Mapper;
import com.dky.mapper.influ.LoadRainInfoMapper;
import com.dky.service.periodicityAnalysis.LoadHisdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 14:09
 */
@Service("loadHisdataService")
public class LoadHisdataServiceImpl implements LoadHisdataService {
    private LoadHisdataMapper loadHisdata;
    private LoadWeektypeload96Mapper loadWeektypeload96Mapper;
    private LoadWeektypeload288Mapper loadWeektypeload288Mapper;
    @Autowired
    public LoadHisdataServiceImpl(LoadHisdataMapper loadHisdata,LoadWeektypeload96Mapper loadWeektypeload96Mapper,
                                  LoadWeektypeload288Mapper loadWeektypeload288Mapper){
        this.loadHisdata = loadHisdata;
        this.loadWeektypeload96Mapper = loadWeektypeload96Mapper;
        this.loadWeektypeload288Mapper = loadWeektypeload288Mapper;
    }

    public List<LoadHisdata> selectAll(LoadHisdataKey key) {
        return loadHisdata.selectAll(key);
    }

    public int insert(LoadHisdata record) {
        return loadHisdata.insert(record);
    }

    public List<LoadHisdata> selectQuery96(LoadHisdataKey key) {
        return loadHisdata.selectQuery96(key);
    }
    public List<LoadHisdata> selectQuery288(LoadHisdataKey key) {
        return loadHisdata.selectQuery288(key);
    }


    public String selectOne(LoadHisdataKey key) {
        return loadHisdata.selectOne(key);
    }

    public String selectTwo(LoadHisdataKey key) {
        return loadHisdata.selectTwo(key);
    }

    public List<LoadHisdata> find288MdateListByKey(LoadHisdataKey loadHisdataKey) {
        return loadHisdata.find288MdateListByKey(loadHisdataKey);
    }

    public List<LoadHisdata> find96MdateListByKey(LoadHisdataKey loadHisdataKey) {
        return loadHisdata.find96MdateListByKey(loadHisdataKey);
    }

    public List<LoadWeektypeload288> find288WeekListByKey(LoadWeektypeload288 loadAnalysis288) {
        return   loadWeektypeload288Mapper.selectForWeek288(loadAnalysis288);
    }

    public List<LoadWeektypeload96> find96WeekListByKey(LoadWeektypeload96 loadWeektypeload96) {
        return  loadWeektypeload96Mapper.selectForWeek96(loadWeektypeload96);
    }

    public String week96MaxEndDayServer(LoadWeektypeload96Key loadWeektypeload96) {
        return loadWeektypeload96Mapper.week96MaxEndDay(loadWeektypeload96);
    }

    public String week288MaxEndDayServer(LoadWeektypeload288Key loadWeektypeload288) {
        return loadWeektypeload288Mapper.week288MaxEndDay(loadWeektypeload288);
    }

    @Override
    public LoadHisdata selectByAreaAndMdate(LoadHisdataKey loadHisdataKey) {
        return loadHisdata.selectByAreaAndMdate(loadHisdataKey);
    }

    @Override
    public void insert(LoadWeektypeload288 record) {
        loadWeektypeload288Mapper.insert(record);
    }

    @Override
    public void insert(LoadWeektypeload96 record) {
        loadWeektypeload96Mapper.insert(record);
    }
}
