package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.OutputValue;
import com.dky.entity.vo.AxisLabel;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.entity.vo.Yaxis;
import com.dky.mapper.OutputValueMapper;
import com.dky.service.influencingFactorAnalysis.OutPutValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("outPutValueService")
public class OutPutValueServiceImpl implements OutPutValueService {

    @Autowired
    private OutputValueMapper outputValueMapper;

    public List<ChartsOption> selectQuery(String area, String date, String nianXian) {
        OutputValue outputValue = new OutputValue();
        List<OutputValue> outputValueList = new LinkedList<OutputValue>();

        ChartsOption option = new ChartsOption();
        List<ChartsOption> list = new LinkedList<ChartsOption>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            outputValue.setAreaName(area);
            outputValue.setmDate(dateTime[1]);
            outputValue.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1]) - Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            outputValue.setAreaName(area);
            outputValue.setmDate(date);
            outputValue.setMinDate(((Integer.parseInt(date) - Integer.parseInt(nianXian))+1) + "");
        }
        outputValueList = outputValueMapper.selectQuery(outputValue);
        if (outputValueList.size() > 0) {
            String[] hengZhou = new String[outputValueList.size()];//X轴的集合
            String[] shuZhou = new String[outputValueList.size()];//Y轴值的集合
            for (int i = 0; i < outputValueList.size(); i++) {
                hengZhou[i] = outputValueList.get(i).getmDate();
                shuZhou[i] = outputValueList.get(i).getOutPutValue().toString();
            }
            Series series = new Series();//这个是数据
            series.setName("生产总值");
            series.setData(shuZhou);
            series.setSmooth(true);
            series.setType("line");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("亿元");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            yaxis.setAxisLabel(axisLabel);//Y轴数据加的返回数据中
            option.setYaxis(yaxis);//Y轴添加
            option.setHengZhou(hengZhou);//X轴添加
            option.setSeries(series);//数据和图像样式的添加
            list.add(option);
        }
        return list;
    }

    public List<ChartsOption> selectQueryIndex(String area, String date, String nianXian) {

        OutputValue outputValue = new OutputValue();
        List<OutputValue> outputValueList = new LinkedList<OutputValue>();

        ChartsOption option = new ChartsOption();
        List<ChartsOption> list = new LinkedList<ChartsOption>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            outputValue.setAreaName(area);
            outputValue.setmDate(dateTime[1]);
            outputValue.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1]) - Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            outputValue.setAreaName(area);
            outputValue.setmDate(date);
            outputValue.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1) + "");
        }
        outputValueList = outputValueMapper.selectQuery(outputValue);
        if (outputValueList.size() > 0) {
            String[] hengZhou = new String[outputValueList.size()];//X轴的集合
            String[] shuZhou = new String[outputValueList.size()];//Y轴值的集合

            for (int i = 0; i < outputValueList.size(); i++) {
                hengZhou[i] = outputValueList.get(i).getmDate();
                shuZhou[i] = outputValueList.get(i).getOutPutIndex().toString();
            }
            Series series = new Series();//这个是数据
            series.setName("生产指数");
            series.setData(shuZhou);
            series.setSmooth(true);
            series.setType("bar");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("亿元");

            AxisLabel axisLabel = new AxisLabel();//Y轴的输出格式
            axisLabel.setFormatter("{value}");

            yaxis.setAxisLabel(axisLabel);//Y轴数据加的返回数据中
            option.setYaxis(yaxis);//Y轴添加
            option.setHengZhou(hengZhou);//X轴添加
            option.setSeries(series);//数据和图像样式的添加
            list.add(option);
        }
        return list;
    }

    @Override
    public Map selectMaxDate(String areaName) {
        OutputValue outputValue=new OutputValue();
        outputValue.setAreaName(areaName);
        return outputValueMapper.selectMaxDate(outputValue);
    }


    @Override
    public int insert(OutputValue outputValue) {
        return outputValueMapper.insert(outputValue);
    }
}
