package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.PerGdp;
import com.dky.entity.vo.*;
import com.dky.mapper.PerGdpMapper;
import com.dky.service.influencingFactorAnalysis.PerGdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("perGdpService")
public class PerGdpServiceImpl implements PerGdpService {

    @Autowired
    private PerGdpMapper perGdpMapper;


    public List<ChartsOption> selectQuery(String area, String date, String nianXian) {
        PerGdp perGdp = new PerGdp();
        List<PerGdp> perGdpList = new LinkedList<PerGdp>();

        ChartsOption option = new ChartsOption();
        List<ChartsOption> list = new LinkedList<ChartsOption>();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            perGdp.setAreaName(area);
            perGdp.setmDate(dateTime[1]);
            perGdp.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1])-Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            perGdp.setAreaName(area);
            perGdp.setmDate(date);
            perGdp.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1) + "");
        }

        perGdpList = perGdpMapper.selectQuery(perGdp);

        if (perGdpList.size()>0) {
            String[] hengZhou = new String[perGdpList.size()];//X轴的集合
            String[] shuZhou = new String[perGdpList.size()];//Y轴值的集合

            for (int i = 0; i < perGdpList.size(); i++) {
                hengZhou[i] = perGdpList.get(i).getmDate();
                shuZhou[i] = perGdpList.get(i).getPerGdp().toString();
            }

            Series series = new Series();//这个是数据
            series.setName("人均GDP");
            series.setData(shuZhou);
            series.setType("bar");

            Yaxis yaxis = new Yaxis();//Y轴
            yaxis.setType("value");
            yaxis.setName("万元/人");

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
    public int insert(PerGdp perGdp) {
        return perGdpMapper.insert(perGdp);
    }
}
