package com.dky.service.periodicityAnalysis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dky.entity.LoadDaytrait;
import com.dky.entity.LoadHisdata;
import com.dky.entity.period.Holiday;
import com.dky.mapper.LoadDaytraitMapper;
import com.dky.mapper.LoadHisdataMapper;
import com.dky.mapper.period.CommonMapper;
import com.dky.mybaties.ResultMapHandler;
import com.dky.service.periodicityAnalysis.HolidayService;
import com.dky.util.ObjToArraysUtils;
import com.dky.util.TimePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description: 节假日service
 * @Date: Create in 10:42 2018/2/27
 */
@Service
public class HolidayServiceImpl implements HolidayService {
    private final CommonMapper commonMapper;
    private final LoadHisdataMapper loadHisdataMapper;
    private final LoadDaytraitMapper loadDaytraitMapper;
    @Autowired
    public HolidayServiceImpl(CommonMapper commonMapper,
                              LoadHisdataMapper loadHisdataMapper,
                              LoadDaytraitMapper loadDaytraitMapper){
        this.commonMapper = commonMapper;
        this.loadHisdataMapper = loadHisdataMapper;
        this.loadDaytraitMapper = loadDaytraitMapper;
    }

    /**
     * 查询出所有节假日类型
     * @return 节假日类型
     */
    @Override
    public List<String> findAllTypeOfHoliday() {
        return commonMapper.findAllTypeOfHoliday();
    }

    /**
     * 获取指定节假日的日期信息
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 该节日信息
     */
    @Override
    public List<Holiday> findHolidayByParams(String startYear, String endYear, String holiday) {
        Map<String,Object> params = new HashMap<>();
        params.put("startYear",startYear);
        params.put("endYear",endYear);
        params.put("holiday",holiday);
        return commonMapper.findHolidayByParams(params);
    }

    /**
     * 获取该节假日类型每年的负荷数据
     * @param area 地区id
     * @param holidays 日期列表
     * @return 负荷曲线数据
     */
    @Override
    public Map<String, Object> findAllLoadOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays) {
        if (holidays.size() == 0){
            return null;
        }

        Map<String,Object> options = new HashMap<>();
        List<String> legends = new ArrayList<>();
        String[] xAxis = TimePoint.create96XAxisByDays(holidays.get(0).getDays());
        List<Map<String,Object>> series = new ArrayList<>();
        int maxlength = 0;

        //通过循环，将每年的曲线数据找出来
        for (int i = 0 ; i < holidays.size() ; i++){
            Map<String,Object> s = new HashMap<>();
            Holiday h = holidays.get(i);
            String legend = h.getMyear()+"年";
            s.put("name",legend);
            s.put("smooth",true);
            s.put("type","line");

            //获取数据进行分装
            List<LoadHisdata> hisdatas = loadHisdataMapper.findOnly96ListByAreaAndTimearea(area,h.getStartday(),h.getEndday());
            String[] data = null;
            if (hisdatas.size()!=0){
                for (LoadHisdata his : hisdatas){
                    String[] temp = ObjToArraysUtils.getFieldValues(his);
                    if (data == null){
                        data = temp;
                    } else {
                        String[] re = new String[data.length+temp.length];
                        System.arraycopy(data,0,re,0,data.length);
                        System.arraycopy(temp,0,re,data.length,temp.length);
                        data = re;
                    }
                    if (data.length > maxlength){
                        maxlength = data.length;
                    }
                }

                legends.add(legend);
            } else{
                continue;
            }
            s.put("data",data);
            series.add(s);
        }
        String[] a = new String[maxlength];
        System.arraycopy(xAxis,0,a,0,maxlength);
        if (legends.size() == 0 || series.size() == 0 || xAxis.length == 0){
            return null;
        }
        options.put("legend",legends);
        options.put("xAxis",a);
        options.put("series",series);
        return options;
    }

    /**
     * 获取选定节假日的每日不同负荷数据
     * @param area 地区id
     * @param holidays 节日列表
     * @param load 负荷类型
     * @return 曲线数据
     */
    @Override
    public Map<String, Object> findLoadOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays, String load) {
        if (holidays.size() == 0){
            return null;
        }
        Map<String,Object> options = new HashMap<>();
        List<String> legends = new ArrayList<>();
        String[] xAxis = TimePoint.createDaysXAxisByDays(holidays.get(0).getDays());
        List<Map<String,Object>> series = new ArrayList<>();
        int maxlength = 0;

        //通过循环获取每年信息，封装数据
        for (int i = 0 ; i < holidays.size() ; i++){
            Map<String,Object> s = new HashMap<>();
            Holiday h = holidays.get(i);
            String legend = h.getMyear()+"年";
            s.put("name",legend);
            s.put("smooth",true);
            s.put("type","line");
            Map<String,Object> params = new HashMap<>();
            params.put("area",area);
            params.put("startday",h.getStartday());
            params.put("endday",h.getEndday());
            params.put("load",load);
            List<BigDecimal>   values = loadDaytraitMapper.findValuesByParams(params);
            if (values.size() == 0){
                continue;
            }
            if (values.size()>maxlength){
                maxlength = values.size();
            }
            legends.add(legend);
            String[] data = new String[values.size()];
            for (int j = 0 ; j < values.size();j++){
                data[j] = values.get(j).toString();
            }
            s.put("data",data);
            series.add(s);
        }
        String[] a = new String[maxlength];
        System.arraycopy(xAxis,0,a,0,maxlength);
        if (legends.size() == 0 || series.size() == 0 || xAxis.length == 0){
            return null;
        }
        options.put("legend",legends);
        options.put("xAxis",a);
        options.put("series",series);
        return options;
    }

    /**
     * 获取选定节假日的增长率数据
     * @param area 地区id
     * @param holidays 节日列表
     * @param load 负荷类型
     * @return 曲线数据
     */
    @Override
    public Map<String, Object> findLoadUpOptionsByAreaAndHolidays(Integer area, List<Holiday> holidays, String load) {
        if (holidays.size() == 0){
            return null;
        }
        Map<String,Object> options = new HashMap<>();
        List<String> legends = new ArrayList<>();
        String[] xAxis = TimePoint.createDaysXAxisByDays(holidays.get(0).getDays());
        List<Map<String,Object>> series = new ArrayList<>();
        int maxlength = 0;
        List<BigDecimal> temp = null;

        //封装每年的数据
        for (int i = 0 ; i < holidays.size() ; i++){
            Holiday h = holidays.get(i);
            String legend = h.getMyear()+"年";
            Map<String,Object> params = new HashMap<>();
            params.put("area",area);
            params.put("startday",h.getStartday());
            params.put("endday",h.getEndday());
            params.put("load",load);
            List<BigDecimal>   values = loadDaytraitMapper.findValuesByParams(params);
            if (values.size() == 0){
                continue;
            }
            if (values.size()>maxlength){
                maxlength = values.size();
            }
            if (temp == null){
                temp = values;
            } else {
                Map<String,Object> s2 = new HashMap<>();
                s2.put("name",legend+"增长率");
                s2.put("smooth",true);
                s2.put("type","line");
                legends.add(legend+"增长率");
                String[] data2 = new String[h.getDays()];
                for (int j = 0;j < data2.length;j++){
                    if (temp.size() > j && values.size()>j){
                        data2[j] = values.get(j).subtract(temp.get(j)).divide(temp.get(j),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString();
                    } else {
                        data2[j] = null;
                    }
                }
                temp = values;
                s2.put("data",data2);
                series.add(s2);
            }
        }
        String[] a = new String[maxlength];
        System.arraycopy(xAxis,0,a,0,maxlength);
        if (legends.size() == 0 || series.size() == 0 || xAxis.length == 0){
            return null;
        }
        options.put("legend",legends);
        options.put("xAxis",a);
        options.put("series",series);
        return options;
    }

    /**
     * 获取holiday表中开始年份和结束年份
     * @return 开始和结束年份
     */
    @Override
    public Map<String, String> findStartAndEndYear() {
        return commonMapper.findStartAndEndYear();
    }
}
