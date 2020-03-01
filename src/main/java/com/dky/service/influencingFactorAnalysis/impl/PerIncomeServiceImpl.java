package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.PerIncome;
import com.dky.entity.vo.AxisLabel;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.entity.vo.Yaxis;
import com.dky.mapper.PerIncomeMapper;
import com.dky.service.influencingFactorAnalysis.PerIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("perIncomeService")
public class PerIncomeServiceImpl implements PerIncomeService {

    @Autowired
    private PerIncomeMapper perIncomeMapper;


    public List<ChartsOption> selectQuery(String area, String date, String nianXian) {
        ChartsOption option = new ChartsOption();
        List<ChartsOption> list = new LinkedList<ChartsOption>();

        PerIncome perIncome = new PerIncome();
        List<PerIncome> perIncomeList = new LinkedList<PerIncome>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            perIncome.setAreaName(area);
            perIncome.setmDate(dateTime[1]);
            perIncome.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1]) - Integer.parseInt(dateTime[0])))+"");
        } else {
            date = date.substring(0, 4);
            perIncome.setAreaName(area);
            perIncome.setmDate(date);
            perIncome.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1)+"");
        }
        perIncomeList = perIncomeMapper.selectQuery(perIncome);
        if (perIncomeList.size() > 0) {
            String[] hengZhou = new String[perIncomeList.size()];//X轴的集合
            String[] shuZhou = new String[perIncomeList.size()];//Y轴值的集合

            for (int i = 0; i < perIncomeList.size(); i++) {
                hengZhou[i] = perIncomeList.get(i).getmDate();
                shuZhou[i] = perIncomeList.get(i).getPerIncome().toString();
            }
            Series series = new Series();//这个是数据
            series.setName("城镇居民人均收入");
            series.setData(shuZhou);
            series.setSmooth(true);
            series.setType("line");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("元/人");

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
    public int insert(PerIncome perIncome) {
        return perIncomeMapper.insert(perIncome);
    }
}
