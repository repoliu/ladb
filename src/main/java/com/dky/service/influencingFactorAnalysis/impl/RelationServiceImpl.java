package com.dky.service.influencingFactorAnalysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.dky.controller.periodicityAnalysis.LoadWeektraitController;
import com.dky.entity.RelationSupport;
import com.dky.entity.influ.vo.WeatherResult;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.mapper.DbareaMapper;
import com.dky.mapper.RelationSupportMapper;
import com.dky.service.influencingFactorAnalysis.RelationService;
import com.dky.util.TimePoint;
import com.dky.util.daoOperate.MultipleDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:04 2018/1/17
 */
@Service("relationService")
public class RelationServiceImpl implements RelationService {
    private final RelationSupportMapper relationSupportMapper;
    private final DbareaMapper dbareaMapper;

    private String datasource = "dataSource2";

    Logger logger = Logger.getLogger(RelationServiceImpl.class.getName());

    @CacheEvict(beforeInvocation=true)
    public static void CacheEvict() {}



    @Autowired
    public RelationServiceImpl(RelationSupportMapper relationSupportMapper,
                               DbareaMapper dbareaMapper) {
        this.relationSupportMapper = relationSupportMapper;
        this.dbareaMapper = dbareaMapper;
    }

    /**
     * 通过地区id找到数据库中最新记录
     * @param area 地区id
     * @return 日期
     */
    @Override
    public String findLastMdateByArea(Integer area) {
        return relationSupportMapper.findLastMdateByArea(area);
    }

    @Override
    public void setResultByParams(WeatherResult result, Map<String, String> params,String wenDu,String jiangYu) throws ParseException {
        result.setYearButton(TimePoint.createTimeButtonValueForYear(result.getYear()));
        //str这个参数XLY加的，这个是用来判断单选按钮的，穿过来的值是-空-温度-最低温度，这3个中的一个
        if (wenDu!=null&wenDu!=""){
        //    params.put("type", wenDu);
        }else {
            wenDu="温度";
         //   params.put("type", wenDu);
        }
        List<RelationSupport> tempList= new ArrayList<>();
        List<RelationSupport> humdList= new ArrayList<>();
        List<RelationSupport> cosnList= new ArrayList<>();
        List<RelationSupport> rainList= new ArrayList<>();
        try {
            //List<RelationSupport> tempList = relationSupportMapper.findYearSupportByParams(params);
            tempList = relationSupportMapper.selectTypeX(Integer.parseInt(params.get("area")),params.get("startYear"),params.get("endYear"),wenDu);
            // params.put("type", "湿度");
            // List<RelationSupport> humdList = relationSupportMapper.findYearSupportByParams(params);
            humdList = relationSupportMapper.selectTypeX(Integer.parseInt(params.get("area")),params.get("startYear"),params.get("endYear"),"湿度");
            String a="ss=''''";
            //params.put("type", "人体舒适度");
            // List<RelationSupport> cosnList = relationSupportMapper.findYearSupportByParams(params);
            cosnList = relationSupportMapper.selectTypeX(Integer.parseInt(params.get("area")),params.get("startYear"),params.get("endYear"),"人体舒适度");
            if (jiangYu!=null&jiangYu!=""){
                //params.put("type", jiangYu);
            }else {
                jiangYu="降雨量";
               // params.put("type", jiangYu);
            }
            //  List<RelationSupport> rainList = relationSupportMapper.findYearSupportByParams(params);
            rainList = relationSupportMapper.selectTypeX(Integer.parseInt(params.get("area")),params.get("startYear"),params.get("endYear"),jiangYu);

            if (tempList.size() > 0) {
                result.setAreaname(tempList.get(0).getAreaname());
            } else if (humdList.size() > 0) {
                result.setAreaname(humdList.get(0).getAreaname());
            } else if (cosnList.size() > 0) {
                result.setAreaname(cosnList.get(0).getAreaname());
            } else if (rainList.size() > 0) {
                result.setAreaname(rainList.get(0).getAreaname());
            } else {
                result.setAreaname(dbareaMapper.findAreaname(result.getArea()));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (wenDu!=null&wenDu!=""){
            result.setTempRelations(setOptions(tempList, wenDu));
        }else {
            result.setTempRelations(setOptions(tempList, "温度"));
        }

        result.setHumdRelations(setOptions(humdList, "湿度"));
        result.setCosnRelations(setOptions(cosnList, "人体舒适度"));
        result.setRainRelations(setOptions(rainList, "降雨量"));
    }

    //通过参数生成曲线图数据
    private String setOptions(List<RelationSupport> list, String type) {
        List<ChartsOption> result = new ArrayList<>();
        ChartsOption chartsOption = new ChartsOption();
        Series series = new Series();
        String legend = type;
        String echartType = "bar";
        String[] hengZhou = new String[list.size()];
        String[] data = new String[list.size()];
        int index = 0;
        for (RelationSupport r : list) {
            hengZhou[index] = r.getMdate().substring(4, 6) + "月";
            data[index] = r.getRelationDegree().intValue() + "";
            index++;
        }
        series.setName(legend);
        series.setData(data);
        series.setType(echartType);
        chartsOption.setSeries(series);
        chartsOption.setLegend(legend);
        chartsOption.setHengZhou(hengZhou);
        result.add(chartsOption);
        return JSONArray.toJSONString(result);
    }
}
