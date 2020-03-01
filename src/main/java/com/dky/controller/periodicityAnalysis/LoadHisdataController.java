package com.dky.controller.periodicityAnalysis;

import com.dky.entity.*;
import com.dky.service.periodicityAnalysis.DaytraitService;
import com.dky.service.periodicityAnalysis.LoadMonthtypeload288Service;
import com.dky.service.periodicityAnalysis.LoadMonthtypeload96Service;
import com.dky.util.String288;
import com.dky.util.String96;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.service.periodicityAnalysis.LoadHisdataService;
import com.dky.util.TimePoint;
import com.dky.util.UtilType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 14:15
 */
@Controller
@RequestMapping("loadHisdataController")
public class LoadHisdataController {

Logger logger= Logger.getLogger(LoadHisdataController.class.getName());

    @Autowired
    private LoadHisdataService loadHisdataService;

    @Autowired
    private DaytraitService daytraitService;

    @Autowired
    private LoadMonthtypeload96Service loadMonthtypeload96Service;

    @Autowired
    private LoadMonthtypeload288Service loadMonthtypeload288Service;


    public String  dateTime(String string){
        String str=null;
        str=string.replaceAll("-","");
        //str=string.substring(0,4)+string.substring(5,7);
        return str;
    }


    /**
     * 现在改成了这一个方法返回所有的曲线,只要是曲线图的方法都在这获取
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectQuery96")
    @ResponseBody
    public List<List<ChartsOption>> selectQuery96(HttpServletRequest request) {
        String queRenShiJain = request.getParameter("queRenShiJain");
        queRenShiJain=dateTime(queRenShiJain);
        String dianShu = request.getParameter("dianShu");
        String area = request.getParameter("area");
        if (area==null){
            area="1";
        }
        String daytype = request.getParameter("daytype");
        List<List<ChartsOption>> lists = new ArrayList<List<ChartsOption>>();
        try {
            //工作日的曲线
            List<ChartsOption> gongZuoRi = new ArrayList<ChartsOption>();
            gongZuoRi = dianXing(queRenShiJain, dianShu, area, UtilType.MONTH_ONE);
            //休息日的曲线
            List<ChartsOption> xiuXiRi = new ArrayList<ChartsOption>();
            xiuXiRi = dianXing(queRenShiJain, dianShu, area, UtilType.MONTH_TWO);
            //最大负荷的曲线图
            List<ChartsOption> zuiDaFuHe = new ArrayList<ChartsOption>();
            zuiDaFuHe = selectQuery(queRenShiJain, dianShu, area);
            //最小负荷的曲线图
            List<ChartsOption> zuiXiaoFuHe = new ArrayList<ChartsOption>();
            zuiXiaoFuHe = selectZuiXiao(queRenShiJain, dianShu, area);
            //最大峰谷的曲线图
            List<ChartsOption> zuiDaFengGu = new ArrayList<ChartsOption>();
            zuiDaFengGu = selectDaFengGu(queRenShiJain, dianShu, area);
            //最小峰谷的曲线图
            List<ChartsOption> zuiXiaoFengGu = new ArrayList<ChartsOption>();
            zuiXiaoFengGu = selectXiaoFengGu(queRenShiJain, dianShu, area);
                lists.add(gongZuoRi);
                lists.add(xiuXiRi);
                lists.add(zuiDaFuHe);
                lists.add(zuiXiaoFuHe);
                lists.add(zuiDaFengGu);
                lists.add(zuiXiaoFengGu);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectQuery96出错："+e.getMessage());
        }
        return lists;
    }


    /**
     * 最大负荷曲线
     *
     * @param
     * @return
     */
    public List<ChartsOption> selectQuery(String queRenShiJain, String dianShu, String area) {
        List<ChartsOption> optionList = null;//最终需要转成json的
        try {
            //先查一下这个最大负荷曲线
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            //最大负荷的记录表
            loadDaytrait = zuiDaFuHe(queRenShiJain, area);
            //在1440表查出具体的数据
            LoadHisdataKey loadHisdataKey = new LoadHisdataKey();
            loadHisdataKey.setMdate(loadDaytrait.getMdate());
            loadHisdataKey.setArea(Integer.parseInt(area));
            optionList = new ArrayList<ChartsOption>();
            List<LoadHisdata> list = new ArrayList<LoadHisdata>();
            String [] color={ "#B15BFF","#68CFE8","#B00","#FF1493","#FF9432"};
            if (dianShu.equals("96")) {
                list = loadHisdataService.selectQuery96(loadHisdataKey);
            } else {
                list = loadHisdataService.selectQuery288(loadHisdataKey);
            }
            TimePoint timePoint = new TimePoint();
            for (int i = 0; i < list.size(); i++) {
                ChartsOption chartsOption = new ChartsOption();
                Series series = new Series();
                LoadHisdata li = list.get(i);
                chartsOption.setLegend(li.getMdate());
                if (dianShu.equals("96")) {
                    chartsOption.setHengZhou(String96.jihe96());
                } else {
                    chartsOption.setHengZhou(String288.jihe288());
                }
                series.setName(li.getMdate());
                series.setSmooth(true);
                series.setType("line");
                if (dianShu.equals("96")) {
                    series.setData(String96.toStrings(li));
                } else {
                    series.setData(String288.toStrings(li));
                }
                chartsOption.setSeries(series);
                chartsOption.setColor(color);
                optionList.add(chartsOption);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectQuery出错："+e.getMessage());
        }
        return optionList;
    }
    //最大负荷的记录表
    public LoadDaytrait zuiDaFuHe(String queRenShiJain, String area) {
        LoadDaytrait daytrait = null;
        try {
            String madeOme = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            loadDaytrait.setMadeOme(madeOme);
            loadDaytrait.setMadeTwo(madeTwo);
            loadDaytrait.setArea(Integer.parseInt(area));
            List<LoadDaytrait> loadDaytraits = new ArrayList<LoadDaytrait>();
            loadDaytraits = daytraitService.selectQuery(loadDaytrait);
            daytrait = new LoadDaytrait();
            for (int i = 0; i < loadDaytraits.size(); i++) {
                if (i == 0) {
                    daytrait = loadDaytraits.get(0);
                } else {
                    if (daytrait.getMaxload().intValue() > loadDaytraits.get(i).getMaxload().intValue()) {
                    } else {
                        daytrait = loadDaytraits.get(i);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的zuiDaFuHe出错："+e.getMessage());
        }
        return daytrait;
    }

    //最小符合曲线
    public List<ChartsOption> selectZuiXiao(String queRenShiJain, String dianShu, String area) {
        List<ChartsOption> optionList = null;//最终需要转成json的
        try {
            //先查一下这个最小负荷曲线
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            //最小负荷的记录表
            loadDaytrait = zuiXiaoFuHe(queRenShiJain, area);
            //在1440表查出具体的数据
            LoadHisdataKey loadHisdataKey = new LoadHisdataKey();
            loadHisdataKey.setMdate(loadDaytrait.getMdate());
            loadHisdataKey.setArea(Integer.parseInt(area));
            optionList = new ArrayList<ChartsOption>();
            List<LoadHisdata> list = new ArrayList<LoadHisdata>();
            String [] color={ "#B15BFF","#68CFE8","#B00","#FF1493","#FF9432"};
            if (dianShu.equals("96")) {
                list = loadHisdataService.selectQuery96(loadHisdataKey);
            } else {
                list = loadHisdataService.selectQuery288(loadHisdataKey);
            }
            TimePoint timePoint = new TimePoint();
            for (int i = 0; i < list.size(); i++) {
                ChartsOption chartsOption = new ChartsOption();
                Series series = new Series();
                LoadHisdata li = list.get(i);
                chartsOption.setLegend(li.getMdate());
                if (dianShu.equals("96")) {
                    chartsOption.setHengZhou(String96.jihe96());
                } else {
                    chartsOption.setHengZhou(String288.jihe288());
                }
                series.setName(li.getMdate());
                series.setSmooth(true);
                series.setType("line");
                if (dianShu.equals("96")) {
                    series.setData(String96.toStrings(li));
                } else {
                    series.setData(String288.toStrings(li));
                }
                chartsOption.setSeries(series);
                chartsOption.setColor(color);
                optionList.add(chartsOption);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectZuiXiao出错："+e.getMessage());
        }
        return optionList;
    }

    //最小负荷的记录表
    public LoadDaytrait zuiXiaoFuHe(String queRenShiJain, String area) {
        LoadDaytrait daytrait = null;
        try {
            String madeOme = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            loadDaytrait.setMadeOme(madeOme);
            loadDaytrait.setMadeTwo(madeTwo);
            loadDaytrait.setArea(Integer.parseInt(area));
            List<LoadDaytrait> loadDaytraits = new ArrayList<LoadDaytrait>();
            loadDaytraits = daytraitService.selectQuery(loadDaytrait);
            daytrait = new LoadDaytrait();
            for (int i = 0; i < loadDaytraits.size(); i++) {
                if (i == 0) {
                    daytrait = loadDaytraits.get(0);
                } else {
                    if (daytrait.getMaxload().intValue() < loadDaytraits.get(i).getMaxload().intValue()) {
                    } else {
                        daytrait = loadDaytraits.get(i);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的zuiXiaoFuHe出错："+e.getMessage());
        }
        return daytrait;
    }


    /**
     * 最大峰谷曲线
     *
     * @param
     * @return
     */
    public List<ChartsOption> selectDaFengGu(String queRenShiJain, String dianShu, String area) {
        List<ChartsOption> optionList = null;//最终需要转成json的
        try {
            //先查一下这个最大峰谷荷曲线
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            //最大峰谷的记录表
            loadDaytrait = zuiDaFengGu(queRenShiJain, area);
            //在1440表查出具体的数据
            LoadHisdataKey loadHisdataKey = new LoadHisdataKey();
            loadHisdataKey.setMdate(loadDaytrait.getMdate());
            loadHisdataKey.setArea(Integer.parseInt(area));
            optionList = new ArrayList<ChartsOption>();
            List<LoadHisdata> list = new ArrayList<LoadHisdata>();
            String [] color={ "#FF1493","#B15BFF","#68CFE8","#B00","#FF9432"};
            if (dianShu.equals("96")) {
                list = loadHisdataService.selectQuery96(loadHisdataKey);
            } else {
                list = loadHisdataService.selectQuery288(loadHisdataKey);
            }
            TimePoint timePoint = new TimePoint();
            for (int i = 0; i < list.size(); i++) {
                ChartsOption chartsOption = new ChartsOption();
                Series series = new Series();
                LoadHisdata li = list.get(i);
                chartsOption.setLegend(li.getMdate());
                if (dianShu.equals("96")) {
                    chartsOption.setHengZhou(String96.jihe96());
                } else {
                    chartsOption.setHengZhou(String288.jihe288());
                }
                series.setName(li.getMdate());
                series.setSmooth(true);
                series.setType("line");
                if (dianShu.equals("96")) {
                    series.setData(String96.toStrings(li));
                } else {
                    series.setData(String288.toStrings(li));
                }
                chartsOption.setSeries(series);
                chartsOption.setColor(color);
                optionList.add(chartsOption);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectDaFengGu出错："+e.getMessage());
        }
        return optionList;
    }
    //最大峰谷的记录表
    public LoadDaytrait zuiDaFengGu(String queRenShiJain, String area) {
        LoadDaytrait daytrait = null;
        try {
            String madeOme = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            loadDaytrait.setMadeOme(madeOme);
            loadDaytrait.setMadeTwo(madeTwo);
            loadDaytrait.setArea(Integer.parseInt(area));
            List<LoadDaytrait> loadDaytraits = new ArrayList<LoadDaytrait>();
            loadDaytraits = daytraitService.selectQuery(loadDaytrait);
            daytrait = new LoadDaytrait();
            for (int i = 0; i < loadDaytraits.size(); i++) {
                if (i == 0) {
                    daytrait = loadDaytraits.get(0);
                } else {
                    if (daytrait.getDiffer().intValue() > loadDaytraits.get(i).getDiffer().intValue()) {
                    } else {
                        daytrait = loadDaytraits.get(i);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的zuiDaFengGu出错："+e.getMessage());
        }
        return daytrait;
    }



    /**
     * 最小峰谷曲线
     *
     * @param
     * @return
     */
    public List<ChartsOption> selectXiaoFengGu(String queRenShiJain, String dianShu, String area) {
        List<ChartsOption> optionList = null;//最终需要转成json的
        try {
            //先查一下这个最大峰谷荷曲线
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            //最小峰谷记录表
            loadDaytrait = zuiXiaoFengGu(queRenShiJain, area);
            //在1440表查出具体的数据
            LoadHisdataKey loadHisdataKey = new LoadHisdataKey();
            loadHisdataKey.setMdate(loadDaytrait.getMdate());
            loadHisdataKey.setArea(Integer.parseInt(area));
            optionList = new ArrayList<ChartsOption>();
            List<LoadHisdata> list = new ArrayList<LoadHisdata>();
            String [] color={ "#FF1493","#B15BFF","#68CFE8","#B00","#FF9432"};
            if (dianShu.equals("96")) {
                list = loadHisdataService.selectQuery96(loadHisdataKey);
            } else {
                list = loadHisdataService.selectQuery288(loadHisdataKey);
            }
            TimePoint timePoint = new TimePoint();
            for (int i = 0; i < list.size(); i++) {
                ChartsOption chartsOption = new ChartsOption();
                Series series = new Series();
                LoadHisdata li = list.get(i);
                chartsOption.setLegend(li.getMdate());
                if (dianShu.equals("96")) {
                    chartsOption.setHengZhou(String96.jihe96());
                } else {
                    chartsOption.setHengZhou(String288.jihe288());
                }
                series.setName(li.getMdate());
                series.setSmooth(true);
                series.setType("line");
                if (dianShu.equals("96")) {
                    series.setData(String96.toStrings(li));
                } else {
                    series.setData(String288.toStrings(li));
                }
                chartsOption.setSeries(series);
                chartsOption.setColor(color);
                optionList.add(chartsOption);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectXiaoFengGu出错："+e.getMessage());
        }
        return optionList;
    }

    //最小峰谷记录表
    public LoadDaytrait zuiXiaoFengGu(String queRenShiJain, String area) {
        LoadDaytrait daytrait = null;
        try {
            String madeOme = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            loadDaytrait.setMadeOme(madeOme);
            loadDaytrait.setMadeTwo(madeTwo);
            loadDaytrait.setArea(Integer.parseInt(area));
            List<LoadDaytrait> loadDaytraits = new ArrayList<LoadDaytrait>();
            loadDaytraits = daytraitService.selectQuery(loadDaytrait);
            daytrait = new LoadDaytrait();
            for (int i = 0; i < loadDaytraits.size(); i++) {
                if (i == 0) {
                    daytrait = loadDaytraits.get(0);
                } else {
                    if (daytrait.getDiffer().intValue() < loadDaytraits.get(i).getDiffer().intValue()) {
                    } else {
                        daytrait = loadDaytraits.get(i);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的zuiXiaoFengGu出错："+e.getMessage());
        }
        return daytrait;
    }

    /**
     * 这个是进度条的方法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectMDataOneTwo")
    @ResponseBody
    public Map selectOneTwo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String queRenShiJain = request.getParameter("queRenShiJain");
            queRenShiJain=dateTime(queRenShiJain);
            String dianShu = request.getParameter("dianShu");
            String area = request.getParameter("area");
            if (area==null){//没有获得到地区的标号默认1，1代表国网
                area="1";
            }
            //代表了所选择这个月的1号和31号
            String madeOme = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";

            List<LoadDaytrait> list = new ArrayList<LoadDaytrait>();
            LoadHisdata loadHisdata = new LoadHisdata();
            loadHisdata.setArea(Integer.parseInt(area));
            //这个是获得最小时间的方法，用来禁用时间选择框的
            String dateMin = loadHisdataService.selectOne(loadHisdata);
            //这个是获得最大时间的方法，用来禁用时间选择框的
            String dateMax = loadHisdataService.selectTwo(loadHisdata);
            //获得5个按钮时间的map
            Map<String, String> map5 = String96.StringToMap(queRenShiJain);
            String anMin="";
            String anMax="";
            if (dateMax!=null&&dateMax!=""){
                anMax = dateMax.substring(0, 6);//这个时间用来对比5个按钮
                map.put("anMax", anMax);//数据库中最小的时间，这个是来禁用5个时间按钮的的
            }
            if (dateMin!=null&&dateMin!=""){
                anMin = dateMin.substring(0, 6);//这个时间用来对比5个按钮
                map.put("anMin", anMin);//数据库中最大的时间，这个是来禁用5个时间按钮的的
            }
            //5个时间按钮的值
            map.put("one", map5.get("one"));
            map.put("two", map5.get("two"));
            map.put("three", map5.get("three"));
            map.put("four", map5.get("four"));
            map.put("five", map5.get("five"));

            dateMax = String96.StringToStringMax(dateMax);//数据库中最大的时间，这个是来禁用时间选择框的
            dateMin = String96.StringToString(dateMin);//数据库中最小的时间，这个是来禁用时间选择框的
            map.put("dateMax", dateMax);
            map.put("dateMin", dateMin);

            //这个是查询进度条传过去的实体参数
            LoadDaytrait loadDaytrait = new LoadDaytrait();
            loadDaytrait.setMadeOme(madeOme);
            loadDaytrait.setMadeTwo(madeTwo);
            loadDaytrait.setArea(Integer.parseInt(area));
            //这个是查询进度条用来就收返回的实体
            LoadDaytrait Daytrait = new LoadDaytrait();
            Daytrait = daytraitService.selectMaxMinAvgSum(loadDaytrait);
            if (Daytrait!=null) {
                map.put("maxload", Daytrait.getMaxload().setScale(2,BigDecimal.ROUND_HALF_UP).toString()); //月最大负荷
                map.put("maxloads", String96.BigToString(Daytrait.getMaxload())+"%");
                map.put("minload", Daytrait.getMinload().setScale(2,BigDecimal.ROUND_HALF_UP).toString()); //月最小负荷
                map.put("minloads",String96.BigToString(Daytrait.getMinload())+"%");
                map.put("aveload", Daytrait.getAveload().setScale(2,BigDecimal.ROUND_HALF_UP)+"");//月平均负荷
                map.put("aveloads",String96.BigToString(Daytrait.getAveload())+"%");//
                map.put("loadrate",String96.jinDuTiao(Daytrait.getLoadrate())+"%");// 月负荷率
                map.put("minloadrate",String96.jinDuTiao(Daytrait.getMinloadrate())+"%");// 月最小负荷率
                map.put("differrate",String96.jinDuTiao(Daytrait.getDifferrate())+"%");// 月最大峰谷差率
                map.put("differ",Daytrait.getDiffer().setScale(2,BigDecimal.ROUND_HALF_UP)+"");// 月平均峰谷差
                map.put("differs",String96.BigToString(Daytrait.getDiffer())+"%");
                map.put("differratePing",String96.jinDuTiao(Daytrait.getFmmaxload())+"%");//平均谷峰差率
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的selectOneTwo出错："+e.getMessage());
        }
        return map;
    }

    //获得狗工作日和休息日的曲线图
    public List<ChartsOption> dianXing(String queRenShiJain, String dianShu, String area, Integer daytype) {
        List<ChartsOption> optionList = null;//最终需要转成json的
        try {
            optionList = new ArrayList<ChartsOption>();
            String madeOne = queRenShiJain + "01";
            String madeTwo = queRenShiJain + "31";
            String [] color={ "#B15BFF","#68CFE8","#B00","#FF1493","#FF9432"};
            if (dianShu.equals("96")) {
                LoadMonthtypeload96 loadMonthtypeload96 = new LoadMonthtypeload96();
                loadMonthtypeload96.setEndDay(madeTwo);
                loadMonthtypeload96.setStartDay(madeOne);
                loadMonthtypeload96.setArea(Integer.parseInt(area));
                loadMonthtypeload96.setDaytype(daytype);
                List<LoadMonthtypeload96> list = new ArrayList<LoadMonthtypeload96>();
                //去数据库查数据
                list = loadMonthtypeload96Service.selectQuery96(loadMonthtypeload96);
                TimePoint timePoint = new TimePoint();
                for (int i = 0; i < list.size(); i++) {
                    ChartsOption chartsOption = new ChartsOption();
                    chartsOption.setHengZhou(String96.jihe96());
                    Series series = new Series();
                    LoadMonthtypeload96 li = list.get(i);
                    chartsOption.setLegend(li.getStartDay());
                    series.setName(li.getStartDay());
                    series.setSmooth(true);
                    series.setType("line");
                    series.setData(String96.toStringDianXing(li));
                    chartsOption.setSeries(series);
                    chartsOption.setColor(color);
                    optionList.add(chartsOption);
                }
            } else {
                LoadMonthtypeload288 loadMonthtypeload288 = new LoadMonthtypeload288();
                loadMonthtypeload288.setEndDay(madeTwo);
                loadMonthtypeload288.setStartDay(madeOne);
                loadMonthtypeload288.setArea(Integer.parseInt(area));
                loadMonthtypeload288.setDaytype(daytype);
                List<LoadMonthtypeload288> list = new ArrayList<LoadMonthtypeload288>();
                //去数据库查数据
                list = loadMonthtypeload288Service.selectQuery288(loadMonthtypeload288);
                TimePoint timePoint = new TimePoint();
                for (int i = 0; i < list.size(); i++) {
                    ChartsOption chartsOption = new ChartsOption();
                    chartsOption.setHengZhou(String288.jihe288());
                    Series series = new Series();
                    LoadMonthtypeload288 li = list.get(i);
                    chartsOption.setLegend(li.getStartDay());
                    //96点表,288点的表start_day和end_day记录了这个月的1号和31号，之前用start_day作为他的图例，
                    // 如果有多条曲线，那么这个月的曲线图的图例都会是同一个时间，所以把他图例去掉了
                    series.setName(li.getStartDay());
                    series.setSmooth(true);
                    series.setType("line");
                    series.setData(String288.toStringDianXing(li));
                    chartsOption.setSeries(series);
                    chartsOption.setColor(color);
                    optionList.add(chartsOption);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("查询月loadHisdataController的dianXing出错："+e.getMessage());
        }
        return optionList;
    }


}
