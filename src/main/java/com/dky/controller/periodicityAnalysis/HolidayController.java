package com.dky.controller.periodicityAnalysis;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.period.Holiday;
import com.dky.service.common.DbareaService;
import com.dky.service.periodicityAnalysis.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description: 节假日
 * @Date: Create in 15:29 2018/2/26
 */
@Controller
@RequestMapping("periodicity/holiday")
public class HolidayController {
    private final HolidayService holidayService;
    private final DbareaService dbareaService;
    @Autowired
    public HolidayController(DbareaService dbareaService,
                             HolidayService holidayService){
        this.dbareaService = dbareaService;
        this.holidayService = holidayService;
    }

    /**
     * 跳转到节假日页面，并传回通用值
     * @param area 地区id
     * @param model 模型
     * @return 页面地址
     */
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load(Integer area, Model model){
        Map<String,String> years = holidayService.findStartAndEndYear();
        String startYear = years.get("startyear");
        String endYear = years.get("endyear");
        String areaname = dbareaService.findAreanameByArea(area);
        List<String> holiday = holidayService.findAllTypeOfHoliday();
        Map<String,Object> result = new HashMap<>();
        result.put("area",area);
        result.put("areaname",areaname);
        result.put("startYear",startYear);
        result.put("endYear",endYear);
        result.put("holiday",holiday);
        model.addAttribute("result",result);
        return "periodicityAnalysis/holidaytrait";
    }

    /**
     * 查询相应年份节假日时负荷曲线数据
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/allLoad")
    public Map<String , Object> allLoad(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findAllLoadOptionsByAreaAndHolidays(area,holidays);
    }

    /**
     * 查询相关年份节假日每日最大负荷
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/maxLoad")
    public Map<String , Object> maxLoad(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadOptionsByAreaAndHolidays(area,holidays,"maxload");
    }

    /**
     * 查询相关年份节假日每日最大负荷增长率
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/maxLoadup")
    public Map<String , Object> maxLoadup(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadUpOptionsByAreaAndHolidays(area,holidays,"maxload");
    }

    /**
     * 查询相关年份节假日每日最小负荷
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/minLoad")
    public Map<String , Object> minLoad(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadOptionsByAreaAndHolidays(area,holidays,"minload");
    }

    /**
     * 查询相关年份节假日每日最小负荷增长率
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/minLoadup")
    public Map<String , Object> minLoadup(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadUpOptionsByAreaAndHolidays(area,holidays,"minload");
    }

    /**
     * 查询相关年份节假日每日平均负荷
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/aveLoad")
    public Map<String , Object> aveLoad(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadOptionsByAreaAndHolidays(area,holidays,"aveload");
    }

    /**
     * 查询相关年份节假日每日平均负荷增长率
     * @param area 地区id
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param holiday 节假日类型
     * @return 曲线数据
     */
    @ResponseBody
    @RequestMapping("/aveLoadup")
    public Map<String , Object> aveLoadup(Integer area,String startYear,String endYear,String holiday){
        List<Holiday> holidays = holidayService.findHolidayByParams(startYear,endYear,holiday);
        return holidayService.findLoadUpOptionsByAreaAndHolidays(area,holidays,"aveload");
    }
}
