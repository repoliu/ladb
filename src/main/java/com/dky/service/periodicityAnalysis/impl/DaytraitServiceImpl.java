package com.dky.service.periodicityAnalysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.*;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.DaytraitResult;
import com.dky.entity.vo.Series;
import com.dky.entity.vo.YearTraitResult;
import com.dky.mapper.LoadDaytraitMapper;
import com.dky.mapper.LoadHisdataMapper;
import com.dky.service.periodicityAnalysis.DaytraitService;
import com.dky.util.ObjToArraysUtils;
import com.dky.util.DateUtils;
import com.dky.util.TimePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author YangSL
 * @PackageName: com.dky.service.periodicityAnalysis.impl
 * @Description: 日分析页功能模块
 * @date Create in 2017/10/16 16:15
 */
@Service("daytraitService")
public class DaytraitServiceImpl implements DaytraitService {
    private final LoadDaytraitMapper loadDaytraitMapper;
    private final LoadHisdataMapper loadHisdataMapper;

    @Autowired
    public DaytraitServiceImpl(LoadDaytraitMapper loadDaytraitMapper,
                               LoadHisdataMapper loadHisdataMapper) {
        this.loadHisdataMapper = loadHisdataMapper;
        this.loadDaytraitMapper = loadDaytraitMapper;
    }

    @Override
    public LoadDaytrait selectJinDuTiao(LoadDaytraitKey key) {
        return loadDaytraitMapper.selectJinDuTiao(key);
    }

    @Override
    public DaytraitResult findExtremaMdateByKey(LoadDaytraitKey loadDaytraitKey) {
        return loadDaytraitMapper.findExtremaMdateByKey(loadDaytraitKey);
    }

    @Override
    public List<LoadDaytrait> selectQuery(LoadDaytraitKey key) {
        return loadDaytraitMapper.selectQuery(key);
    }

    @Override
    public List<LoadDaytrait> selectQuery2(LoadDaytraitKey key) {
        return loadDaytraitMapper.selectQuery2(key);
    }

    @Override
    public YearTraitResult findExtremaYearByArea(int area) {
        return loadDaytraitMapper.findExtremaYearByArea(area);
    }

    @Override
    public LoadYeartrait findYeartraitByKey(LoadYeartraitKey loadYeartraitKey) {
        return loadDaytraitMapper.findYeartraitByKey(loadYeartraitKey);
    }

    @Override
    public List<LoadDaytrait> findChartsDataByKey(LoadYeartraitKey loadYeartraitKey) {
        return loadDaytraitMapper.findChartsDataByKey(loadYeartraitKey);
    }

    @Override
    public LoadWeektrait selectForWeekIndex(LoadDaytraitKey loadDaytraitKey) {
        return loadDaytraitMapper.selectForWeekByKey(loadDaytraitKey);
    }

    @Override
    public String getDayMaxDate(LoadDaytraitKey key) {
        return loadDaytraitMapper.dayMaxDate(key);
    }

    @Override
    public LoadDaytrait selectMaxMinAvgSum(LoadDaytraitKey key) {
        return loadDaytraitMapper.selectMaxMinAvgSum(key);
    }

    /**
     * 获取日负荷特性
     *
     * @param params 参数
     * @return 负荷特性数据
     */
    @Override
    public LoadDaytrait findDayTraitByParams(Map<String, Object> params) {
        LoadDaytrait loadDaytrait;
        if (params.get("mdate") == null) {
            loadDaytrait = loadDaytraitMapper.findLastDayByArea((int) params.get("area"));
            if (loadDaytrait == null) {
                params.put("mdate", DateUtils.format(new Date(), DateUtils.YMDN_P));
            } else {
                params.put("mdate", loadDaytrait.getMdate());
            }
        } else {
            params.put("mdate", ((String) params.get("mdate")).replace("-", ""));
            loadDaytrait = loadDaytraitMapper.selectByParams(params);
            if (loadDaytrait == null) {
                loadDaytrait = selectDayByParams(params);
            }
        }
        return loadDaytrait;
    }

    /**
     * 获取日负荷曲线图数据
     *
     * @param params 参数
     * @return 结果
     * @throws ParseException
     */
    @Override
    public String createChartsByParams(Map<String, Object> params) throws ParseException {
        List<LoadHisdata> hisdataList;
        int point = (int) params.get("point");
        hisdataList = loadHisdataMapper.findListByParams(params);
        List<ChartsOption> chartsOptionList = new ArrayList<>();
        String[] hengZhou = TimePoint.timeCreate(point);
        for (LoadHisdata lh : hisdataList) {
            String legend = DateUtils.format(DateUtils.parse(lh.getMdate(), DateUtils.YMDN_P), DateUtils.YMDH_P);
            String[] data = ObjToArraysUtils.getFieldValues(lh);
            data = Arrays.copyOfRange(data, 0, data.length - 1);
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].substring(0, data[i].indexOf("."));
            }
            ChartsOption chartsOption = setOptions(legend, hengZhou, data);
            chartsOptionList.add(chartsOption);
        }
        return JSONArray.toJSONString(chartsOptionList);
    }

    @Override
    public int insert(LoadDaytrait record) {
        return loadDaytraitMapper.insert(record);
    }

    @Override
    public int selectLoadHisdataToLoadDaytrait(String areas, String mdate) {
        int number = 0;
        if (areas!=null){
            int area =Integer.parseInt(areas);
            number = loadDaytraitMapper.selectLoadDaytraitTF(area, mdate.substring(0, 4));
            if (number ==0) {
                List<LoadHisdata> list = new ArrayList<>();
                list = loadHisdataMapper.selectLoadHisdataToLoadDaytrait(area, mdate.substring(0, 4));

                number=list.size();
                for (int p=0;p<list.size();p++){
                    LoadHisdata hisdata = list.get(p);
                    Object[] obj = ObjToArraysUtils.getFieldValuesObject(hisdata);

                    BigDecimal id = hisdata.getId();
                    BigDecimal maxload = new BigDecimal(0), minload = new BigDecimal(99999999), fmmaxload = new BigDecimal(0),
                            pmmaxload = new BigDecimal(0), aveload, totalload = new BigDecimal(0), loadrate,
                            minloadrate, differ, differrate;
                    String maxtime, mintime, fmmaxtime, pmmaxtime;
                    int maxtimeI = 0, mintimeI = 0, fmmaxtimeI = 0, pmmaxtimeI = 0;
                    Date verTime = new Date();
                    BigDecimal[] big = Arrays.copyOfRange(obj, 1, obj.length - 1, BigDecimal[].class);
                    for (int i = 0; i < big.length; i++) {
                        totalload = totalload.add(big[i]);
                        BigDecimal tempMaxload = maxload, tempMinload = minload;
                        maxload = maxload.max(big[i]);
                        if (tempMaxload.compareTo(maxload) < 0) {
                            maxtimeI = i;
                        }
                        minload = minload.min(big[i]);
                        if (tempMinload.compareTo(minload) > 0) {
                            mintimeI = i;
                        }
                        if (i <= 720) {
                            BigDecimal tempfmmaxload = fmmaxload;
                            fmmaxload = fmmaxload.max(big[i]);
                            if (tempfmmaxload.compareTo(fmmaxload) < 0) {
                                fmmaxtimeI = i;
                            }
                        } else {
                            BigDecimal temppmmaxload = pmmaxload;
                            pmmaxload = pmmaxload.max(big[i]);
                            if (temppmmaxload.compareTo(pmmaxload) < 0) {
                                pmmaxtimeI = i;
                            }
                        }
                    }
                    maxtime = TimePoint.createTimeByPoint(maxtimeI, 1440);
                    mintime = TimePoint.createTimeByPoint(mintimeI, 1440);
                    fmmaxtime = TimePoint.createTimeByPoint(fmmaxtimeI, 1440);
                    pmmaxtime = TimePoint.createTimeByPoint(pmmaxtimeI, 1440);
                    if(big.length!=0){
                        aveload = totalload.divide(new BigDecimal(big.length), 4);
                    }else{
                        aveload = new BigDecimal(0);
                    }
                    if(maxload.compareTo(BigDecimal.ZERO)!=0){
                        loadrate = aveload.divide(maxload, 4).scaleByPowerOfTen(2);
                    }else{
                        loadrate = new BigDecimal(0);
                    }
                    if(maxload.compareTo(BigDecimal.ZERO)!=0){
                        minloadrate = minload.divide(maxload, 4).scaleByPowerOfTen(2);
                    }else{
                        minloadrate = new BigDecimal(0);
                    }
                    differ = maxload.subtract(minload);
                    if(maxload.compareTo(BigDecimal.ZERO)!=0){
                        differrate = differ.divide(maxload, 4).scaleByPowerOfTen(2);
                    }else{
                        differrate = new BigDecimal(0);
                    }

                    LoadDaytrait loadDaytrait = new LoadDaytrait();
                    loadDaytrait.setArea(area);
                    loadDaytrait.setId(id);
                    loadDaytrait.setMdate(hisdata.getMdate());
                    loadDaytrait.setMaxload(maxload);
                    loadDaytrait.setMaxtime(maxtime);
                    loadDaytrait.setMinload(minload);
                    loadDaytrait.setMintime(mintime);
                    loadDaytrait.setFmmaxload(fmmaxload);
                    loadDaytrait.setFmmaxtime(fmmaxtime);
                    loadDaytrait.setPmmaxload(pmmaxload);
                    loadDaytrait.setPmmaxtime(pmmaxtime);
                    loadDaytrait.setAveload(aveload);
                    loadDaytrait.setLoadrate(loadrate);
                    loadDaytrait.setMinloadrate(minloadrate);
                    loadDaytrait.setDiffer(differ);
                    loadDaytrait.setDifferrate(differrate);
                    loadDaytrait.setVerTime(verTime);
                    loadDaytrait.setMin(1);
                    loadDaytraitMapper.insert(loadDaytrait);
                }

            }
        }

        return number;
    }

    /**
     * 查询hisdata表，并生成daytarit，插入到daytrait表中
     *
     * @param params 参数
     * @return 返回结果
     */
    private LoadDaytrait selectDayByParams(Map<String, Object> params) {
        LoadHisdata hisdata = loadHisdataMapper.selectDayByParams(params);
        if (hisdata == null) {
            return null;
        }
        Object[] obj = ObjToArraysUtils.getFieldValuesObject(hisdata);
        int area = hisdata.getArea();
        BigDecimal id = hisdata.getId();
        String mdate = hisdata.getMdate();
        /*BigDecimal maxload = new BigDecimal(0), minload = new BigDecimal(99999999), fmmaxload = new BigDecimal(0),
                pmmaxload = new BigDecimal(0), aveload, totalload = new BigDecimal(0), loadrate,
                minloadrate, differ, differrate;
        String maxtime, mintime, fmmaxtime, pmmaxtime;
        int maxtimeI = 0, mintimeI = 0, fmmaxtimeI = 0, pmmaxtimeI = 0;
        Date verTime = new Date();
        BigDecimal[] big = Arrays.copyOfRange(obj, 1, obj.length - 1, BigDecimal[].class);
        for (int i = 0; i < big.length; i++) {
            totalload = totalload.add(big[i]);
            BigDecimal tempMaxload = maxload, tempMinload = minload;
            maxload = maxload.max(big[i]);
            if (tempMaxload.compareTo(maxload) < 0) {
                maxtimeI = i;
            }
            minload = minload.min(big[i]);
            if (tempMinload.compareTo(minload) > 0) {
                mintimeI = i;
            }
            if (i <= 720) {
                BigDecimal tempfmmaxload = fmmaxload;
                fmmaxload = fmmaxload.max(big[i]);
                if (tempfmmaxload.compareTo(fmmaxload) < 0) {
                    fmmaxtimeI = i;
                }
            } else {
                BigDecimal temppmmaxload = pmmaxload;
                pmmaxload = pmmaxload.max(big[i]);
                if (temppmmaxload.compareTo(pmmaxload) < 0) {
                    pmmaxtimeI = i;
                }
            }
        }
        if (maxload.compareTo(new BigDecimal(0)) == 0) {
            return null;
        }
        maxtime = TimePoint.createTimeByPoint(maxtimeI, 1440);
        mintime = TimePoint.createTimeByPoint(mintimeI, 1440);
        fmmaxtime = TimePoint.createTimeByPoint(fmmaxtimeI, 1440);
        pmmaxtime = TimePoint.createTimeByPoint(pmmaxtimeI, 1440);
        aveload = totalload.divide(new BigDecimal(big.length), 4);
        loadrate = aveload.divide(maxload, 4).scaleByPowerOfTen(2);
        minloadrate = minload.divide(maxload, 4).scaleByPowerOfTen(2);
        differ = maxload.subtract(minload);
        differrate = differ.divide(maxload, 4).scaleByPowerOfTen(2);*/
        LoadDaytrait loadDaytrait = new LoadDaytrait();

        loadDaytrait= loadDaytraitMapper.selectOne(area,mdate);
/*        loadDaytrait.setArea(area);
        loadDaytrait.setId(id);
        loadDaytrait.setMdate(mdate);
        loadDaytrait.setMaxload(maxload);
        loadDaytrait.setMaxtime(maxtime);
        loadDaytrait.setMinload(minload);
        loadDaytrait.setMintime(mintime);
        loadDaytrait.setFmmaxload(fmmaxload);
        loadDaytrait.setFmmaxtime(fmmaxtime);
        loadDaytrait.setPmmaxload(pmmaxload);
        loadDaytrait.setPmmaxtime(pmmaxtime);
        loadDaytrait.setAveload(aveload);
        loadDaytrait.setLoadrate(loadrate);
        loadDaytrait.setMinloadrate(minloadrate);
        loadDaytrait.setDiffer(differ);
        loadDaytrait.setDifferrate(differrate);
        loadDaytrait.setVerTime(verTime);
        loadDaytrait.setMin(1);*/
        //loadDaytraitMapper.insert(loadDaytrait);
        return  loadDaytrait;
    }

    //根据参数生成图表数据
    private ChartsOption setOptions(String legend, String[] hengZhou, String[] data) {
        ChartsOption chartsOption = new ChartsOption();
        Series series = new Series();
        chartsOption.setLegend(legend);
        chartsOption.setHengZhou(hengZhou);
        series.setType("line");
        series.setSmooth(true);
        series.setName(legend);
        series.setData(data);
        chartsOption.setSeries(series);
        return chartsOption;
    }


    @Override
    public String selectMaxDate(int area) {
        return loadDaytraitMapper.selectMaxDate(area);
    }

    @Override
    public String selectMaxDateString(int area, String mdate) {
        return loadDaytraitMapper.selectMaxDateString(area,mdate);
    }
}
