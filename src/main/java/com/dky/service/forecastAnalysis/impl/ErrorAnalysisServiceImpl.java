package com.dky.service.forecastAnalysis.impl;

import com.dky.entity.*;
import com.dky.entity.influ.Yaxis;
import com.dky.entity.vo.MarkLine;
import com.dky.entity.vo.Series;
import com.dky.mapper.*;
import com.dky.service.forecastAnalysis.ErrorAnalysisService;
import com.dky.util.ObjToArraysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("errorAnalysisService")
public class ErrorAnalysisServiceImpl implements ErrorAnalysisService {

    @Autowired
    private LoadHisdataMapper loadHisdataMapper;

    @Autowired
    private LoadForesultMapper loadForesultMapper;

    @Autowired
    private LoadDayerr96Mapper loadDayerr96Mapper;

    @Autowired
    private LoadDayerrvalueMapper loadDayerrvalueMapper;

    @Autowired
    private LoadShortdayerrAllMapper loadShortdayerrAllMapper;

    @Override
    public LoadDayerrvalue selectAreaDate(int area, String date) {
        return loadDayerrvalueMapper.selectAreaDate(area,date);
    }

    @Override
    public List<LoadShortdayerrAll> findLoadShortdayerr(int area, String date) {
        return loadShortdayerrAllMapper.selectLoadShortdayerr(area,date);
    }

    @Override
    public Map selectContrastCurve(String area, String date) {
        Map<String, Object> mapList = new HashMap<>();
        List<String> stringList=new ArrayList<>();
        List<Series> listSeries=new ArrayList<>();
        String [] list=new String[97];
        String [] list1=new String[97];
        //这个时用来确定曲线最大值的
        double number=0.0;
        //这个查的是实际的数据
        LoadHisdata loadHisdata = loadHisdataMapper.selectContrastCurve(Integer.parseInt(area), date);
        if (loadHisdata == null ) {
        }else {
            list=ObjToArraysUtils.getFieldValues(loadHisdata);
            for (int i=0;i<list.length;i++){
                if (Double.parseDouble(list[i])>number){
                    number=Double.parseDouble(list[i]);
                }
            }
        }
        //这个查的是预测的数据
        LoadForesult listText = loadForesultMapper.selectContrastCurve(Integer.parseInt(area), date);
        if (listText == null ) {
        }else {
            list1=ObjToArraysUtils.getFieldValues(listText);
            for (int i=0;i<list1.length;i++){
                if (Double.parseDouble(list1[i])>number){
                    number=Double.parseDouble(list1[i]);
                }
            }
        }



        if (loadHisdata!=null){
            stringList.add("实际曲线");
            Series series3=new Series();
            series3.setName("实际曲线");
            series3.setType("line");
            series3.setData(list);
            series3.setSmooth(true);
            listSeries.add(series3);
        }
        if (listText!=null){
            stringList.add("预测曲线");
            Series series3=new Series();
            series3.setName("预测曲线");
            series3.setType("line");
            series3.setSmooth(true);
            series3.setData(list1);
            listSeries.add(series3);
        }
        if(stringList!=null&&stringList.size()>0){
            mapList.put("legends",stringList);
            mapList.put("listSeries",listSeries);
            String numberStr="";
            number=number*1.3;
            numberStr=(number+"").substring(0,(number+"").toString().indexOf("."));
            mapList.put("number",numberStr);
        }
        return mapList;
    }


    @Override
    public Map selectAreaDate(String area, String date) {
        Map<String, Object> mapList = new HashMap<>();

        //                                                                  这个地方只把年份传进去
        List<LoadDayerr96> list = loadDayerr96Mapper.selectAreaDate(area, date);
        String [] list1 = new String[97];//实际负荷值
        String [] list2 = new String[97];//预测负荷值
        String [] list3 = new String[97];//96个点误差值
        for (int i = 0; i < list.size(); i++) {
            list1[i]=(list.get(i).getLoadact().toString());
            list2[i]=(list.get(i).getLoadfor().toString());
            list3[i]=(list.get(i).getErr().toString());
        }
        if (list != null && list.size() > 0) {
            Series series3=new Series();
            series3.setName("误差曲线");
            series3.setType("line");
            series3.setData(list3);
            Yaxis yaxis1=new Yaxis();
            Yaxis yaxis2=new Yaxis();
            yaxis1.setyAxis(5);
            yaxis2.setyAxis(-5);
            MarkLine markLine=new MarkLine();
            markLine.setSilent(true);
            List<Yaxis> yaxisList=new ArrayList<>();
            yaxisList.add(yaxis2);
            yaxisList.add(yaxis1);
            markLine.setData(yaxisList);
            series3.setMarkLine(markLine);

            mapList.put("series3",series3);
            mapList.put("list1",list1);
            mapList.put("list2",list2);
            mapList.put("list3",list3);
        }

         return mapList;
    }


}
