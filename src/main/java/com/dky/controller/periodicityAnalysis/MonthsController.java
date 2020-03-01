package com.dky.controller.periodicityAnalysis;

import com.dky.entity.Dbarea;
import com.dky.entity.LoadHisdata;
import com.dky.entity.influ.Autovalue;
import com.dky.entity.influ.Qstation;
import com.dky.mapper.influ.AutovalueMapper;
import com.dky.mapper.influ.QstationMapper;
import com.dky.service.common.DbareaService;
import com.dky.service.periodicityAnalysis.LoadHisdataService;
import com.dky.util.ReadProperties;
import com.dky.util.String96;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("months/day")
public class MonthsController {

    @Autowired
    private LoadHisdataService loadHisdataService;
    @Autowired
    private DbareaService dbareaService;

    @Autowired
    private QstationMapper qstationMapper;

    @Autowired
    private AutovalueMapper autovalueMapper;


    Logger logger = Logger.getLogger(MonthsController.class.getName());


    @RequestMapping(value = "/load")
    public String load(HttpServletRequest request) {
/*
        List<Dbarea> dbareaList = new ArrayList<>();
        dbareaList = dbareaService.findAllArea();

        String[] dateDate = {"2018-07-03", "2018-07-04", "2018-07-05", "2018-07-06", "2018-07-07", "2018-07-08", "2018-07-09", "2018-07-10", "2018-07-11", "2018-07-12", "2018-07-13", "2018-07-14", "2018-07-15", "2018-07-16", "2018-07-17", "2018-07-18", "2018-07-19", "2018-07-20", "2018-07-21", "2018-07-22", "2018-07-23", "2018-07-24", "2018-07-25", "2018-07-26", "2018-07-27", "2018-07-28", "2018-07-29", "2018-07-30", "2018-07-31",
                "2018-08-01", "2018-08-02", "2018-08-03", "2018-08-04", "2018-08-05", "2018-08-06", "2018-08-07", "2018-08-08", "2018-08-09", "2018-08-10", "2018-08-11", "2018-08-12", "2018-08-13", "2018-08-14", "2018-08-15", "2018-08-16", "2018-08-17", "2018-08-18", "2018-08-19", "2018-08-20", "2018-08-21", "2018-08-22", "2018-08-23", "2018-08-24", "2018-08-25", "2018-08-26", "2018-08-27", "2018-08-28", "2018-08-29", "2018-08-30", "2018-08-31",
                "2018-09-01", "2018-09-02", "2018-09-03", "2018-09-04", "2018-09-05", "2018-09-06", "2018-09-07", "2018-09-08", "2018-09-09", "2018-09-10", "2018-09-11", "2018-09-12", "2018-09-13", "2018-09-14", "2018-09-15", "2018-09-16", "2018-09-17", "2018-09-18", "2018-09-19", "2018-09-20", "2018-09-21", "2018-09-22", "2018-09-23", "2018-09-24", "2018-09-25", "2018-09-26", "2018-09-27", "2018-09-28", "2018-09-29", "2018-09-30",
                "2018-10-01", "2018-10-02", "2018-10-03", "2018-10-04", "2018-10-05", "2018-10-06", "2018-10-07", "2018-10-08", "2018-10-09", "2018-10-10", "2018-10-11", "2018-10-12", "2018-10-13", "2018-10-14", "2018-10-15", "2018-10-16", "2018-10-17", "2018-10-18", "2018-10-19", "2018-10-20", "2018-10-21", "2018-10-22", "2018-10-23", "2018-10-24", "2018-10-25", "2018-10-26", "2018-10-27", "2018-10-28", "2018-10-29", "2018-10-30", "2018-10-31",
                "2018-11-01", "2018-11-02", "2018-11-03", "2018-11-04", "2018-11-05", "2018-11-06", "2018-11-07", "2018-11-08", "2018-11-09", "2018-11-10", "2018-11-11", "2018-11-12", "2018-11-13", "2018-11-14", "2018-11-15", "2018-11-16", "2018-11-17", "2018-11-18", "2018-11-19", "2018-11-20", "2018-11-21", "2018-11-22", "2018-11-23", "2018-11-24", "2018-11-25", "2018-11-26", "2018-11-27", "2018-11-28", "2018-11-29", "2018-11-30",
                "2018-12-01", "2018-12-02", "2018-12-03", "2018-12-04", "2018-12-05", "2018-12-06", "2018-12-07", "2018-12-08", "2018-12-09", "2018-12-10", "2018-12-11", "2018-12-12", "2018-12-13", "2018-12-14", "2018-12-15", "2018-12-16", "2018-12-17", "2018-12-18", "2018-12-19", "2018-12-20", "2018-12-21", "2018-12-22", "2018-12-23", "2018-12-24", "2018-12-25", "2018-12-26", "2018-12-27", "2018-12-28", "2018-12-29", "2018-12-30", "2018-12-31"};


        List<Autovalue> autovalueList = new ArrayList<>();
        autovalueList = autovalueMapper.selerain();
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int  numberaa=0;
        for (int t = 0; t < dbareaList.size(); t++) {
            String stcd = null;
            stcd = qstationMapper.findStcdByAreaname(dbareaList.get(t).getDname());
            if (stcd!=null){

                for (int i = 0; i < dateDate.length; i++) {
                    Autovalue  autovalue=autovalueList.get(numberaa);
                    autovalue.setStcd(stcd);
                    try {
                        autovalue.setTime(format.parse(dateDate[i] +" 10:00:00"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    autovalueMapper.insert(autovalue);

                    numberaa++;
                    if (numberaa==6){
                        numberaa=0;
                    }
                }
            }


        }

*/

        String date = null;
        LoadHisdata loadHisdata = new LoadHisdata();
        List<LoadHisdata> list = new ArrayList<LoadHisdata>();
        date = request.getParameter("queRenShiJain");
        String area = request.getParameter("area");
        if (area == null || area == "") {
            //第一次进来的时候默认查询国网，1代表国网
            loadHisdata.setArea(Integer.parseInt(ReadProperties.readProperties("periodicityArea")));
        } else {
            loadHisdata.setArea(Integer.parseInt(area));
        }
        String areaname = dbareaService.findAreanameByArea(loadHisdata.getArea());
        //最小时间用来对时间选择框禁用的
        String dateMin = loadHisdataService.selectOne(loadHisdata);
        //最大时间用来对时间选择框禁用的
        String dateMax = loadHisdataService.selectTwo(loadHisdata);
        if (date == null && dateMax != null) {
            date = dateMax.substring(0, 4) + "-" + dateMax.substring(4, 6);
        } else if (date == null && dateMax == null) {
            Date dates = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            date = sdf1.format(dates) + "";
        }
        if (dateMin != null) {
            request.setAttribute("xianZhiMin", String96.StringToString(dateMin));//是2017-10-01格式
        } else {
            request.setAttribute("xianZhiMin", String96.StringToString("1900-11-11"));//是2017-10-01格式
        }
        if (dateMax != null) {
            request.setAttribute("xianZhiMax", String96.StringToStringMax(dateMax));//是2017-10-01格式
        } else {
            request.setAttribute("xianZhiMax", String96.StringToString("9999-11-11"));//是2017-10-01格式
        }
        Map<String, String> map5 = String96.StringToMap(dateTime(date));
        request.setAttribute("one", map5.get("one").substring(0, 4) + "-" + map5.get("one").substring(4, 6));
        request.setAttribute("two", map5.get("two").substring(0, 4) + "-" + map5.get("two").substring(4, 6));
        request.setAttribute("three", map5.get("three").substring(0, 4) + "-" + map5.get("three").substring(4, 6));
        request.setAttribute("four", map5.get("four").substring(0, 4) + "-" + map5.get("four").substring(4, 6));
        request.setAttribute("five", map5.get("five").substring(0, 4) + "-" + map5.get("five").substring(4, 6));


        request.setAttribute("shiJian", date);
        request.setAttribute("areaname", areaname);
        return "periodicityAnalysis/months";
    }

    public String dateTime(String string) {
        String str = null;
        str = string.substring(0, 4) + string.substring(5, 7);
        return str;
    }
}
