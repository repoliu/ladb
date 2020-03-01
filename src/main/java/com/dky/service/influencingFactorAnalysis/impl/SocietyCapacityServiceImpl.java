package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.SocietyCapacity;
import com.dky.entity.vo.AxisLabel;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.entity.vo.Yaxis;
import com.dky.mapper.SocietyCapacityMapper;
import com.dky.service.influencingFactorAnalysis.SocietyCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("societyCapacityService")
public class SocietyCapacityServiceImpl implements SocietyCapacityService {

    @Autowired
    private SocietyCapacityMapper societyCapacityMapper;


    public List<ChartsOption> selectQuery(String date, String area, String num, String nianXian, String anNiu) {
        SocietyCapacity societyCapacity = new SocietyCapacity();
        List<ChartsOption> chartsOptionList = new LinkedList<ChartsOption>();//返回值的集合
        List<SocietyCapacity> societyCapacityList = new LinkedList<SocietyCapacity>();
        ChartsOption chartsOption = new ChartsOption();
        String[] hengZhou = null;
        String[] shuZhou = null;
        String[] dates = null;
        //判断是否是点击4个时间按钮进入这里
        if (anNiu.equals("1")) {              //1代表是4个时间按钮
            date = date.substring(0, 4);
            societyCapacity.setMdate(date + 12);
            societyCapacity.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian) + 1) + "01");
            societyCapacity.setAreaName(area);
            societyCapacity.setId(num);

            societyCapacityList = societyCapacityMapper.selectQuery(societyCapacity);

            if (societyCapacityList.size() > 0) {
                //判断是否是同一年的数
                if (Integer.parseInt(nianXian) == 1) {
                    shuZhou = new String[societyCapacityList.size()];//Y轴的集合
                    hengZhou = new String[societyCapacityList.size()];//X轴的集合
                    for (int i = 0; i < societyCapacityList.size(); i++) {
                        hengZhou[i] = societyCapacityList.get(i).getMdate().substring(0, 6);//这是把这条曲线的X轴放进去
                        shuZhou[i] = societyCapacityList.get(i).getCapacityMon() + "";//这是把这条曲线的值放进去
                    }
                } else {
                    List<String> strList=dates(societyCapacityList);
                    shuZhou = new String[strList.size()];//Y轴的集合
                    hengZhou = new String[strList.size()];//X轴的集合
                    for (int i = 0; i < strList.size(); i++) {
                        Double dou = new Double(0);
                        for (int j = 0; j < societyCapacityList.size(); j++) {
                            //把同一年所有的月份的值加一起
                            if (strList.get(i).equals(societyCapacityList.get(j).getMdate().substring(0, 4))) {
                                dou += societyCapacityList.get(j).getCapacityMon().doubleValue();
                            }
                        }
                        hengZhou[i] = strList.get(i).substring(0, 4);//这是把这条曲线的X轴放进去
                        shuZhou[i] = dou + "";//这是把这条曲线的值放进去
                    }
                }
            }
        } else {
            //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
            String[] dateTime = new String[2];
            if (date.length() > 6) {
                dateTime = date.split("-");
                societyCapacity.setMdate(dateTime[1] + 12);
                societyCapacity.setMinDate(Integer.parseInt(dateTime[0]) + "01");
                societyCapacity.setAreaName(area);
                societyCapacity.setId(num);
            } else {
                date = date.substring(0, 4);
                societyCapacity.setMdate(date + 12);
                societyCapacity.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian) + 1) + "01");
                societyCapacity.setAreaName(area);
                societyCapacity.setId(num);
            }
            societyCapacityList = societyCapacityMapper.selectQuery(societyCapacity);
            if (societyCapacityList.size() > 0) {
                if (date.length() > 6) {
                    //判断这个年范围选择框选择的是不是同一年
                    if (Integer.parseInt(dateTime[0]) == Integer.parseInt(dateTime[1])) {
                        shuZhou = new String[societyCapacityList.size()];//Y轴的集合
                        hengZhou = new String[societyCapacityList.size()];//X轴的集合
                        for (int i = 0; i < societyCapacityList.size(); i++) {
                            hengZhou[i] = societyCapacityList.get(i).getMdate().substring(0, 6);//这是把这条曲线的X轴放进去
                            shuZhou[i] = societyCapacityList.get(i).getCapacityMon() + "";//这是把这条曲线的值放进去
                        }
                    } else {
                        List<String> strList=dates(societyCapacityList);
                        shuZhou = new String[strList.size()];//Y轴的集合
                        hengZhou = new String[strList.size()];//X轴的集合
                        for (int i = 0; i < strList.size(); i++) {
                            Double dou = new Double(0);
                            for (int j = 0; j < societyCapacityList.size(); j++) {
                                //把同一年所有的月份的值加一起
                                if (strList.get(i).equals(societyCapacityList.get(j).getMdate().substring(0, 4))) {
                                    dou += societyCapacityList.get(j).getCapacityMon().doubleValue();
                                }
                            }
                            hengZhou[i] = strList.get(i).substring(0, 4);//这是把这条曲线的X轴放进去
                            shuZhou[i] = dou + "";//这是把这条曲线的值放进去
                        }
                    }
                } else {
                    List<String> strList=dates(societyCapacityList);
                    shuZhou = new String[strList.size()];//Y轴的集合
                    hengZhou = new String[strList.size()];//X轴的集合
                    if (nianXian.equals("1")){
                        shuZhou = new String[societyCapacityList.size()];//Y轴的集合
                        hengZhou = new String[societyCapacityList.size()];//X轴的集合
                        for (int i = 0; i < societyCapacityList.size(); i++) {
                            hengZhou[i] = societyCapacityList.get(i).getMdate().substring(0, 6);//这是把这条曲线的X轴放进去
                            shuZhou[i] = societyCapacityList.get(i).getCapacityMon() + "";//这是把这条曲线的值放进去
                        }
                    }else {
                        shuZhou = new String[strList.size()];//Y轴的集合
                        hengZhou = new String[strList.size()];//X轴的集合
                        for (int i = 0; i < strList.size(); i++) {
                            Double dou = new Double(0);
                            for (int j = 0; j < societyCapacityList.size(); j++) {
                                //把同一年所有的月份的值加一起
                                if (strList.get(i).equals(societyCapacityList.get(j).getMdate().substring(0, 4))) {
                                    dou += societyCapacityList.get(j).getCapacityMon().doubleValue();
                                }
                            }
                            hengZhou[i] = strList.get(i);//这是把这条曲线的X轴放进去
                            shuZhou[i] = dou + "";//这是把这条曲线的值放进去
                        }
                    }


                }
            }
        }
        if (societyCapacityList.size() > 0) {
            Series series = new Series();//这个是数据
            series.setName("全社会用电量");
            series.setData(shuZhou);
            series.setSmooth(true);
            series.setType("line");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("亿千瓦时");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            yaxis.setAxisLabel(axisLabel);//Y轴数据加的返回数据中
            chartsOption.setYaxis(yaxis);//Y轴添加
            chartsOption.setHengZhou(hengZhou);//X轴添加
            chartsOption.setSeries(series);//数据和图像样式的添加
            chartsOptionList.add(chartsOption);
        }
        return chartsOptionList;
    }

    //把不通的年拿出来，相同的不要
    public List<String> dates(List<SocietyCapacity> list) {
        List<String> str=new LinkedList<>();
        for (int i=0;i<list.size();i++){
            if (i==0){
                str.add(list.get(i).getMdate().substring(0,4));
            }else {
                int number=0;
                for (int k=0;k<str.size();k++){
                    if (list.get(i).getMdate().substring(0,4).equals(str.get(k).substring(0,4))){
                        number++;
                    }
                }
                if (number==0){
                    str.add(list.get(i).getMdate().substring(0,4));
                }
            }
        }
        return str;
    }


    public Map selectMaxDate(String string, String id) {
        SocietyCapacity societyCapacity = new SocietyCapacity();
        if (string != null && id != null) {
            societyCapacity.setAreaName(string);
            societyCapacity.setId(id);
        }
        return societyCapacityMapper.selectMaxDate(societyCapacity);
    }

    @Override
    public int insert(SocietyCapacity societyCapacity) {
        return societyCapacityMapper.insert(societyCapacity);
    }
}
