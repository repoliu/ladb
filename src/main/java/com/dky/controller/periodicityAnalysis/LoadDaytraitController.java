package com.dky.controller.periodicityAnalysis;

import com.dky.entity.*;
import com.dky.entity.vo.DaytraitResult;
import com.dky.entity.vo.TimeSelectButtonOfDayLoad;
import com.dky.service.common.DbareaService;
import com.dky.service.periodicityAnalysis.DaytraitService;
import com.dky.service.periodicityAnalysis.LoadHisdataService;
import com.dky.util.ReadProperties;
import com.dky.util.DateUtils;
import com.dky.util.TimePoint;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * @author YangSL
 * @PackageName: com.dky.controller.periodicityAnalysis
 * @Description: 日数据分析Controller类
 * @Date Create in 2017/10/14 20:59
 */
@Controller
@RequestMapping("periodicity/day")
public class LoadDaytraitController {
    private final DaytraitService daytraitService;
    private final LoadHisdataService loadHisdataService;
    private final DbareaService dbareaService;

    /**
     * 使用构造方法注入service，可以明确成员变量的加载顺序；
     * 加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变；
     */
    @Autowired
    public LoadDaytraitController(DaytraitService daytraitService,
                                  LoadHisdataService loadHisdataService,
                                  DbareaService dbareaService) {
        this.daytraitService = daytraitService;
        this.loadHisdataService = loadHisdataService;
        this.dbareaService = dbareaService;
    }
    @ResponseBody
    @RequestMapping(value = "/selectLoadHisdataToLoadDaytrait")
    public Map selectLoadHisdataToLoadDaytrait(@Param("area")String area,@Param("mdate")String mdate){
        Map<String ,String > map=new HashMap<>();
        map.put("bumber",daytraitService.selectLoadHisdataToLoadDaytrait(area,mdate)+"");
        return map;

    }
    /**
     * load方法，接受前台请求，将气象因素所需属性返回jsp;
     *
     * @param point 曲线图的横轴点数
     * @param area  地区唯一标识
     * @param mdate 时间标识
     * @return 返回影响因素分析的日分析页
     */
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load(int point, Integer area, String mdate, Model model) throws ParseException {
        DaytraitResult daytraitResult = new DaytraitResult();

        //设置地区信息
        if (area == null){
            area = Integer.parseInt(ReadProperties.readProperties("periodicityArea"));
        }
        String areaname = dbareaService.findAreanameByArea(area);
        daytraitResult.setAreaname(areaname);

        //设置参数
        Map<String,Object> params = new HashMap<>();
        params.put("area",area);
        params.put("mdate",mdate);

        //查询日负荷特性
        LoadDaytrait loadDaytrait = daytraitService.findDayTraitByParams(params);
        Date dateMdate = DateUtils.parse((String)params.get("mdate"),DateUtils.YMDN_P);
        String stringMdate = DateUtils.format(dateMdate,DateUtils.YMDH_P);
        daytraitResult.setPointResponse(point);
        daytraitResult.setMdate(stringMdate);

        //时间按钮
        List<TimeSelectButtonOfDayLoad> timeSelectButton;
        timeSelectButton = TimePoint.createTimeButtonValue((String)params.get("mdate"));
        daytraitResult.setTimeSelectButton(timeSelectButton);

        //日负荷特性为空时，返回
        if (loadDaytrait == null) {
            model.addAttribute("isEmpty", "true");
            model.addAttribute("errorMsg", "该地区/时间没有数据！！！");
            model.addAttribute("dayResult", daytraitResult);
            return "periodicityAnalysis/daytrait";
        }
        daytraitResult.setLoadDay(loadDaytrait);
        List<String> chartTime = new ArrayList<>();
        chartTime.add(loadDaytrait.getMdate());
        params.put("chartTimes",chartTime);
        params.put("point",point);

        //查询曲线数据
        String charts = daytraitService.createChartsByParams(params);
        LoadHisdataKey loadHisdataKey = new LoadHisdataKey();
        loadHisdataKey.setArea(area);
        loadHisdataKey.setMdateList(chartTime);
        model.addAttribute("chartsList", charts);
        model.addAttribute("dayResult", daytraitResult);
        return "periodicityAnalysis/daytrait";
    }

    /**
     * 用于日分析页添加对比日期功能
     *
     * @param point     横轴显示的点数
     * @param area      地区的唯一标识
     * @param dataMdate 日期集合
     * @return 异步返回曲线图的参数
     */
    @RequestMapping(value = "/chartsUpdate", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String chartsUpdate(int point, Integer area, @Param("dataMdate") String[] dataMdate,String mdate) throws ParseException {
        List<String> paramTime = new ArrayList<>();
        for (int i = 0; i < dataMdate.length; i++) {
            String temp;
            if (i == 0) {
                temp = dataMdate[i].substring(2, dataMdate[i].length() - 1).replace("-", "");
            } else if (i == dataMdate.length - 1) {
                temp = dataMdate[i].substring(1, dataMdate[i].length() - 2).replace("-", "");
            } else {
                temp = dataMdate[i].substring(1, dataMdate[i].length() - 1).replace("-", "");
            }
            if (!paramTime.contains(temp)) {
                paramTime.add(temp);
            }
        }
        Map<String,Object> params = new HashMap<>();
        params.put("chartTimes",paramTime);
        params.put("point",point);
        params.put("area",area);
        params.put("mdate",mdate.replace("-",""));
        return daytraitService.createChartsByParams(params);
    }


    @ResponseBody
    @RequestMapping(value = "/selectMaxDateString")
    public String selectMaxDateString(@Param("area")String area,@Param("mdate")String mdate)   {
        String str=daytraitService.selectMaxDateString(Integer.parseInt(area),mdate);

        return str;
    }
}
