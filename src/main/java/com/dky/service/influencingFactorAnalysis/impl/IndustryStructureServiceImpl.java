package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.IndustryStructure;
import com.dky.entity.vo.*;
import com.dky.mapper.IndustryStructureMapper;
import com.dky.service.influencingFactorAnalysis.IndustryStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("industryStructure")
public class IndustryStructureServiceImpl  implements IndustryStructureService {


    @Autowired
    private IndustryStructureMapper industryStructureMapper;

    public     List<ChartsOption> selectQuery(String area, String date, String nianXian) {
        ChartsOption option = new ChartsOption();//代表了第一产业
        ChartsOption option1 = new ChartsOption();//代表了第二产业
        ChartsOption option2 = new ChartsOption();//代表了第三产业
        List<ChartsOption> list = new LinkedList<ChartsOption>();

        IndustryStructure industryStructure=new IndustryStructure();
        List<IndustryStructure> industryStructureList=new LinkedList<IndustryStructure>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            industryStructure.setAreaName(area);
            industryStructure.setmDate(dateTime[1]);
            industryStructure.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1])-Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            industryStructure.setAreaName(area);
            industryStructure.setmDate(date);
            industryStructure.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1) + "");
        }
        industryStructureList=industryStructureMapper.selectQuery(industryStructure);

        if (industryStructureList.size()>0) {
            String[] hengZhou = new String[industryStructureList.size()];//X轴的集合
            String[] shuZhou = new String[industryStructureList.size()];//Y轴值的集合----代表了第一产业
            String[] shuZhou1 = new String[industryStructureList.size()];//Y轴值的集合----代表了第二产业
            String[] shuZhou2 = new String[industryStructureList.size()];//Y轴值的集合----代表了第三产业

            for (int i = 0; i < industryStructureList.size(); i++) {
                hengZhou[i] = industryStructureList.get(i).getmDate();
                shuZhou[i] = industryStructureList.get(i).getPrimary().toString();
                shuZhou1[i] = industryStructureList.get(i).getSecond().toString();
                shuZhou2[i] = industryStructureList.get(i).getTertiary().toString();
            }

            Series series = new Series();//这个是第一产业数据
            series.setName("第一产业");
            series.setData(shuZhou);
            series.setSmooth(true);
            series.setType("bar");

            Series series1 = new Series();//这个是第二产业数据
            series1.setName("第二产业");
            series1.setData(shuZhou1);
            series1.setSmooth(true);
            series1.setType("bar");

            Series series2 = new Series();//这个是第三产业数据
            series2.setName("第三产业");
            series2.setData(shuZhou2);
            series2.setSmooth(true);
            series2.setType("bar");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}%");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName(industryStructureList.get(0).getUnit());
            yaxis.setAxisLabel(axisLabel);//Y轴的输出格式加到Y轴中

            option.setYaxis(yaxis);//Y轴添加
            option.setHengZhou(hengZhou);//X轴添加
            option.setSeries(series);//数据和图像样式的添加

            option1.setYaxis(yaxis);//Y轴添加
            option1.setHengZhou(hengZhou);//X轴添加
            option1.setSeries(series1);//数据和图像样式的添加

            option2.setYaxis(yaxis);//Y轴添加
            option2.setHengZhou(hengZhou);//X轴添加
            option2.setSeries(series2);//数据和图像样式的添加

            list.add(option);
            list.add(option1);
            list.add(option2);
        }
        return list;
    }

    @Override
    public int insert(IndustryStructure industryStructure) {
        return industryStructureMapper.insert(industryStructure);
    }

    @Override
    public List<IndustryStructure> select() {
        return industryStructureMapper.select();
    }
}
