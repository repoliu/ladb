package com.dky.thread.influ;

import com.dky.entity.influ.Autovalue;
import com.dky.service.influencingFactorAnalysis.AutovalueService;
import com.dky.service.influencingFactorAnalysis.LoadRainInfoService;

import java.util.*;

public class ThreadRain implements Runnable {

    private AutovalueService autovalueService;
    private LoadRainInfoService loadRainInfoService;

    private Autovalue autovalue;
    private List<Autovalue> list = new ArrayList<>();
    private int number;
    private String str1 = null;
    private Map<String, Object> mapList;
    private Map<String, Object> mapListDay;





    public ThreadRain(AutovalueService autovalueService, Autovalue autovalue, Map<String, Object> mapListDay, int number) {
        this.mapListDay = mapListDay;
        this.number = number;
        this.autovalue = autovalue;
        this.autovalueService = autovalueService;
    }

    public ThreadRain(AutovalueService autovalueService, Autovalue autovalue, Map<String, Object> mapList, String str1) {
        this.mapList = mapList;
        this.str1 = str1;
        this.autovalue = autovalue;
        this.autovalueService = autovalueService;
    }

    @Override
    public void run() {
        if (number == 1) {
            mapListDay.put("listDay", autovalueService.selectRainDay(autovalue));
        }
        if ("list".equals(str1)) {
            mapList.put("list", autovalueService.selectRain(autovalue));
        }
    }




}
