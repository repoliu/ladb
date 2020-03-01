package com.dky.controller.influencingFactorAnalysis;

import com.dky.controller.periodicityAnalysis.LoadWeektraitController;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.influ.Autovalue;
import com.dky.entity.influ.LoadRainInfo;
import com.dky.entity.influ.LoadRainLevel;
import com.dky.entity.influ.vo.RainSelect;
import com.dky.entity.influ.vo.WeatherResult;
import com.dky.service.common.DbareaService;
import com.dky.service.influencingFactorAnalysis.*;
import com.dky.thread.influ.FindIptRcd;
import com.dky.thread.influ.FindRstCrtsOptThread;
import com.dky.util.PythonOperation;
import com.dky.util.ReadProperties;
import com.dky.util.DateUtils;
import com.dky.util.forecast.DygraphsTableToArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 气象因素查询Controller类
 * 1、通过load请求加载气象因素Tab页
 *
 * @author YangSL
 */
@Controller
@RequestMapping("/influence/weather")
public class WeatherFactorController {


    Logger logger = Logger.getLogger(WeatherFactorController.class.getName());

    private final AutovalueService autovalueService;
    private final WeatherService weatherService;
    private final LoadRainInfoService loadRainInfoService;
    private final QstationService qstationService;
    private final LoadRainLevelService loadRainLevelService;
    private final DbareaService dbareaService;
    /**
     * 使用构造方法注入service，可以明确成员变量的加载顺序；
     * 加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变；
     */
    @Autowired
    public WeatherFactorController(WeatherService weatherService, QstationService qstationService, LoadRainLevelService loadRainLevelService, AutovalueService autovalueService,
                                   LoadRainInfoService loadRainInfoService,DbareaService dbareaService) {
        this.weatherService = weatherService;
        this.qstationService = qstationService;
        this.loadRainLevelService = loadRainLevelService;
        this.autovalueService = autovalueService;
        this.loadRainInfoService = loadRainInfoService;
        this.dbareaService=dbareaService;
    }


    /**
     * load方法，接受前台请求，将气象因素所需属性返回jsp;
     *
     * @param area  地区的唯一标识；
     * @param mdate 时间标识；
     * @return 返回影响因素分析的气象因素页面;
     */
    @RequestMapping("load")
    public String load(Integer area, String mdate, Model model) throws ParseException, InterruptedException {
        WeatherResult result = new WeatherResult();
        /* 当页面初始化时，进入气象因素首页，此时area和mdate为空，在for_hour_weather中找最新记录，对其赋值；
         * 如果不是页面初始化，则area和mdate不为空，将其值赋给 @result 对应的值；
         */
        if (area == null) {
            area = Integer.parseInt(ReadProperties.readProperties("influWeatherArea"));
        }
        if (mdate == null) {
            //result = weatherService.findAreaAndMdate();
            Date maxMdate = weatherService.findMdateByArea(area);
            result.setArea(area);
            mdate = DateUtils.format(maxMdate, DateUtils.YMDH_P);
            result.setMdate(mdate);
        } else {
            result.setArea(area);
            result.setMdate(mdate);
        }

        //返回两个气象因素曲线数据和支持度数据
        FindRstCrtsOptThread rstCrtsOptThread = new FindRstCrtsOptThread(weatherService, result, mdate);
        //返回气象信息和导入记录
        FindIptRcd iptRcd = new FindIptRcd(weatherService, result, mdate);
        Thread thread1 = new Thread(rstCrtsOptThread);
        Thread thread2 = new Thread(iptRcd);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        if (result.getTList() == null && result.getRList() == null && result.getSList() == null) {
            model.addAttribute("isEmpty", "true");
        }
        model.addAttribute("weather", result);
        return "influencingFactorAnalysis/weatherFactor";
    }

    /**
     * 降雨量表格
     *
     * @param request 请求数据
     * @return 页面地址
     */
    @RequestMapping("rainload")
    public String rainload(HttpServletRequest request) {
        String area = request.getParameter("area");
        String mdate = request.getParameter("mdate");
        try {
            String areaname=dbareaService.findAreanameByArea(Integer.parseInt(area));
            request.setAttribute("areaname", areaname);
            request.setAttribute("mdate", mdate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "influencingFactorAnalysis/rainSelect";
    }

    /**
     * 返回降雨量图表数据
     *
     * @param request 请求数据
     * @return 表格数据
     */
    @RequestMapping("rainSelect")
    @ResponseBody
    public Map rainSelect(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String date = request.getParameter("date");//时间
        String areaname = request.getParameter("areaname");//地区
        String rainfall = request.getParameter("rainfall");//降雨的大小
        //这个是查出来的地区id
        String stcd = qstationService.findStcdByAreaname(areaname);
        //所选的地区在数据库是否存在
        List<Autovalue> list = new LinkedList<>();

        List<List<DygraphsTable>> listList = new LinkedList<>();//表格的第一行，时间
        List<RainSelect> listListData = new LinkedList<>();//表格的其他行，具体数据
        if (stcd == null) {
        } else {
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
            //是否选择了雨量程度【小雨，中雨，大雨等等等】
            if (rainfall != null && rainfall != "") {
                LoadRainLevel loadRainLevel = new LoadRainLevel();
                loadRainLevel = loadRainLevelService.select(rainfall);
                if (loadRainLevel != null) {
                    autovalue.setRainMin(loadRainLevel.getRainMin());
                    autovalue.setRainMax(loadRainLevel.getRainMax());
                    loadRainInfo.setRainLeve(loadRainLevel.getLevelName());
                }
            }
            // String day = autovalueService.selectRainNumber(autovalue);
            List<LoadRainInfo> loadRainInfoList = new LinkedList<>();
            loadRainInfo.setAreaname(areaname);
            loadRainInfoList = loadRainInfoService.selectCopy(areaname, rainfall, date);
            //获得表格的第一行
            listList.add(DygraphsTableToArray.selectRain());

            String[] dayNumber = new String[6];                    //调用这个小方法确定降雨程度的天数，用来返回前端用的
            String[] strDay = new String[6];
            strDay[0] = "小雨";
            strDay[1] = "中雨";
            strDay[2] = "大雨";
            strDay[3] = "暴雨";
            strDay[4] = "大暴雨";
            strDay[5] = "特大暴雨";
            Map<String, Object> mapList = new HashMap<>();

            list = autovalueService.selectRain(autovalue);
/*
            if (list.size() == loadRainInfoList.size()) {
                for (int i = 0; i < loadRainInfoList.size(); i++) {
                    listListData.add(DygraphsTableToArray.selectRainArrayLoadRainInfo(loadRainInfoList.get(i)));
                }
                List<LoadRainInfo> infoList = new LinkedList<>();
                infoList = loadRainInfoService.selectDayNumber(loadRainInfo);
                for (int i = 0; i < strDay.length; i++) {
                    int number = 0;
                    for (int j = 0; j < infoList.size(); j++) {
                        if (strDay[i].equals(infoList.get(j).getRainLeve())) {
                            dayNumber[i] = ":" + infoList.get(j).getRain().toString() + "天";
                        } else {
                            number++;
                        }
                    }
                    if (number == infoList.size()) {
                        dayNumber[i] = ":0天";
                    }
                }
            } else if (list != null) {
                //把现在这个条件查询的数据在LoadRainInfo表中删掉
                loadRainInfo.setRainLeve(rainfall);
                loadRainInfo.setModel(ReadProperties.readProperties("/db.properties", "currentSchema"));

                //这个是代表了小雨，中雨，大雨等等等的天数
                List<Autovalue> listDay = new LinkedList<>();

                //线程问题后，用这样的方式，有点慢
                listDay = autovalueService.selectRainDay(autovalue);

                //调用这个小方法确定降雨程度的天数，用来返回前端用的
                dayNumber = string6(listDay, strDay);
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        listListData.add(DygraphsTableToArray.selectRainArrayAutovalue(list.get(i)));
                    }
                }
            } else {
                List<Autovalue> listDay = new LinkedList<>();
                listDay = autovalueService.selectRainDay(autovalue);//数据库查询出来的降雨级别的天数
                if (listDay != null && listDay.size() > 0) {
                    //调用这个小方法确定降雨程度的天数，用来返回前端用的
                    dayNumber = string6(listDay, strDay);
                } else {
                    dayNumber[0] = ":0天";
                    dayNumber[1] = ":0天";
                    dayNumber[2] = ":0天";
                    dayNumber[3] = ":0天";
                    dayNumber[4] = ":0天";
                    dayNumber[5] = ":0天";
                }
            }

*/

            if (list != null && loadRainInfoList != null) {
                //判断LoadRainInfo和Autovalue表的数据是否一样多，一样多在LoadRainInfo表查，否则在Autovalue表查
                if (list.size() <=loadRainInfoList.size()) {
                    for (int i = 0; i < loadRainInfoList.size(); i++) {
                        listListData.add(DygraphsTableToArray.selectRainArrayLoadRainInfo(loadRainInfoList.get(i)));
                    }
                    List<LoadRainInfo> infoList = new LinkedList<>();
                    infoList = loadRainInfoService.selectDayNumber(loadRainInfo);
                    for (int i = 0; i < strDay.length; i++) {
                        int number = 0;
                        for (int j = 0; j < infoList.size(); j++) {
                            if (strDay[i].equals(infoList.get(j).getRainLeve())) {
                                dayNumber[i] = ":" + infoList.get(j).getRain().toString() + "天";
                            } else {
                                number++;
                            }
                        }
                        if (number == infoList.size()) {
                            dayNumber[i] = ":0天";
                        }
                    }
                } else {
                    //把现在这个条件查询的数据在LoadRainInfo表中删掉
                    loadRainInfo.setRainLeve(rainfall);
                    loadRainInfo.setModel(ReadProperties.readProperties("/db.properties", "currentSchema"));
                    loadRainInfo.setAreaname(areaname);
                    //这个是代表了小雨，中雨，大雨等等等的天数
                    List<Autovalue> listDay = new LinkedList<>();

                    List<Autovalue> listAou=new ArrayList<>();
                    if (loadRainInfoList.size() > 0) {
                        List<String> contrastList = new ArrayList<>();
                        SimpleDateFormat formatDateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (int i=0;i<loadRainInfoList.size();i++){
                            contrastList.add(formatDateTime.format(loadRainInfoList.get(i).getTime()));
                        }
                        listAou=autovalueService.selectDateTime(contrastList  ,  qstationService.findStcdByAreaname(areaname));
                    }
                    //调用这个小方法确定降雨程度的天数，用来返回前端用的
                    listDay = autovalueService.selectRainDay(autovalue);//数据库查询出来的降雨级别的天数
                    dayNumber = string6(listDay, strDay);
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            listListData.add(DygraphsTableToArray.selectRainArrayAutovalue(list.get(i)));
                        }
                    }
                    try {
                        if (loadRainInfoList != null && loadRainInfoList.size() > 0) {
                            insertLoadRainInfo(listAou, areaname);//
                        }else {
                            insertLoadRainInfo(list, areaname);//
                        }
                    } catch (Exception e) {
                        logger.error("insert+++++++++++++++++++++++++++++++++++=error：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } else  if (list != null ) {
                //把现在这个条件查询的数据在LoadRainInfo表中删掉
                loadRainInfo.setRainLeve(rainfall);
                loadRainInfo.setModel(ReadProperties.readProperties("/db.properties", "currentSchema"));
                loadRainInfo.setAreaname(areaname);
                //这个是代表了小雨，中雨，大雨等等等的天数
                List<Autovalue> listDay = new LinkedList<>();

                //调用这个小方法确定降雨程度的天数，用来返回前端用的
                listDay = autovalueService.selectRainDay(autovalue);//数据库查询出来的降雨级别的天数
                dayNumber = string6(listDay, strDay);
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        listListData.add(DygraphsTableToArray.selectRainArrayAutovalue(list.get(i)));
                    }
                }
                try {
                        insertLoadRainInfo(list, areaname);//
                } catch (Exception e) {
                    logger.error("insert+++++++++++++++++++++++++++++++++++=error：" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                List<Autovalue> listDay = new LinkedList<>();
                listDay = autovalueService.selectRainDay(autovalue);//数据库查询出来的降雨级别的天数
                if (listDay != null && listDay.size() > 0) {
                    //调用这个小方法确定降雨程度的天数，用来返回前端用的
                    dayNumber = string6(listDay, strDay);
                } else {
                    dayNumber[0] = ":0天";
                    dayNumber[1] = ":0天";
                    dayNumber[2] = ":0天";
                    dayNumber[3] = ":0天";
                    dayNumber[4] = ":0天";
                    dayNumber[5] = ":0天";
                }
            }
            map.put("dayNumber", dayNumber);//降雨级别的天数，返回页面
            map.put("listList", listList);//表格的第一行，时间
            map.put("listListData", listListData);//表格的其他行，具体数据
        }
        return map;

    }

    public String[] string6(List<Autovalue> listDay, String[] strDay) {
        String[] dayNumber = new String[6];
        for (int i = 0; i < strDay.length; i++) {
            int number = 0;
            for (int j = 0; j < listDay.size(); j++) {
                if (strDay[i].equals(listDay.get(j).getStcd())) {
                    dayNumber[i] = ":" + listDay.get(j).getRain().toString() + "天";
                } else {
                    number++;
                }
            }
            if (number == listDay.size()) {
                dayNumber[i] = ":0天";
            }
        }
        return dayNumber;
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
        loadRainInfoService.insert(loadRainInfos);
    }

    /**
     * 根据气象类型返回曲线数据
     *
     * @param mdate 时间
     * @param area  地区id
     * @param type  气象类型
     * @return 曲线数据
     */
    @RequestMapping("changeGraph")
    @ResponseBody
    public String changeGraph(String mdate, Integer area, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("mdate", mdate);
        params.put("area", area);
        params.put("type", type);
        return weatherService.findGraphDataByParams(params);
    }

    /**
     * 调用python支持度分析脚本
     *
     * @param areaname 地区名
     * @param mdate    时间
     * @return 聚类是否陈工
     * @throws ParseException 格式异常
     */
    @RequestMapping(value = "/clusterAnalysis", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean clusterAnalysis(String areaname, String mdate) throws ParseException {
        boolean b = false;
        try {
            //areaname = new String(areaname.getBytes("UTF-8"));
            String path = WeatherAnalysisController.class.getResource("/").getPath().substring(1);
            String[] args11 = new String[]{"python", path + "pythonScript/weatherDayCf.py", areaname, mdate.replace("-", "")};
            b = PythonOperation.pythonExcute(args11);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }
}
