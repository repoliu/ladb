package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.PerYdl;
import com.dky.entity.vo.*;
import com.dky.mapper.PerYdlMapper;
import com.dky.service.influencingFactorAnalysis.PerYdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service("perTdlService")
public class PerYdlServiceImpl implements PerYdlService {


    @Autowired
    private PerYdlMapper perYdlMapper;


    public int insert(PerYdl perYdl) {
        return perYdlMapper.insert(perYdl);
    }

    public List<ChartsOption> selectQuery(String area, String date, String nianXian) {
        List<ChartsOption> list = new LinkedList<ChartsOption>();
        ChartsOption option = new ChartsOption();
        ChartsOption option1 = new ChartsOption();
        ChartsOption option2 = new ChartsOption();
        ChartsOption option3 = new ChartsOption();
        ChartsOption option4 = new ChartsOption();
        PerYdl perYdl=new PerYdl();
        List<PerYdl> perYdlList=new LinkedList<>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            perYdl.setAreaName(area);
            perYdl.setmDate(dateTime[1]);
            perYdl.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1])-Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            perYdl.setAreaName(area);
            perYdl.setmDate(date);
            perYdl.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1) + "");
        }
        perYdlList=perYdlMapper.selectQuery(perYdl);

        if (perYdlList.size()>0) {
            String[] hengZhou = new String[perYdlList.size()];//X轴的集合
            String[] shuZhou = new String[perYdlList.size()];//Y轴值的集合
            String[] shuZhou1 = new String[perYdlList.size()];//Y轴值的集合
            String[] shuZhou2 = new String[perYdlList.size()];//Y轴值的集合
            String[] shuZhou3 = new String[perYdlList.size()];//Y轴值的集合
            String[] shuZhou4 = new String[perYdlList.size()];//Y轴值的集合


            for (int i = 0; i < perYdlList.size(); i++) {
                hengZhou[i] = perYdlList.get(i).getmDate();
                shuZhou[i] = perYdlList.get(i).getAllYdl().toString();
                shuZhou1[i] = perYdlList.get(i).getYiChanYdl().toString();
                shuZhou2[i] = perYdlList.get(i).getErChanYdl().toString();
                shuZhou3[i] = perYdlList.get(i).getSanChanYdl().toString();
                shuZhou4[i] = perYdlList.get(i).getJuMingYdl().toString();
            }

            Series series = new Series();//这个是数据
            series.setName("总用电量");
            series.setData(shuZhou);
            series.setType("bar");

            Series series1 = new Series();//这个是数据
            series1.setName("第一产业用电");
            series1.setData(shuZhou1);
            series1.setType("bar");

            Series series2 = new Series();//这个是数据
            series2.setName("第二产业用电");
            series2.setData(shuZhou2);
            series2.setType("bar");

            Series series3 = new Series();//这个是数据
            series3.setName("第三产业用电");
            series3.setData(shuZhou3);
            series3.setType("bar");

            Series series4 = new Series();//这个是数据
            series4.setName("居民用电");
            series4.setData(shuZhou4);
            series4.setType("bar");


            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("亿千瓦时");
            yaxis.setAxisLabel(axisLabel);//Y轴数据加的返回数据中

            option.setYaxis(yaxis);//Y轴添加
            option.setHengZhou(hengZhou);//X轴添加
            option.setSeries(series);//数据和图像样式的添加

            option1.setYaxis(yaxis);//Y轴添加
            option1.setHengZhou(hengZhou);//X轴添加
            option1.setSeries(series1);//数据和图像样式的添加

            option2.setYaxis(yaxis);//Y轴添加
            option2.setHengZhou(hengZhou);//X轴添加
            option2.setSeries(series2);//数据和图像样式的添加

            option3.setYaxis(yaxis);//Y轴添加
            option3.setHengZhou(hengZhou);//X轴添加
            option3.setSeries(series3);//数据和图像样式的添加

            option4.setYaxis(yaxis);//Y轴添加
            option4.setHengZhou(hengZhou);//X轴添加
            option4.setSeries(series4);//数据和图像样式的添加

            list.add(option);
            list.add(option1);
            list.add(option2);
            list.add(option3);
            list.add(option4);
        }
        return  list;
    }

    public List<ChartsOption> selectPerYdl(String area, String date, String nianXian) {
        List<ChartsOption> list = new LinkedList<ChartsOption>();
        ChartsOption option = new ChartsOption();
        PerYdl perYdl = new PerYdl();
        List<PerYdl> perYdlList = new LinkedList<>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            perYdl.setAreaName(area);
            perYdl.setmDate(dateTime[1]);
            perYdl.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1]) - Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            perYdl.setAreaName(area);
            perYdl.setmDate(date);
            perYdl.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian) + 1) + "");
        }
        perYdlList = perYdlMapper.selectQuery(perYdl);

        if (perYdlList.size()>0) {
            String[] hengZhou = new String[perYdlList.size()];//X轴的集合
            String[] shuZhou = new String[perYdlList.size()];//Y轴值的集合


            for (int i = 0; i < perYdlList.size(); i++) {
                hengZhou[i] = perYdlList.get(i).getmDate();
                shuZhou[i] = perYdlList.get(i).getAllYdl().toString();
            }

            Series series = new Series();//这个是数据
            series.setName("总用电量");
            series.setData(shuZhou);
            series.setType("line");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("亿千瓦时");
            yaxis.setAxisLabel(axisLabel);//Y轴数据加的返回数据中

            option.setYaxis(yaxis);//Y轴添加
            option.setHengZhou(hengZhou);//X轴添加
            option.setSeries(series);//数据和图像样式的添加

            list.add(option);
        }
        return  list;
    }
}
