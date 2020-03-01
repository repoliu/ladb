package com.dky.controller.forecastAnalysis;

import com.dky.controller.periodicityAnalysis.LoadWeektraitController;
import com.dky.entity.LoadForesult;
import com.dky.entity.LoadHisdata;
import com.dky.entity.forecast.DygraphsEntity;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.service.common.DbareaService;
import com.dky.service.forecastAnalysis.LoadForesultService;
import com.dky.service.periodicityAnalysis.LoadHisdataService;
import com.dky.util.ForecastUtils;
import com.dky.util.ReadProperties;
import com.dky.util.String96;
import com.dky.util.forecast.DygraphsTableToArray;
import com.dky.util.forecast.DygraphsTableUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("forecast/day")
public class ForecastController {

    Logger logger = Logger.getLogger(LoadWeektraitController.class.getName());

    private final DbareaService dbareaService;
    @Autowired
    private LoadHisdataService loadHisdataService;

    @Autowired
    private LoadForesultService loadForesultService;

    @Autowired
    public ForecastController(DbareaService dbareaService) {
        this.dbareaService = dbareaService;
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public String periodIndex() {
        return "forecastAnalysis/froecastIndex";
    }

    //这个是加载的时候的方法
    @RequestMapping(value = "/load")
    public String load(HttpServletRequest request) {
        String date = request.getParameter("date");//这个是时间
        String area = request.getParameter("area");//地区id
        String areaname = request.getParameter("areaname");//地区名称，这个没有用到
        String dateScope = request.getParameter("dateScope");//这个是用来确定修改曲线的时间范围
        String uiAddValue = request.getParameter("uiAddValue");//对曲线的修改增加的值
        String uiLessValue = request.getParameter("uiLessValue");//对曲线的修改减少的值
        if (dateScope == null) {
            dateScope = "";
        }
        if (uiAddValue == null) {
            uiAddValue = "";
        }
        if (uiLessValue == null) {
            uiLessValue = "";
        }

        request.setAttribute("dateScope", dateScope);
        request.setAttribute("uiAddValue", uiAddValue);
        request.setAttribute("uiLessValue", uiLessValue);

        String dateJ = null;
        String dateZ = null;

        String[] dateList = new String[7];
        if (area == "" || area == null) {
            area = ReadProperties.readProperties("forecastArea");
        }
        if (areaname == "" || areaname == null) {
            areaname = dbareaService.findAreanameByArea(Integer.parseInt(area));
        }
        //第一次进入这个tab页的时候时间死空的
        if (date == "" || date == null) {
            String str = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
            Calendar ca2 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
            try {
                Date dateTime = format.parse(str);
                ca1.setTime(dateTime);
                ca2.setTime(dateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ca1.add(Calendar.DAY_OF_MONTH, +1);//
            ca2.add(Calendar.DAY_OF_MONTH, -1);//
            dateList = ForecastUtils.strings(format.format(ca1.getTime()).toString());
            date = format.format(ca1.getTime()).toString();
            dateZ = format.format(ca2.getTime()).toString();
            dateJ = str;
        } else {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
            Calendar ca2 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。

            try {
                Date dateTime = format.parse(date);
                ca1.setTime(dateTime);
                ca2.setTime(dateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ca1.add(Calendar.DAY_OF_MONTH, +1);//
            ca2.add(Calendar.DAY_OF_MONTH, -1);//
            dateList = ForecastUtils.strings(format.format(ca1.getTime()).toString());
            dateList = ForecastUtils.strings(date);
            dateJ = date;
            dateZ = format.format(ca2.getTime()).toString();
        }

        request.setAttribute("zhuanHuan1", dateJ);
        request.setAttribute("zhuanHuan2", dateJ + " - " + dateJ);
        request.setAttribute("zhuanHuan3", dateZ);

        request.setAttribute("area", area);
        request.setAttribute("date", date);
        request.setAttribute("dates", dateList);
        request.setAttribute("areaname", areaname);

        return "forecastAnalysis/forecast";
    }

    //这个获得3个默认显示数据的时间
    public String[] selectZJM(String date, String[] dateList) {
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca2 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca3 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca4 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca5 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            Date dateTime = format.parse(date);
            Date dateSDF = format.parse(format.format(new Date()));
            ca1.setTime(dateTime);
            ca2.setTime(dateTime);
            ca3.setTime(dateTime);
            ca4.setTime(dateSDF);
            ca5.setTime(dateSDF);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        ca4.add(Calendar.DAY_OF_MONTH, +0);//
        ca3.add(Calendar.DAY_OF_MONTH, +0);//
        String str4 = format.format(ca4.getTime());
        String str3 = format.format(ca3.getTime());
        str4 = str4.substring(0, 4) + str4.substring(5, 7) + str4.substring(8, 10);
        str3 = str3.substring(0, 4) + str3.substring(5, 7) + str3.substring(8, 10);
        int value = 0;
        if (Integer.parseInt(str4) < Integer.parseInt(str3)) {
            value = 1;
            ca5.add(Calendar.DAY_OF_MONTH, -1);//
        } else {
            ca1.add(Calendar.DAY_OF_MONTH, -1);//
            ca2.add(Calendar.DAY_OF_MONTH, -0);//
        }

        int values = 0;
        if (dateList != null) {
            values = 1;
            List<String> str = new LinkedList<>();
            if (value == 0) {
                str.add(format.format(ca3.getTime()));
                str.add(format.format(ca2.getTime()));
                str.add(format.format(ca1.getTime()));
            } else if (value == 1) {
                str.add(format.format(ca3.getTime()));
                str.add(format.format(ca4.getTime()));
                str.add(format.format(ca5.getTime()));
            }

            List<String> strDateList = new LinkedList<>();
            //把添加的曲线日期也加进去
            for (int i = 0; i < dateList.length; i++) {
                str.add(dateList[i]);
            }
            //排除重复的日期
            for (int i = 0; i < str.size(); i++) {
                if (i == 0) {
                    strDateList.add(str.get(i));
                } else {
                    int number = 0;
                    for (int j = 0; j < strDateList.size(); j++) {
                        if (strDateList.get(j).equals(str.get(i))) {
                            number++;
                        }
                    }
                    if (number == 0) {
                        strDateList.add(str.get(i));
                    }
                }
            }

            dateList = new String[strDateList.size()];
            for (int i = 0; i < strDateList.size(); i++) {
                dateList[i] = strDateList.get(i);
            }
        } else {
            dateList = new String[3];
            if (value == 0) {
                dateList[0] = format.format(ca3.getTime()).toString();
                dateList[1] = format.format(ca2.getTime()).toString();
                dateList[2] = format.format(ca1.getTime()).toString();
            } else if (value == 1) {
                dateList[0] = format.format(ca3.getTime()).toString();
                dateList[1] = format.format(ca4.getTime()).toString();
                dateList[2] = format.format(ca5.getTime()).toString();
            }
        }
        if (value == 0 && values == 1) {
            String[] array = new String[dateList.length + 1];
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    array[0] = dateList[0];
                } else if (i == 1) {
                    array[1] = dateList[0];
                } else {
                    array[i] = dateList[i - 1];
                }
            }
            dateList = array;
        }
        return dateList;
    }

    //先查出来三条曲线进行显示
    @ResponseBody
    @RequestMapping(value = "/select")
    public Map select(@Param("dateList") String[] dateList, HttpServletRequest request) {
        String date = request.getParameter("date");
        String area = request.getParameter("area");
        Map<String, Object> map = new HashMap<>();//这个是返回值
        List<List<DygraphsTable>> listList = new LinkedList<>();//表格的第一行，时间
        List<DygraphsEntity> listListData = new LinkedList<>();//表格的其他行，具体数据
        List<String[]> tableList = new LinkedList<>();//这个是曲线的数据

        //获得所有需要查询的时间
        String[] dates = selectZJM(date, dateList);
        String[] mingTian = new String[97];
        //这个是预测曲线
        LoadForesult loadForesult = new LoadForesult();
        loadForesult.setMdate(stringToString(dates[0]));
        loadForesult.setArea(Integer.parseInt(area));

        LoadForesult loadForesultValue = new LoadForesult();
        loadForesultValue = loadForesultService.select(loadForesult);

        if (loadForesultValue != null) {
            mingTian = ForecastUtils.toStrings(loadForesultValue);
        } else {
            LoadForesult loadForesultMing = new LoadForesult();
            loadForesultMing = null;
            mingTian = ForecastUtils.toStrings(loadForesultMing);
        }
        map.put("mingTian", mingTian);

        String[] date24 = new String[97];
        date24 = String96.jihe96();
        map.put("date24", date24);
        //这个是表格数据的第一行，时间行
        listList.add(DygraphsTableToArray.toArray(date24, "曲线时间名称"));
        //预测曲线的查询时，看所选时间是否小于等于实际的当前日期，如果小于等于那他和dates[1]的值一样
        //表格的其他行数据
        listListData.add(DygraphsTableToArray.toArrayData(mingTian, dates[0] + DygraphsTableUtil.YUCE));

        //根据时间查数据0，1，2这三个位置已经查出来放进了listListData
        tableList.add(mingTian);
        if (dates != null) {
            for (int i = 1; i < dates.length; i++) {
                String[] strings = new String[97];
                LoadHisdata loadHisdataOne = new LoadHisdata();
                String mdate = dates[i].substring(0, 4) + dates[i].substring(5, 7) + dates[i].substring(8, 10);
                loadHisdataOne.setMdate(mdate);
                loadHisdataOne.setArea(Integer.parseInt(area));

                LoadHisdata loadHisdataTwo = new LoadHisdata();
                loadHisdataTwo = loadHisdataService.selectByAreaAndMdate(loadHisdataOne);
                if (loadHisdataTwo != null) {
                    strings = String96.toStrings(loadHisdataTwo);
                } else {
                    LoadHisdata loadHisdataZuo = new LoadHisdata();
                    loadHisdataZuo = null;
                    strings = String96.toStrings(loadHisdataTwo);
                }
                tableList.add(strings);
                listListData.add(DygraphsTableToArray.toArrayData(strings, dates[i] + DygraphsTableUtil.SHIJI));
            }
        }
        map.put("listList", listList);//表格的第一行，时间
        map.put("listListData", listListData);//表格的其他行，具体数据
        map.put("tableList", tableList);
        //这个判断是用来得知显示的画板的最大值的
        if (maxJJ(tableList) != 0.0) {
            map.put("maxNumber", maxJJ(tableList));//曲线的最大值的1.2倍
        } else {
            map.put("maxNumber", DygraphsTableUtil.MAX);//曲线的最大值的1.2倍
        }
        map.put("dates", dates);
        return map;
    }

    public String stringToString(String string) {
        string = string.substring(0, 4) + string.substring(5, 7) + string.substring(8, 10);
        return string;
    }


    //曲线的最大值的1.2倍方法
    public Double maxJJ(List<String[]> list) {
        Double number = 0.0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                String[] str = list.get(i);
                if (number < Double.parseDouble(str[j])) {
                    number = Double.parseDouble(str[j]);
                }
            }
        }
        number = number * DygraphsTableUtil.MAXNUMBER;
        return number;
    }

    /*
        弹出框的修改方法
     */
    @ResponseBody
    @RequestMapping(value = "/updatePredict")
    public Map updatePredict(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String num = request.getParameter("num");//2代表了使用预测日期来修改，4代表了使用实际日期来修改
        String area = request.getParameter("area"); //地区id
        String zhuanHuan1 = request.getParameter("zhuanHuan1");//预测日期
        String zhuanHuan2 = request.getParameter("zhuanHuan2");//需要修改的日期范围
        String zhuanHuan3 = request.getParameter("zhuanHuan3");//实际日期
        String zhuanHuan4 = request.getParameter("zhuanHuan4");//需要修改的日期范围
        int number = 0;
        if (Integer.parseInt(num) == 2) {
            String[] str = strToArray(zhuanHuan2);
            LoadForesult loadForesult = new LoadForesult();
            zhuanHuan1 = zhuanHuan1.substring(0, 4) + zhuanHuan1.substring(5, 7) + zhuanHuan1.substring(8, 10);
            loadForesult.setMdate(zhuanHuan1);
            loadForesult.setArea(Integer.parseInt(area));
            LoadForesult foresult = new LoadForesult();
            foresult = loadForesultService.select(loadForesult);
            //返回的值，0修改失败，1是修改成功，2是没有找到
            if (foresult != null) {
                foresult.setArea(Integer.parseInt(area));
                if (Integer.parseInt(str[0]) == Integer.parseInt(str[1])) {
                    foresult.setMdate(str[0]);
                    int selectInt = loadForesultService.selectInt(foresult);
                    if (selectInt == 0) {
                        foresult.setId(new Long(1));
                        number = loadForesultService.insert(foresult);
                    } else {
                        number = loadForesultService.update(foresult);
                    }
                } else {
                    String dateString = null;
                    for (int i = 0; i < 1000; i++) {
                        if (i == 0) {
                            dateString = str[0];
                        } else {
                            dateString = dateTodate(dateString);
                        }
                        foresult.setMdate(dateString);
                        int selectInt = loadForesultService.selectInt(foresult);
                        if (Integer.parseInt(dateString) <= Integer.parseInt(str[1])) {
                            if (selectInt == 0) {
                                foresult.setId(new Long(1));
                                number = loadForesultService.insert(foresult);
                            } else {
                                number = loadForesultService.update(foresult);
                            }
                        } else if (Integer.parseInt(dateString) > Integer.parseInt(str[1])) {
                            break;
                        }
                    }
                }
            } else {
                number = 2;
            }
        } else {
            String[] str = strToArray(zhuanHuan4);
            zhuanHuan3 = zhuanHuan3.substring(0, 4) + zhuanHuan3.substring(5, 7) + zhuanHuan3.substring(8, 10);
            LoadHisdata loadHisdata = new LoadHisdata();
            loadHisdata.setMdate(zhuanHuan3);
            loadHisdata.setArea(Integer.parseInt(area));
            LoadHisdata hisdata = new LoadHisdata();
            hisdata = loadHisdataService.selectByAreaAndMdate(loadHisdata);
            //返回的值，0修改失败，1是修改成功，2是没有找到
            if (hisdata != null) {
                String[] str96 = String96.toStrings(hisdata);
                LoadForesult foresult = new LoadForesult();
                foresult = String96.toLoadForesult(str96);
                foresult.setArea(Integer.parseInt(area));
                if (Integer.parseInt(str[0]) == Integer.parseInt(str[1])) {
                    foresult.setMdate(str[0]);
                    int selectInt = loadForesultService.selectInt(foresult);
                    if (selectInt == 0) {
                        foresult.setId(new Long(1));
                        number = loadForesultService.insert(foresult);
                    } else {
                        number = loadForesultService.update(foresult);
                    }
                } else {
                    String dateString = null;
                    for (int i = 0; i < 1000; i++) {
                        if (i == 0) {
                            dateString = str[0];
                        } else {
                            dateString = dateTodate(dateString);
                        }
                        foresult.setMdate(dateString);
                        int selectInt = loadForesultService.selectInt(foresult);
                        if (Integer.parseInt(dateString) <= Integer.parseInt(str[1])) {
                            if (selectInt == 0) {
                                foresult.setId(new Long(1));
                                number = loadForesultService.insert(foresult);
                            } else {
                                number = loadForesultService.update(foresult);
                            }
                        } else if (Integer.parseInt(dateString) > Integer.parseInt(str[1])) {
                            break;
                        }
                    }
                }
            } else {
                number = 2;
            }
        }
        map.put("value", number);
        return map;
    }


    public String dateTodate(String str) {
        SimpleDateFormat sDF = new java.text.SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            Date dateSDF = sDF.parse(str);
            calendar.setTime(dateSDF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        return sDF.format(calendar.getTime()).toString();
    }


    public String[] strToArray(String str) {
        String[] string = new String[2];
        string[0] = str.substring(0, 10);
        string[0] = string[0].substring(0, 4) + string[0].substring(5, 7) + string[0].substring(8, 10);
        string[1] = str.substring(13, 23);
        string[1] = string[1].substring(0, 4) + string[1].substring(5, 7) + string[1].substring(8, 10);
        return string;
    }


    //对明日曲线进行修改
    @ResponseBody
    @RequestMapping(value = "/update")
    public Map update(@Param("numberList") String[] numberList, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int number = 0;
        String[] list = numberList;
        String date = request.getParameter("date");//这个是时间
        String area = request.getParameter("area");//地区id
        String dateScope = request.getParameter("dateScope");//这个是用来确定修改曲线的时间范围
        String valueAdd = request.getParameter("valueAdd");//代表修改的增加值
        String valueLess = request.getParameter("valueLess");//代表修改的减少值

        if (list == null) {
            map.put("value", "2");
        } else {
            if ((dateScope != "" && valueAdd != "") || (dateScope != "" && valueLess != "")) {
                String[] str = String96.jihe96();
                str[96] = "23:59";//这个集合的下标96的值是"00:00",但是前台的时间选择框最大值是"23:59",所以这个地方改一下
                List<LoadForesult> loadForesults = new LinkedList<>();
                //创建一个LoadForesult的集合，长度为97，时间放在mdate中，值都放在f00中
                for (int i = 0; i < str.length; i++) {
                    LoadForesult loadForesult = new LoadForesult();
                    loadForesult.setMdate(str[i]);
                    loadForesult.setF00(new BigDecimal(list[i]));
                    loadForesults.add(loadForesult);
                }
                String dateStart = dateScope.substring(0, 5);
                String dateEnd = dateScope.substring(8, 13);
                String[] strStart = dateStart.split(":");
                String[] strEnd = dateEnd.split(":");
                int dateStartNumber = 0;
                int dateEndNumber = 0;
                //这个是对两个时间值，把小的值赋予dateStartNumber，大的赋予dateEndNumber
                if (Integer.parseInt(strStart[0] + strStart[1]) < Integer.parseInt(strEnd[0] + strEnd[1])) {
                    dateStartNumber = Integer.parseInt(strStart[0] + strStart[1]);
                    dateEndNumber = Integer.parseInt(strEnd[0] + strEnd[1]);
                } else {
                    dateStartNumber = Integer.parseInt(strStart[0] + strStart[1]);
                    dateEndNumber = Integer.parseInt(strEnd[0] + strEnd[1]);
                }
                for (int i = 0; i < loadForesults.size(); i++) {
                    //把时间转换成数值，用数值进行对比
                    int shu = 0;
                    String[] strList = loadForesults.get(i).getMdate().split(":");
                    shu = Integer.parseInt(strList[0] + strList[1]);
                    Double dou = new Double(0.0);
                    //判断这个时间值是否在所选的时间内
                    if (dateStartNumber <= shu && shu <= dateEndNumber) {
                        //增加值不为空，在原来的基础上在加上
                        if (valueAdd != "") {
                            dou = Double.parseDouble(loadForesults.get(i).getF00().toString()) + Double.parseDouble(valueAdd);
                        }
                        //增加值不为空，在原来的基础上在减去
                        if (valueLess != "") {
                            dou = Double.parseDouble(loadForesults.get(i).getF00().toString()) - Double.parseDouble(valueLess);
                        }
                    } else {
                        dou = Double.parseDouble(loadForesults.get(i).getF00().toString());
                    }
                    list[i] = dou.toString();
                }
            }
            date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);

            SimpleDateFormat sDF = new java.text.SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
            try {
                Date dateD=new Date();
                Date dateSDF = sDF.parse(sDF.format(dateD));
                calendar.setTime(dateSDF);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_MONTH, 0);//

            if (Integer.parseInt(date) <= Integer.parseInt(sDF.format(calendar.getTime()).toString())) {
                try {
                    Date dateSDF = sDF.parse(date);
                    calendar.setTime(dateSDF);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.add(Calendar.DAY_OF_MONTH, -1);//
                date = sDF.format(calendar.getTime()).toString();
            }
            number = loadForesultService.update(list, area, date);
            //修改是否成功
            if (number >0) {
                map.put("value", "1");//代表修改成功
            } else {
                map.put("value", "0");//修改失败
            }
        }
        return map;
    }
}
