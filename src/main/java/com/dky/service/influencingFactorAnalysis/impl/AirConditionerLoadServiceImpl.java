package com.dky.service.influencingFactorAnalysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.IndustryStructure;
import com.dky.entity.LoadAirconditionerAnalysis;
import com.dky.entity.influ.vo.WeatherResult;
import com.dky.entity.vo.*;
import com.dky.mapper.AirConditionerLoadMapper;
import com.dky.mapper.DbareaMapper;
import com.dky.service.influencingFactorAnalysis.AirConditionerLoadService;
import com.dky.util.TimePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("airConditionerLoadService")
public class AirConditionerLoadServiceImpl implements AirConditionerLoadService {

    @Autowired
    private AirConditionerLoadMapper airConditionerLoadMapper;

    @Autowired
    private DbareaMapper dbareaMapper;


    @Override
    public String findLastMdateByArea(Integer area) {
        return airConditionerLoadMapper.findLastMdateByArea(area);
    }

    @Override
    public void setResultByParams(WeatherResult result, Map<String, String> params) throws ParseException {
        result.setYearButton(TimePoint.createTimeButtonValueForYear(result.getYear()));
        List<LoadAirconditionerAnalysis> loadList= new ArrayList<>();
        List<LoadAirconditionerAnalysis> maxLoadList= new ArrayList<>();
        try {
            loadList = airConditionerLoadMapper.selectMaxLoad(Integer.parseInt(params.get("area")),params.get("year"));
            maxLoadList = airConditionerLoadMapper.selectMaxLoadByLoadDaytrait(Integer.parseInt(params.get("area")),params.get("year"));
            result.setAreaname(dbareaMapper.findAreaname(result.getArea()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCosnRelations(setOptions(loadList, "空调负荷占比最大日最大负荷"));
        result.setRainRelations(setOptions(maxLoadList, "年最大负荷"));
    }

    @Override
    public List<ChartsOption> setMaxProportion(WeatherResult result, Map<String, String> params) throws ParseException {
        result.setYearButton(TimePoint.createTimeButtonValueForYear(result.getYear()));
        List<ChartsOption> chartsOptions = new LinkedList<>();
        String nameAll = "全部负荷";
        double numberAll = 100.00;
        List<LoadAirconditionerAnalysis> proportionList= new ArrayList<>();
        try {
            proportionList = airConditionerLoadMapper.selectMaxProportion(Integer.parseInt(params.get("area")),params.get("year"));
            result.setAreaname(dbareaMapper.findAreaname(result.getArea()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] string0 = {"#fd87ab", "#34abec"};
        for (LoadAirconditionerAnalysis val : proportionList) {
            double valResult = val.getResult().doubleValue();
            chartsOptions.add(setChartsOption(numberAll, valResult, "空调负荷最大占比", nameAll, string0));
        }
        return chartsOptions;
    }

    @Override
    public List<ChartsOption> setMaxEnergy(Map<String, String> params) {
        ChartsOption option1 = new ChartsOption();//代表了空调负荷占比最大日电量
        ChartsOption option2 = new ChartsOption();//代表了空调负荷最大电量
        List<ChartsOption> list = new LinkedList<ChartsOption>();

        List<LoadAirconditionerAnalysis> airconditionerAnalysisList1= new LinkedList<>();
        List<LoadAirconditionerAnalysis> airconditionerAnalysisList2= new LinkedList<>();

        airconditionerAnalysisList1=airConditionerLoadMapper.selectMaxEnergyByDay(Integer.parseInt(params.get("area")),params.get("year"));
        airconditionerAnalysisList2=airConditionerLoadMapper.selectMaxEnergy(Integer.parseInt(params.get("area")),params.get("year"));

        if (airconditionerAnalysisList1.size()>0) {
            String[] hengZhou = new String[airconditionerAnalysisList1.size()];//X轴的集合
            String[] shuZhou1 = new String[airconditionerAnalysisList1.size()];//Y轴值的集合----代表了空调负荷占比最大日电量
            String[] shuZhou2 = new String[airconditionerAnalysisList1.size()];//Y轴值的集合----代表了空调负荷最大电量

            for (int i = 0; i < airconditionerAnalysisList1.size(); i++) {
                hengZhou[i] = airconditionerAnalysisList1.get(i).getMdate();
                shuZhou1[i] = airconditionerAnalysisList1.get(i).getResult().toString();
            }
            for (int i = 0; i < airconditionerAnalysisList2.size(); i++) {
                shuZhou2[i] = airconditionerAnalysisList2.get(i).getResult().toString();
            }

            Series series1 = new Series();
            series1.setName("空调负荷占比最大日电量");
            series1.setData(shuZhou1);
            series1.setSmooth(true);
            series1.setType("bar");

            Series series2 = new Series();
            series2.setName("空调负荷最大电量");
            series2.setData(shuZhou2);
            series2.setSmooth(true);
            series2.setType("bar");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("WM.h");
            yaxis.setAxisLabel(axisLabel);//Y轴的输出格式加到Y轴中

            option1.setYaxis(yaxis);//Y轴添加
            option1.setHengZhou(hengZhou);//X轴添加
            option1.setSeries(series1);//数据和图像样式的添加

            option2.setYaxis(yaxis);//Y轴添加
            option2.setHengZhou(hengZhou);//X轴添加
            option2.setSeries(series2);//数据和图像样式的添加

            list.add(option1);
            list.add(option2);
        }
        return list;
    }

    @Override
    public String selectLoadByLoadDaytrait(Map<String, String> params,int choose) {
        List<LoadAirconditionerAnalysis> loadList= new ArrayList<>();
        String setOptions = null;
        try {
            if (choose == 1){
                loadList = airConditionerLoadMapper.selectMaxLoadByLoadDaytrait(Integer.parseInt(params.get("area")),params.get("year"));
                setOptions = setOptions(loadList, "年最大负荷");
            }else {
                loadList = airConditionerLoadMapper.selectMinLoadByLoadDaytrait(Integer.parseInt(params.get("area")),params.get("year"));
                setOptions = setOptions(loadList, "年最小负荷");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setOptions;
    }
    @Override
    public String selectMaxLoad(Map<String, String> params) {
        List<LoadAirconditionerAnalysis> loadList= new ArrayList<>();
        String setOptions = null;
        try {
            loadList = airConditionerLoadMapper.selectMaxLoad(Integer.parseInt(params.get("area")),params.get("year"));
            setOptions = setOptions(loadList, "空调负荷占比最大日最大负荷");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setOptions;
    }

    //通过参数生成柱状图数据
    private String setOptions(List<LoadAirconditionerAnalysis> list, String type) {
        List<ChartsOption> result = new ArrayList<>();
        ChartsOption chartsOption = new ChartsOption();
        Series series = new Series();
        String legend = type;
        String echartType = "bar";
        String[] hengZhou = new String[list.size()];
        String[] data = new String[list.size()];
        int index = 0;
        for (LoadAirconditionerAnalysis r : list) {
            hengZhou[index] = r.getMdate() + "年";
            data[index] = r.getResult().intValue() + "";
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
    //通过参数生成饼状图数据
    private ChartsOption setChartsOption(double numberAll, double numberValue, String type, String typeName, String[] color) {
        ChartsOption chartsOption = new ChartsOption();
        String[] str = {type, typeName};
        chartsOption.setHengZhou(str);

        List<BusNameValue> list = new LinkedList<>();
        BusNameValue valueOne = new BusNameValue();
        BusNameValue valueTwo = new BusNameValue();

        //给第一个赋值
        valueOne.setName(type);
        valueOne.setValue(numberValue + "");
        //给第二个赋值
        valueTwo.setName(typeName);
        valueTwo.setValue((numberAll - numberValue) + "");

        list.add(valueOne);
        list.add(valueTwo);

        chartsOption.setNameValueList(list);
        chartsOption.setColor(color);

        return chartsOption;
    }
}
