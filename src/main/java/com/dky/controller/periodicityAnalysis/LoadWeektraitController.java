package com.dky.controller.periodicityAnalysis;

/*
 * @author liuhaijian
 * @PackageName: com.dky.controller
 * @Description: 周数据分析Controller类
 * @date Create in 2017/10/18 16:32
* */

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.dky.entity.*;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.DaytraitResult;
import com.dky.entity.vo.Series;
import com.dky.service.common.DbareaService;
import com.dky.service.periodicityAnalysis.DaytraitService;
import com.dky.service.periodicityAnalysis.LoadHisdataService;

import com.dky.util.*;

import com.dky.util.ArrayOperation;

import com.dky.util.TimePoint;
import com.dky.util.WeekInterval;


import com.dky.util.ObjToArraysUtils;

import com.dky.util.daoOperate.DeleteFunction;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;




@Controller
@RequestMapping("periodicity/week")
public class LoadWeektraitController {

    @Autowired
    private LoadHisdataService loadHisdataService;
    @Autowired
    private DaytraitService daytraitService;
    @Autowired
    private DbareaService dbareaService;
    Logger logger = Logger.getLogger(LoadWeektraitController.class.getName());

    private String[] workLegend; //用于记录典型工作日图例，为日指标提供查询条件
    private String[] restLegend;  //用于记录典型休息日图例，为日指标提供查询条件
    private String[] daytype1Array={};
    private String[] daytype0Array={};
    /*TODO 关于从曲线图中取指标的问题，因为曲线图中不记录存在的是哪一天数据，
        所以此处处理方案为只拿一条曲线的数据进行处理，
        因为循环的原因，所拿到的是集合中的最后一条曲线
    */
    private WeekInterval weekInterval = new WeekInterval();
    private List<LoadWeektrait> weektraits = new LinkedList<LoadWeektrait>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load(Model model, @Param("id") Long id, @Param("point") Integer point, @Param("area") Integer area, @Param("mdate") String mdate) {
        String week[][] = new String[5][2];
        String weekJson = "";
        DaytraitResult daytraitResult = new DaytraitResult();
        LoadDaytraitKey loadDaytraitKey = new LoadDaytraitKey();
        if (area == null){
            area = Integer.parseInt(ReadProperties.readProperties("periodicityArea"));
        }
        try {
            model.addAttribute("area", area);
           String areaname=dbareaService.findAreanameByArea(area);
            model.addAttribute("areaname",areaname);
           loadDaytraitKey.setArea(area);
            daytraitResult = daytraitService.findExtremaMdateByKey(loadDaytraitKey);
            if (daytraitResult != null) {
                model.addAttribute("weekMaxDate", sdf2.format(sdf.parse(daytraitResult.getMaxMdate())));
                model.addAttribute("weekMinDate", sdf2.format(sdf.parse(daytraitResult.getMinMdate())));
                model.addAttribute("daytraitResult", daytraitResult);
                weekInterval(model, daytraitResult.getMaxMdate());
            } else {
                model.addAttribute("weekMaxDate", null);
                model.addAttribute("weekMinDate", null);
                model.addAttribute("daytraitResult", null);
                weekInterval(model, sdf.format(new Date()));
            }
        } catch (ParseException e) {
            logger.error("查询周load出错：" + e.getMessage());
            e.printStackTrace();
        }
        return "periodicityAnalysis/weektrait";
    }

    @RequestMapping(value = "/weekInterval")
    @ResponseBody
    public String[][] weekInterval(Model model, @Param("mdate") String mdate) {
        String week[][] = new String[5][2];
        String weekJson = "";
        try {
            if (mdate != null && !"".equals(mdate)) {
                try {
                    week = weekInterval.getFiveWeekInterval(sdf.parse(mdate));
                    weekJson = JSON.toJSONString(week);
                    model.addAttribute("weekJson", weekJson);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.error("查询周weekInterval出错：" + e.getMessage());
            e.printStackTrace();
        }
        return week;
    }

    //查询周页面周进度条数据
    @RequestMapping(value = "/selectWeektraitCtl")
    @ResponseBody
    public List<LoadWeektrait> selectWeektraitCtl(Model model, @Param("date") String date, @Param("area") Integer area) {
        String[] mdate = new String[2];
        List<LoadWeektrait> weektraitList = new LinkedList<LoadWeektrait>();

        if (date != null && !"".equals(date)) {
            //按钮的逻辑
            if(date.split("-").length==2) {
                mdate = date.split("-");
            }else{
                try {
                    //时间选择框的逻辑
                    mdate[0] = weekInterval.getFiveWeekInterval(sdf2.parse(date))[2][0];
                    mdate[1] = weekInterval.getFiveWeekInterval(sdf2.parse(date))[2][1];
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            LoadDaytraitKey loadDaytraitkey = new LoadDaytraitKey();
            loadDaytraitkey.setArea(area);
            String dayMaxDate = daytraitService.getDayMaxDate(loadDaytraitkey);
            if (StringUtils.isEmpty(date) && dayMaxDate != null) {
                mdate[0] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][0];
                mdate[1] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][1];
            }
            loadDaytraitkey.setMadeOme(mdate[0]);
            loadDaytraitkey.setMadeTwo(mdate[1]);
            daytraitService.selectForWeekIndex(loadDaytraitkey);
            LoadWeektrait loadWeektrait = new LoadWeektrait();
            daytraitService.selectForWeekIndex(loadDaytraitkey);
            loadWeektrait = daytraitService.selectForWeekIndex(loadDaytraitkey);
            //loadWeektrait.setStartDay(mdate[0]);
            weektraitList.add(loadWeektrait);
            //weektraits.addAll(weektraitList);
            //model.addAttribute("weektraits",weektraits);
        } catch (Exception e) {
            logger.error("查询周selectWeektraitCtl出错：" + e.getMessage());
            e.printStackTrace();
        }
        return weektraitList;
    }

    //查询96点、288点曲线图数据
    @RequestMapping(value = "/commonGraph")
    @ResponseBody
    public List<ChartsOption> CommonGraph(@Param("date") String date, @Param("area") int area, @Param("point") int point, @Param("daytype") Integer daytype) {
        String[] mdate = new String[2];
        List<ChartsOption> chartsOptionList = new LinkedList<ChartsOption>();
        try {
            if (date != null && !"".equals(date)) {
                //按钮的逻辑
                if(date.split("-").length==2) {
                    mdate = date.split("-");
                }else{
                    try {
                        //时间选择框的逻辑
                        mdate[0] = weekInterval.getFiveWeekInterval(sdf2.parse(date))[2][0];
                        mdate[1] = weekInterval.getFiveWeekInterval(sdf2.parse(date))[2][1];
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (point == 96) {
                if (date == null || "".equals(date)) {
                    LoadWeektypeload96Key weektypeload96Key = new LoadWeektypeload96Key();
                    weektypeload96Key.setArea(area);
                    weektypeload96Key.setDaytype(daytype);
                    LoadDaytraitKey loadDaytraitkey = new LoadDaytraitKey();
                    loadDaytraitkey.setArea(area);
                    String dayMaxDate = daytraitService.getDayMaxDate(loadDaytraitkey);
                    if (!StringUtils.isEmpty(dayMaxDate)) {
                        mdate[0] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][0];
                        mdate[1] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][1];
                    }
                }
                if (mdate.length > 0 && mdate != null) {
                    if (selectWeek96Ctl(mdate, area, daytype).size() > 0){
                        for (LoadWeektypeload96 week96 : selectWeek96Ctl(mdate, area, daytype)) {
                            ChartsOption chartsOption = new ChartsOption();
                            chartsOption.setHengZhou(TimePoint.timeCreate(point));
                            Series series = new Series();
                            series.setSmooth(true);
                            series.setType("line");
                            String temp[] = ObjToArraysUtils.getFieldValues(week96);
                            String array[] = new String[97];
                            for (int i = 1; i <= 97; i++) {
                                array[i - 1] = temp[i];
                            }
                            if (daytype == 1) {
                                chartsOption.setLegend(week96.getStartDay());
                                series.setName(week96.getStartDay());
                                daytype1Array = array;
                            } else if (daytype == 0) {
                                chartsOption.setLegend(week96.getEndDay());
                                series.setName(week96.getEndDay());
                                daytype0Array = array;
                            }
                            series.setData(array);
                            chartsOption.setSeries(series);
                            chartsOptionList.add(chartsOption);
                        }
                }else {
                        if (daytype == 1) {
                            daytype1Array = new String[0];
                        } else if (daytype == 0) {
                            daytype0Array = new String[0];
                        }
                    }
                }
            } else if (point == 288) {
                if (date == null || "".equals(date)) {
                    LoadWeektypeload288Key weektypeload288Key = new LoadWeektypeload288Key();
                    weektypeload288Key.setArea(area);
                    weektypeload288Key.setDaytype(daytype);
                    LoadDaytraitKey loadDaytraitkey = new LoadDaytraitKey();
                    loadDaytraitkey.setArea(area);
                    String dayMaxDate = daytraitService.getDayMaxDate(loadDaytraitkey);
                    if (!StringUtils.isEmpty(dayMaxDate)) {
                        mdate[0] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][0];
                        mdate[1] = weekInterval.getFiveWeekInterval(sdf.parse(dayMaxDate))[2][1];
                    }
                }
                if (mdate.length > 0 && mdate != null) {
                    if (selectWeek228Ctl(mdate, area, daytype).size() > 0){
                        for (LoadWeektypeload288 week288 : selectWeek228Ctl(mdate, area, daytype)) {
                            ChartsOption chartsOption = new ChartsOption();
                            chartsOption.setHengZhou(TimePoint.timeCreate(point));
                            Series series = new Series();

                            series.setSmooth(true);
                            series.setType("line");
                            String temp[] = ObjToArraysUtils.getFieldValues(week288);
                            String array[] = new String[289];
                            for (int i = 1; i <= 289; i++) {
                                array[i - 1] = temp[i];
                            }
                            if (daytype == 1) {
                                chartsOption.setLegend(week288.getStartDay());
                                series.setName(week288.getStartDay());
                                daytype1Array = array;
                            } else if (daytype == 0) {
                                chartsOption.setLegend(week288.getEndDay());
                                series.setName(week288.getEndDay());
                                daytype0Array = array;
                            }
                            series.setData(array);
                            chartsOption.setSeries(series);
                            chartsOptionList.add(chartsOption);
                        }
                }else{
                        if (daytype == 1) {
                            daytype1Array = new String[0];
                        } else if (daytype == 0) {
                            daytype0Array = new String[0];
                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.error("查询周commonGraph出错：" + e.getMessage());
            e.printStackTrace();
        }

        return chartsOptionList;
    }

    //查询周页面日数据
    @RequestMapping(value = "/selectDaytraitCtl")
    @ResponseBody
    public Map<String, List<LoadDaytrait>> selectDaytraitCtl(@Param("point")int point, @Param("daytype") int daytype, @Param("area") int area) {

        Map<String, List<LoadDaytrait>> map = new HashMap<>();

        try {

            List<LoadDaytrait> loadDaytraitList = new LinkedList<LoadDaytrait>();
            if (daytype == 1&&daytype1Array.length>0) {
                LoadDaytrait loadDaytrait=new LoadDaytrait();
                int length=daytype1Array.length;
                loadDaytrait.setMaxload(ArrayOperation.getMaxValue(daytype1Array,0,daytype1Array.length).get("value"));
                loadDaytrait.setMaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype1Array,0,length).get("index"),point));

                loadDaytrait.setMinload(ArrayOperation.getMinValue(daytype1Array,0,length).get("value"));
                loadDaytrait.setMintime(TimePoint.createTimeByPoint(ArrayOperation.getMinValIndex(daytype1Array,0,length).get("index"),point));

                loadDaytrait.setFmmaxload(ArrayOperation.getMaxValue(daytype1Array,0,length/2).get("value"));
                loadDaytrait.setFmmaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype1Array,0,length/2).get("index"),point));

                loadDaytrait.setPmmaxload(ArrayOperation.getMaxValue(daytype1Array,length/2,length).get("value"));
                loadDaytrait.setPmmaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype1Array,length/2,length).get("index"),point));

                loadDaytraitList.add(loadDaytrait);
                /*loadDaytrait.setArea(area);
                String temp = ArrayOperation.arrayToField(workLegend);
                loadDaytrait.setLegend(temp);
                loadDaytraitList = daytraitService.selectQuery2(loadDaytrait);*/
                map.put("1", loadDaytraitList);
            }
            if (daytype == 0&&daytype0Array.length>0) {
                int length=daytype0Array.length;
                /*LoadDaytraitKey loadDaytrait = new LoadDaytraitKey();
                loadDaytrait.setArea(area);
                loadDaytrait.setLegend(ArrayOperation.arrayToField(restLegend));
                loadDaytraitList = daytraitService.selectQuery2(loadDaytrait);*/
                LoadDaytrait loadDaytrait=new LoadDaytrait();
                loadDaytrait.setMaxload(ArrayOperation.getMaxValue(daytype0Array,0,daytype0Array.length).get("value"));
                loadDaytrait.setMaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype0Array,0,length).get("index"),point));

                loadDaytrait.setMinload(ArrayOperation.getMinValue(daytype0Array,0,length).get("value"));
                loadDaytrait.setMintime(TimePoint.createTimeByPoint(ArrayOperation.getMinValIndex(daytype0Array,0,length).get("index"),point));

                loadDaytrait.setFmmaxload(ArrayOperation.getMaxValue(daytype0Array,0,length/2).get("value"));
                loadDaytrait.setFmmaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype0Array,0,length/2).get("index"),point));

                loadDaytrait.setPmmaxload(ArrayOperation.getMaxValue(daytype0Array,length/2,length).get("value"));
                loadDaytrait.setPmmaxtime(TimePoint.createTimeByPoint(ArrayOperation.getMaxValIndex(daytype0Array,length/2,length).get("index"),point));
                loadDaytraitList.add(loadDaytrait);
                map.put("0", loadDaytraitList);
            }

        } catch (Exception e) {
            logger.error("查询周selectDaytraitCtl出错：" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return map;
    }

    //查询周页面96点曲线数据
    @RequestMapping(value = "/selectAnalysis96Ctl")
    @ResponseBody
    public List<LoadWeektypeload96> selectWeek96Ctl(@Param("date") String[] date, @Param("area") int area, Integer daytype) {
        List<LoadWeektypeload96> loadWeektypeload96 = new LinkedList<LoadWeektypeload96>();
        LoadWeektypeload96 week96 = new LoadWeektypeload96();
        try {
            week96.setArea(area);
            week96.setStartDate(date[0]);
            week96.setEndDate(date[1]);
            week96.setDaytype(daytype);
            loadWeektypeload96 = loadHisdataService.find96WeekListByKey(week96);
            if (daytype == 1) {
                workLegend = new String[loadWeektypeload96.size()];
                for (int i = 0; i < loadWeektypeload96.size(); i++) {
                    workLegend[i] = loadWeektypeload96.get(i).getStartDay();
                }
            } else if (daytype == 0) {
                restLegend = new String[loadWeektypeload96.size()];
                for (int i = 0; i < loadWeektypeload96.size(); i++) {
                    restLegend[i] = loadWeektypeload96.get(i).getEndDay();
                }
            }
        } catch (Exception e) {
            logger.error("查询周selectAnalysis96Ctl出错：" + e.getMessage());
            e.printStackTrace();
        }
        return loadWeektypeload96;
    }

    //查询周页面288点曲线数据
    @RequestMapping(value = "/selectAnalysis228Ctl")
    @ResponseBody
    public List<LoadWeektypeload288> selectWeek228Ctl(@Param("date") String[] date, @Param("area") int area, Integer daytype) {
        List<LoadWeektypeload288> loadWeektypeload288 = new LinkedList<LoadWeektypeload288>();
        LoadWeektypeload288 week288 = new LoadWeektypeload288();
        try {
            week288.setArea(area);
            week288.setStartDate(date[0]);
            week288.setEndDate(date[1]);
            week288.setDaytype(daytype);
            loadWeektypeload288 = loadHisdataService.find288WeekListByKey(week288);
            if (daytype == 1) {
                workLegend = new String[loadWeektypeload288.size()];
                for (int i = 0; i < loadWeektypeload288.size(); i++) {
                    workLegend[i] = loadWeektypeload288.get(i).getStartDay();
                }
            } else if (daytype == 0) {
                restLegend = new String[loadWeektypeload288.size()];
                for (int i = 0; i < loadWeektypeload288.size(); i++) {
                    restLegend[i] = loadWeektypeload288.get(i).getEndDay();
                }
            }
        } catch (Exception e) {
            logger.error("查询周selectAnalysis228Ctl出错：" + e.getMessage());
            e.printStackTrace();
        }
        return loadWeektypeload288;
    }

    public String[] getWorkLegend() {
        return workLegend;
    }

    public void setWorkLegend(String[] workLegend) {
        this.workLegend = workLegend;
    }

    public String[] getRestLegend() {
        return restLegend;
    }

    public void setRestLegend(String[] restLegend) {
        this.restLegend = restLegend;
    }

    public List<LoadWeektrait> getWeektraits() {
        return weektraits;
    }

    public void setWeektraits(List<LoadWeektrait> weektraits) {
        this.weektraits = weektraits;
    }

}
