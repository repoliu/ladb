package com.dky.controller.influencingFactorAnalysis;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.LoadDaytrait;
import com.dky.entity.influ.vo.*;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.TimeSelectButtonOfDayLoad;
import com.dky.service.common.DbareaService;
import com.dky.service.influencingFactorAnalysis.LoadRainInfoService;
import com.dky.service.influencingFactorAnalysis.WeatherAnalysisService;
import com.dky.service.influencingFactorAnalysis.WeatherService;
import com.dky.service.periodicityAnalysis.DaytraitService;
import com.dky.util.PythonOperation;
import com.dky.util.ReadProperties;
import com.dky.util.DateUtils;
import com.dky.util.TimePoint;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 气象因素分析Controller类
 *
 * @Author: YangSL
 * @Date: Create in 15:04 2018/1/4
 */
@Controller
@RequestMapping("/influence/weatherAnalysis")
public class WeatherAnalysisController {
    private final WeatherAnalysisService weatherAnalysisService;
    private final WeatherService weatherService;
    private final DbareaService dbareaService;
    private final LoadRainInfoService loadRainInfoService;
    private final DaytraitService daytraitService;
    @Autowired
    public WeatherAnalysisController(WeatherAnalysisService weatherAnalysisService,
                                     WeatherService weatherService,
                                     DbareaService dbareaService,
                                     LoadRainInfoService loadRainInfoService,
                                     DaytraitService daytraitService) {
        this.weatherAnalysisService = weatherAnalysisService;
        this.weatherService = weatherService;
        this.dbareaService = dbareaService;
        this.loadRainInfoService = loadRainInfoService;
        this.daytraitService = daytraitService;
    }

    Logger logger = Logger.getLogger(WeatherAnalysisController.class.getName());
    /**
     * 气象因素分析页面得全部加载方法
     * @param area 地区id
     * @param mdate 时间
     * @param analysisType 气象类型
     * @param model 模型
     * @return 页面地址
     * @throws ParseException 格式化异常，由时间格式化类抛出
     */
    @RequestMapping("load")
    public String load(Integer area, String mdate, String analysisType, Model model) throws ParseException {
        WeatherResult result = new WeatherResult();

        //地区设置
        if (area == null) {
            area = Integer.parseInt(ReadProperties.readProperties("influWeatherArea"));
        }
        result.setArea(area);

        //时间设置
        String minMdate = null;
        if (mdate != null && !mdate.equals("")) {
            mdate = mdate.replace("-", "");
      /*      String dateTrue=null;
            dateTrue=daytraitService.selectMaxDate(area);
            if (dateTrue!=null&&dateTrue!=""){
                mdate=dateTrue;
            }*/
            minMdate = DateUtils.findLastThirtyDayMdate(mdate);
        }

        //气象类型设置
        if (analysisType == null || analysisType.equals("")) {
            analysisType = ReadProperties.readProperties("analysisType");
        }
        result.setAnalysisType(analysisType);

        //设置参数
        Map<String, Object> params = new HashMap<>();
        params.put("mdate", mdate);
        params.put("area", area);
        params.put("minMdate", minMdate);
        params.put("aType", analysisType);

        //查询最近30日，日负荷信息
        List<LoadDaytrait> daytraitsNow = weatherAnalysisService.findListByParams(params);

        //判断daytraitsNow是否为空，true进行控制返回处理，false进行后续部分
        if (daytraitsNow.size() == 0) {
            model.addAttribute("isEmpty", "true");

            //时间选择按钮
            String buttonDate;
            if (mdate == null || mdate.equals("")) {
                buttonDate = DateUtils.format(new Date(),DateUtils.YMDN_P);
            } else {
                buttonDate = mdate.replace("-", "");
            }
            List<TimeSelectButtonOfDayLoad> timeSelectButton = TimePoint.createTimeButtonValue(buttonDate);
            result.setMdate(DateUtils.nToH(buttonDate));
            result.setTimeButton(timeSelectButton);
        } else {

            //对时间格式进行处理
            String nowDateString;
            if (mdate == null || mdate.equals("")) {
                nowDateString = daytraitsNow.get(0).getMdate();
            } else {
                nowDateString = mdate.replace("-", "");
            }
            params.put("mdate", nowDateString);
            params.put("minMdate", daytraitsNow.get(daytraitsNow.size() - 1).getMdate());

            //获取支持度图表数据
            List<ChartsOption> sList = weatherService.findSupportListByKey(params);
            result.setSList(JSONArray.toJSONString(sList));
            String areaname = (String) params.get("areaname");

            //获取当前负荷温度标签，是升温负荷还是降温负荷
            result.setTempSelect(weatherAnalysisService.setTempSelect(params));
            result.setAreaname(areaname);

            //获取最近30日，对应气象因素得数据
            List<RelationValue> valuesNow = weatherAnalysisService.findValuesByParams(params);

            //时间选择按钮信息
            List<TimeSelectButtonOfDayLoad> timeSelectButton = TimePoint.createTimeButtonValue(nowDateString);
            result.setTimeButton(timeSelectButton);

            //处理日期格式
            String lastDateString;
            if (mdate == null) {
                result.setMdate(DateUtils.nToH(nowDateString));
                lastDateString = (Integer.parseInt(nowDateString) - 10000) + "";
            } else {
                result.setMdate(DateUtils.nToH(mdate));
                lastDateString = (Integer.parseInt(mdate.replace("-", "")) - 10000) + "";
            }

            //获取气象信息数据
            WeatherInfo weatherInfo = weatherAnalysisService.setWeatherInfoByParams(params);
            result.setWeatherInfo(weatherInfo);

            //设置前一年相应时间参数
            long now = DateUtils.parse(lastDateString,DateUtils.YMDN_P).getTime();
            long then = now - 2592000000L;
            String lastMinDateString = DateUtils.format(new Date(then),DateUtils.YMDN_P);
            result.setLastMdate(DateUtils.nToH(lastDateString));
            params.put("minMdate", lastMinDateString);
            params.put("mdate", lastDateString);

            //获取前一年相关数据
            List<LoadDaytrait> daytraitsLast = weatherAnalysisService.findListByParams(params);
            List<RelationValue> valuesLast = weatherAnalysisService.findValuesByParams(params);

            //生成当年和前一年曲线数据
            String nowList = weatherAnalysisService.createLoadCharts(daytraitsNow, valuesNow, analysisType);
            String lastList = weatherAnalysisService.createLoadCharts(daytraitsLast, valuesLast, analysisType);
            result.setNowList(nowList);
            result.setLastList(lastList);
        }
        model.addAttribute("weather", result);

        return "influencingFactorAnalysis/weatherFactorAnalysis";
    }

    /**
     * 传入参数，返回对应曲线图数据，目前为气象因素分析对比日期得时间选择按钮使用
     * @param lastMdate 日期
     * @param area 地区id
     * @param analysisType 气象类型
     * @return 曲线图数据
     * @throws ParseException 类型转化异常
     */
    @RequestMapping(value = "/changeCharts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeCharts(String lastMdate, Integer area, String analysisType) throws ParseException {
        String mdate = lastMdate.replace("-", "");
        String minMdate = DateUtils.findLastThirtyDayMdate(mdate);
        Map<String, Object> params = new HashMap<>();
        params.put("mdate", mdate);
        params.put("area", area);
        params.put("minMdate", minMdate);
        params.put("aType", analysisType);
        List<LoadDaytrait> daytraitsNow = weatherAnalysisService.findListByParams(params);
        List<RelationValue> valuesNow = weatherAnalysisService.findValuesByParams(params);
        return weatherAnalysisService.createLoadCharts(daytraitsNow, valuesNow, analysisType);
    }

    /**
     * 同时改变两个曲线得展示数据
     * @param area 地区id
     * @param mdate 时间
     * @param analysisType 气象类型
     * @return 曲线数据
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "/changeTwoCharts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map changeTwoCharts(Integer area, String mdate, String analysisType) throws ParseException {
        //处理并设置参数
        mdate = mdate.replace("-", "");
        String minMdate = DateUtils.findLastThirtyDayMdate(mdate);
        Map<String, Object> params = new HashMap<>();
        params.put("mdate", mdate);
        params.put("area", area);
        params.put("minMdate", minMdate);
        params.put("aType", analysisType);

        //查询数据
        List<LoadDaytrait> daytraitsNow = weatherAnalysisService.findListByParams(params);
        params.put("mdate", daytraitsNow.get(0).getMdate());
        params.put("minMdate", daytraitsNow.get(daytraitsNow.size() - 1).getMdate());
        List<RelationValue> valuesNow = weatherAnalysisService.findValuesByParams(params);
        List<LoadDaytrait> daytraitsLast = weatherAnalysisService.findListByParams(params);
        List<RelationValue> valuesLast = weatherAnalysisService.findValuesByParams(params);
        String nowList = weatherAnalysisService.createLoadCharts(daytraitsNow, valuesNow, analysisType);
        String lastList = weatherAnalysisService.createLoadCharts(daytraitsLast, valuesLast, analysisType);
        Map<String, Object> result = new HashMap<>();
        result.put("nowList", nowList);
        result.put("lastList", lastList);
        return result;
    }

    /**
     * 查询聚类表格信息
     * @param area 地区id
     * @param mdate 时间
     * @param analysisType 气象类型
     * @return 表格数据
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "/cluster", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public List cluster(Integer area, String mdate, String analysisType) throws ParseException {
        //设置参数
        Map<String, Object> params = new HashMap<>();
        params.put("area", area);
        params.put("areaname", dbareaService.findAreanameByArea(area));
        params.put("mdate", mdate);
        mdate = mdate.substring(0, 4);
        params.put("year",mdate);
        String mindate = mdate + "0101";
        String maxdate = mdate + "1231";
        params.put("mindate", mindate);
        params.put("maxdate", maxdate);

        //设置标签
        boolean tempSelect = weatherAnalysisService.setTempSelect(params);
        String clusterFlag = createClusterFlag(analysisType,tempSelect);
        params.put("tempSelect", tempSelect);
        params.put("clusterFlag", clusterFlag);

        return weatherAnalysisService.findDatasByParams(params);
    }

    /**
     * 调用python聚类分析脚本
     * @param areaname 地区名
     * @param mdate 时间
     * @return 聚类是否成功
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "/clusterAnalysis", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean clusterAnalysis(String areaname, String mdate) throws ParseException {
        boolean b=false;
        try {
            //areaname = new String(areaname.getBytes("UTF-8"));
            loadRainInfoService.selectSL(mdate.substring(0,4),areaname);

            String path = WeatherAnalysisController.class.getResource("/").getPath().substring(1);
            String[] args11 = new String[]{"python", path + "pythonScript/clusterThread.py", areaname, mdate};
            b=PythonOperation.pythonExcute(args11);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return b;
    }

    /**
     * 聚类信息表格页面
     * @param type 聚类类型
     * @param area 地区id
     * @param mdate 时间
     * @param analysisType 气象类型
     * @param model 返回模型
     * @return 页面地址
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "allClusterData", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String allClusterData(String type, Integer area, String mdate, String analysisType, Model model) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("area", area);
        params.put("areaname", dbareaService.findAreanameByArea(area));
        resultMap.put("mdate", mdate);
        params.put("mdate", mdate);
        boolean tempSelect = weatherAnalysisService.setTempSelect(params);
        String year = mdate.substring(0, 4);
        params.put("mindate", year + "0101");
        params.put("maxdate", year + "1231");
        String clusterFlag = createClusterFlag(analysisType,tempSelect);
        params.put("tempSelect", tempSelect);
        params.put("clusterFlag", clusterFlag);
        List<ClusterData> result = weatherAnalysisService.findDatasByParams(params);
        resultMap.put("area", area);
        resultMap.put("year", year);
        resultMap.put("type", type);
        resultMap.put("analysisType", analysisType);
        resultMap.put("clusterDatas", result);
        model.addAttribute("allDatas", resultMap);
        return "influencingFactorAnalysis/allCluster";
    }

    /**
     * 聚类信息的具体数据
     * @param mdate 时间
     * @param year 年
     * @param area 地区id
     * @param type 聚类类型
     * @param analysisType 气象类型
     * @param page 页码
     * @param limit 每页条数
     * @return 表格数据
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "/tableDatas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> tableDatas(String mdate, String year, Integer area, String type, String analysisType, Integer page, Integer limit) throws ParseException {
        Map<String, Object> params = new HashMap<>();
        params.put("mindate", year + "0101");
        params.put("maxdate", year + "1231");
        params.put("area", area);
        params.put("year", mdate.substring(0, 4) + "__");
        params.put("areaname", dbareaService.findAreanameByArea(area));
        params.put("mdate", mdate);
        if (!"all".equals(type)) {
            params.put("type", type);
        }
        params.put("page", (page - 1) * limit);
        params.put("limit", limit);
        String clusterFlag = createClusterFlag(analysisType,weatherAnalysisService.setTempSelect(params));
        params.put("clusterFlag", clusterFlag);
        List<ClusterTableData> data = weatherAnalysisService.findClusterDatasByParams(params);
        Map<String, Object> result = new HashMap<>();
        int count = weatherAnalysisService.findClusterCountByParams(params);
        result.put("code", 0);
        result.put("count", count);
        result.put("type", type);
        result.put("msg", "");
        result.put("data", data);
        return result;
    }

    /**
     * 降雨量聚类页面
     * @param area 地区id
     * @param mdate 时间
     * @param loadvalue 聚类/差值曲线数据
     * @param model 返回模型
     * @return 页面地址
     */
    @RequestMapping(value = "rainClusterGraph", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String rainClusterGraph(Integer area, String mdate,Integer loadvalue,Model model) {
        //设置参数
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("area", area);
        String year = mdate.substring(0, 4);
        String areaname = dbareaService.findAreanameByArea(area);
        if (loadvalue == null) {
           loadvalue = 0;
        }
        result.put("area",area);
        result.put("areaname", areaname);
        result.put("year", year);
        result.put("loadvalue",loadvalue);
        List<String> chartsMap = new ArrayList<>();
        params.put("year", year);
        params.put("loadvalue",loadvalue);

        //查询各类型聚类曲线数据
        for (int i = 0; i < 5; i++) {
            params.put("clusterType", i);
            String charts = weatherAnalysisService.findRainCLusterGraphByParams(params);
            if(charts!=null) {
                chartsMap.add(charts);
            }
        }
        result.put("chartsMap",chartsMap);
        model.addAttribute("result", result);
        return "influencingFactorAnalysis/rainClusterGraphs";
    }

    /**
     * 极端天气下，各气象因素对负荷的影响
     * @param area 地区id
     * @param mdate 日期
     * @return 返回曲线数据
     */
    @ResponseBody
    @RequestMapping("/rainGraphs")
    public Map<String,Object> rainGraphs(Integer area,String mdate){
        mdate = mdate.replace("-","");
        try {
            return weatherAnalysisService.findRainGraphsByParams(area,mdate);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 极端天气时差值支持度分析
     * @param area 地区id
     * @param mdate 时间
     * @return 柱状图数据
     */
    @ResponseBody
    @RequestMapping("/dLoadSpot")
    public Map<String,Object> dLoadSpot(Integer area,String mdate){
        mdate = mdate.replace("-","");
        return weatherAnalysisService.findDLoadSpotByParams(area,mdate);
    }

    /**
     * 调用python神经网络算法计算负荷变化值
     * @param area 地区id
     * @param mdate 世纪按
     * @param tempValue 温度改变值
     * @param rainValue 降雨量改变值
     * @param wpValue 风力改变值
     * @return 返回结果
     */
    @ResponseBody
    @RequestMapping(value = "/multCalcul",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String multCalcul(Integer area,String mdate,Integer tempValue,Integer rainValue,Integer wpValue){
        String result= null;
        try {
            mdate = mdate.replace("-","");
            String path = WeatherAnalysisController.class.getResource("/").getPath().substring(1);
            String[] args11 = new String[]{"python", path + "pythonScript/loaddataforecast.py", area.toString(), mdate,tempValue.toString(),rainValue.toString(),wpValue.toString()};
            result = PythonOperation.pythonExcute2(args11);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过不同得气象类型返回对应的聚类类型
     * @param analysisType 气象类型
     * @param boo 支持度标签
     * @return 聚类类型
     */
    private String createClusterFlag(String analysisType,boolean boo){
        switch (analysisType) {
            case "温度":
                if (boo) {
                    return "MAXTEMP";
                } else {
                    return  "MINTEMP";
                }
            default:
                return null;
        }
    }
}
