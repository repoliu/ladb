package com.dky.controller.periodicityAnalysis;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.LoadDaytrait;
import com.dky.entity.LoadYeartrait;
import com.dky.entity.LoadYeartraitKey;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.entity.vo.TimeSelectButtonOfYearLoad;
import com.dky.entity.vo.YearTraitResult;
import com.dky.service.common.DbareaService;
import com.dky.service.periodicityAnalysis.DaytraitService;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author YangSL
 * @PackageName: com.dky.controller
 * @Description: 年数据分析Controller类
 * @date Create in 2017/10/20 15:00
 */
@Controller
@RequestMapping("periodicity/year")
public class LoadYeartraitController {

    private final DbareaService dbareaService;
    private final DaytraitService daytraitService;

    /**
     * 使用构造方法注入service，可以明确成员变量的加载顺序；
     * 加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变；
     */
    @Autowired
    public LoadYeartraitController(DaytraitService daytraitService,DbareaService dbareaService) {
        this.dbareaService = dbareaService;
        this.daytraitService = daytraitService;
    }

    /**
     * load方法，接受前台请求，将年所需属性返回jsp;
     *
     * @param area 地区唯一标识
     * @param year 时间标识
     * @return 返回影响因素分析的年分析页
     */
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load(Integer area, String year, Model model) throws ParseException {

        //设置地区信息
        if (area == null){
            area = Integer.parseInt(ReadProperties.readProperties("periodicityArea"));
        }
        String areaname = dbareaService.findAreanameByArea(area);
        YearTraitResult yearTraitResult = null;
        LoadYeartraitKey loadYeartraitKey = new LoadYeartraitKey();
        loadYeartraitKey.setArea(area);

        //设置时间参数
        if (year == null) {
            yearTraitResult = daytraitService.findExtremaYearByArea(area);
            if (yearTraitResult == null){
                year = DateUtils.format(new Date(),DateUtils.Y_P);
            } else {
                year = yearTraitResult.getMaxYear().substring(0, 4);
            }
        }
        if (yearTraitResult == null){
            yearTraitResult = new YearTraitResult();
        }
        yearTraitResult.setYear(year);
        yearTraitResult.setAreaname(areaname);
        loadYeartraitKey.setMinDate(year + "0101");
        loadYeartraitKey.setMaxDate(year + "1231");

        //时间选择按钮
        List<TimeSelectButtonOfYearLoad> timeSelectButtonOfYearLoads = TimePoint.createTimeButtonValueForYear(year);
        yearTraitResult.setTimeSelectButton(timeSelectButtonOfYearLoads);

        //查询年负荷特性，如果为空返回
        LoadYeartrait loadYeartrait = daytraitService.findYeartraitByKey(loadYeartraitKey);
        if (loadYeartrait == null) {
            model.addAttribute("isEmpty", "true");
            model.addAttribute("errorMsg", "该地区/时间没有数据！！！");
            model.addAttribute("yearResult", yearTraitResult);
            return "periodicityAnalysis/yeartrait";
        }
        loadYeartrait.setYear(year);
        yearTraitResult.setLoadYeartrait(loadYeartrait);

        //查询年曲线数据
        ChartsOption chartsOption = new ChartsOption();
        Series series = new Series();
        series.setName(year);
        series.setSmooth(true);
        series.setType("line");
        List<LoadDaytrait> listDay = daytraitService.findChartsDataByKey(loadYeartraitKey);
        String[] chartsData = new String[listDay.size()];
        for (int i = 0; i < listDay.size(); i++) {
            chartsData[i] = listDay.get(i).getAveload().intValue()+"";
        }
        series.setData(chartsData);
        chartsOption.setSeries(series);
        chartsOption.setLegend(year);
        String[] xZhou = createXZhou(listDay);
        chartsOption.setHengZhou(xZhou);
        List<ChartsOption> chartsOptionList = new ArrayList<>();
        chartsOptionList.add(chartsOption);
        String chartsList = JSONArray.toJSONString(chartsOptionList);
        model.addAttribute("chartsList", chartsList);
        model.addAttribute("yearResult", yearTraitResult);
        return "periodicityAnalysis/yeartrait";
    }

    /**
     * 用于年分析页添加对比日期功能
     *
     * @param area 地区的唯一标识
     * @param year 日期集合
     * @return 异步返回曲线图的参数
     */
    @RequestMapping(value = "/chartsUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String chartsUpdate(Integer area, @Param("year") String[] year) throws ParseException {
        List<ChartsOption> result = new ArrayList<>();

        //处理返回年列表
        String[] yearArray = createYearArray(year);
        List<List<LoadDaytrait>> chartsList = new ArrayList<>();

        //查询每年的数据
        for (String aYearArray : yearArray) {
            LoadYeartraitKey loadYeartraitKey = new LoadYeartraitKey();
            loadYeartraitKey.setArea(area);
            String maxDate = aYearArray + "1231";
            String minDate = aYearArray + "0101";
            loadYeartraitKey.setMaxDate(maxDate);
            loadYeartraitKey.setMinDate(minDate);
            List<LoadDaytrait> listDay = daytraitService.findChartsDataByKey(loadYeartraitKey);
            if (listDay.size() != 0) {
                chartsList.add(listDay);
            }
        }

        //生成年曲线数据
        String[] xZhou = createXZhouByList(chartsList);
        for (List<LoadDaytrait> l : chartsList) {
            ChartsOption chartsOption = new ChartsOption();
            Series series = new Series();
            series.setType("line");
            series.setSmooth(true);
            String yearName = l.get(0).getMdate().substring(0, 4);
            series.setName(yearName);
            String[] chartsData = new String[xZhou.length];
            List<Long> cMdate = new ArrayList<>();
            List<Long> xMdate = new ArrayList<>();
            for (LoadDaytrait d : l) {
                cMdate.add(DateUtils.parse(d.getMdate(),DateUtils.YMDN_P).getTime());
            }
            for (String s : xZhou) {
                String a = yearName + s.replace("-", "");
                int temp = Integer.parseInt(yearName);
                boolean aBoo = (temp % 4 == 0 && temp % 100 != 0) || temp % 400 == 0;
                if (!aBoo && s.equals("02-29")) {
                    xMdate.add(1L);
                } else {
                    xMdate.add(DateUtils.parse(a,DateUtils.YMDN_P).getTime());
                }
            }
            int count = 0;
            for (int i = 0; i < xZhou.length; i++) {
                if (cMdate.contains(xMdate.get(i))) {
                    chartsData[i] = l.get(i - count).getAveload().intValue()+"";
                } else {
                    chartsData[i] = null;
                    count++;
                }
            }
            series.setData(chartsData);
            chartsOption.setLegend(yearName);
            chartsOption.setSeries(series);
            chartsOption.setHengZhou(xZhou);
            result.add(chartsOption);
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 根据前台传回来得年得集合，将其转换成正确得格式，并去重
     * @param year 前台请求得参数，
     * @return 返回正确格式得年得集合
     */
    private String[] createYearArray(String[] year) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < year.length; i++) {
            String temp;
            if (i == 0) {
                temp = year[i].substring(2, year[i].length() - 1);
            } else if (i == year.length - 1) {
                temp = year[i].substring(1, year[i].length() - 2);
            } else {
                temp = year[i].substring(1, year[i].length() - 1);
            }
            if (!list.contains(temp)) {
                list.add(temp);
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 根据参数制作曲线图的横轴坐标
     *
     * @param listDay 开始日期
     * @return 返回横轴坐标
     */
    private String[] createXZhou(List<LoadDaytrait> listDay) throws ParseException {
        String[] result = new String[listDay.size()];
        for (int i = 0; i < listDay.size(); i++) {
            String timeYear = listDay.get(i).getMdate();
            Date time = DateUtils.parse(timeYear,DateUtils.YMDN_P);
            String timeMonth = DateUtils.format(time,DateUtils.MDH_P);
            result[i] = timeMonth;
        }
        return result;
    }

    /**
     * 根据传回的list集合生成横轴得坐标，目的是解决不同年坐标不同得问题
     * @param chartsList 数据库返回的结果集
     * @return 返回横轴的坐标数组
     * @throws ParseException 类型转换异常，一般不会出现
     */
    private String[] createXZhouByList(List<List<LoadDaytrait>> chartsList) throws ParseException {
        List<Long> result = new ArrayList<>();
        for (List<LoadDaytrait> l : chartsList) {
            for (LoadDaytrait d : l) {
                String day = "2016" + d.getMdate().substring(4);
                Long time = DateUtils.parse(day,DateUtils.YMDN_P).getTime();
                if (!result.contains(time)) {
                    result.add(time);
                }
            }
        }
        Collections.sort(result);
        String[] resultArrays = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArrays[i] = DateUtils.format(new Date(result.get(i)),DateUtils.MDH_P);
        }
        return resultArrays;
    }
}
