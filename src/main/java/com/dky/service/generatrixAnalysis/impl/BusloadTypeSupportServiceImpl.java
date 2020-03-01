package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.BusloadTypeSupport;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Label;
import com.dky.entity.vo.Normal;
import com.dky.entity.vo.Series;
import com.dky.mapper.BusloadTypeSupportMapper;
import com.dky.service.generatrixAnalysis.BusloadTypeSupportService;
import com.dky.util.daoOperate.MultipleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("busloadTypeSupportService")
public class BusloadTypeSupportServiceImpl implements BusloadTypeSupportService {
    @Autowired
    private BusloadTypeSupportMapper busloadTypeSupportMapper;

    private String datasource = "dataSource2";

    @Override
    public ChartsOption selectAreaDate(String createDate, String busloadType, String dccDescr) {
        ChartsOption chartsOption = new ChartsOption();
        List<BusloadTypeSupport> list = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        list = busloadTypeSupportMapper.selectAreaDate(createDate.substring(0,4), busloadType, dccDescr );
        MultipleDataSource.clearDBType();
        if (list.size()>0) {
        String[] strX = new String[list.size()];//代表X轴
        String[] strY = new String[list.size()];//代表Y轴
            for (int i = 0; i < list.size(); i++) {
                strX[i] = list.get(i).getCreateDate().substring(4,6);
                strY[i] = list.get(i).getRelationDegree() + "";
            }


        Normal normal=new Normal();
        Label label=new Label();
        label.setNormal(normal);

        Series series = new Series();
        series.setData(strY);
        series.setType("bar");//代表柱状图
        series.setName(createDate.substring(0,4)+"年"+busloadType);
        series.setLabel(label);//这个是让数字在柱状体里边显示





        String[] colors = {"#B15BFF", "#68CFE8", "lawngreen"};

        chartsOption.setColor(colors);
        chartsOption.setLegend(createDate.substring(0,4)+"年"+busloadType);
        /**
         * 这个地方有改动
         */
        chartsOption.setHengZhou(strX);
        chartsOption.setSeries(series);
        }
        return chartsOption;
    }

    @Override
    public String selectMaxDate(List<String> listArea) {
        return busloadTypeSupportMapper.selectMaxDate(listArea);
    }


    @Override
    public ChartsOption selectListAreaDate(String createDate, String busloadType, List<String> listArea ) {
//    List<BusloadTypeSupport>   selectListAreaDate(String  createDate, String busloadType,List<String> listArea);


        ChartsOption chartsOption = new ChartsOption();
        List<BusloadTypeSupport> list = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        list = busloadTypeSupportMapper.selectListAreaDate(createDate.substring(0,4), busloadType, listArea);
        MultipleDataSource.clearDBType();
        if (list.size()>0) {
            String[] strX = new String[list.size()];//代表X轴
            String[] strY = new String[list.size()];//代表Y轴
            for (int i = 0; i < list.size(); i++) {
                strX[i] = list.get(i).getCreateDate().substring(4,6);
                String numberStr=list.get(i).getRelationDegree() + "";
                strY[i] =  numberStr.substring(0, numberStr.indexOf(".")+2);
            }

            Normal normal=new Normal();
            Label label=new Label();
            label.setNormal(normal);

            Series series = new Series();
            series.setData(strY);
            series.setType("bar");//代表柱状图
            series.setName(createDate.substring(0,4)+"年"+busloadType);
            series.setLabel(label);//这个是让数字在柱状体里边显示


            String[] colors = {"#B15BFF", "#68CFE8", "lawngreen"};

            chartsOption.setColor(colors);
            chartsOption.setLegend(createDate.substring(0,4)+"年"+busloadType);
            /**
             * 这个地方有改动
             */
            chartsOption.setHengZhou(strX);
            chartsOption.setSeries(series);
        }

        return chartsOption;
    }
}
