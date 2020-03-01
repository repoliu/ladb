package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.influ.Autovalue;
import com.dky.entity.influ.LoadRainInfo;
import com.dky.mapper.influ.LoadRainInfoMapper;
import com.dky.service.influencingFactorAnalysis.AutovalueService;
import com.dky.service.influencingFactorAnalysis.LoadRainInfoService;
import com.dky.service.influencingFactorAnalysis.QstationService;
import com.dky.thread.influ.ThreadRain;
import com.dky.util.UtilType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("loadRainInfoService")
public class LoadRainInfoServiceImpl implements LoadRainInfoService {
    @Autowired
    private LoadRainInfoMapper loadRainInfoMapper;
    @Autowired
    private QstationService qstationService;

    @Autowired
    private AutovalueService autovalueService;


    @Override
    public List<LoadRainInfo> select(LoadRainInfo loadRainInfo) {
        return loadRainInfoMapper.select(loadRainInfo);
    }


    @Override
    public List<LoadRainInfo> selectCopy(String areaname, String rainLeve,String stringDate) {
        return loadRainInfoMapper.selectCopy(areaname,rainLeve,stringDate);
    }
    @Override
    public void delete(LoadRainInfo loadRainInfo) {
       loadRainInfoMapper.deletes(loadRainInfo.getStringDate(),loadRainInfo.getAreaname(),loadRainInfo.getModel());
    }

    @Override
    public void insert(List<LoadRainInfo> list) {
        loadRainInfoMapper.insert(list);
    }

    @Override
    public List<LoadRainInfo> selectDayNumber(LoadRainInfo loadRainInfo) {
        return loadRainInfoMapper.selectDayNumber(loadRainInfo);
    }

    @Override
    public void selectSL(String date, String areaname) {
                            //时间          //地区           降雨量大小
        //这个是查出来的地区id
        String stcd = qstationService.findStcdByAreaname(areaname);
        //所选的地区在数据库是否存在
        List<Autovalue> list = new LinkedList<>();
        if (stcd != null) {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy");
            Autovalue autovalue = new Autovalue();
            LoadRainInfo loadRainInfo = new LoadRainInfo();
            loadRainInfo.setStringDate(date);
            autovalue.setStringDate(date);
            try {
                autovalue.setTime(format.parse(date));
                loadRainInfo.setTime(format.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            autovalue.setStcd(stcd);

            List<LoadRainInfo> loadRainInfoList = new LinkedList<>();
            loadRainInfo.setAreaname(areaname);
            loadRainInfoList = select(loadRainInfo);

            list = autovalueService.selectRain(autovalue);

            if (list != null) {
                //判断LoadRainInfo和Autovalue表的数据是否一样多，一样多在LoadRainInfo表查，否则在Autovalue表查
                if (list.size() == loadRainInfoList.size()) {
                } else {
                    //把现在这个条件查询的数据在LoadRainInfo表中删掉
                    loadRainInfo.setModel(UtilType.MODEL);
                    delete(loadRainInfo);
                    //这个是代表了小雨，中雨，大雨等等等的天数
                    Map<String, Object> mapListDay = new HashMap<>();
                    ThreadRain threadRain1 = new ThreadRain(autovalueService, autovalue, mapListDay, 1);
                    Thread thread1 = new Thread(threadRain1);
                    thread1.start();

                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (list != null && list.size() > 0) {
                        insertLoadRainInfo(list, areaname);//
                    }
                }
            }
        }
    }


    public void insertLoadRainInfo(List<Autovalue> list, String areaname) {
        List<LoadRainInfo> loadRainInfos = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            Autovalue autovalue = list.get(i);
            LoadRainInfo loadRainInfo = new LoadRainInfo();

            loadRainInfo.setTime(autovalue.getTime());

            loadRainInfo.setTemperature(autovalue.getTemperature());
            loadRainInfo.setAreaname(areaname);
            loadRainInfo.setRainLeve(autovalue.getStcd());//降雨级别


            loadRainInfo.setRain(autovalue.getRain());


            loadRainInfo.setRainDay(autovalue.getWd());
            loadRainInfo.setWp(autovalue.getWp());

            loadRainInfos.add(loadRainInfo);
        }
        insert(loadRainInfos);
    }

}
