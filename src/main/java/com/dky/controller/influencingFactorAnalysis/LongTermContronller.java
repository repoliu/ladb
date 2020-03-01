package com.dky.controller.influencingFactorAnalysis;

import com.dky.entity.*;
import com.dky.entity.vo.ChartsOption;
import com.dky.service.influencingFactorAnalysis.*;
import com.dky.util.ReadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/longTermContonller")
public class LongTermContronller {


    @Autowired
    private IndustryStructureService industryStructureService;

    @Autowired
    private RelationSupportService relationSupportService;

    @Autowired
    private SocietyCapacityService societyCapacityService;

    @Autowired
    private UserTypeAreaService userTypeAreaService;

    @Autowired
    private OutPutValueService outPutValueService;

    @Autowired
    private PerIncomeService perIncomeService;

    @Autowired
    private PerGdpService perGdpService;

    @Autowired
    private PerYdlService perYdlService;


    @RequestMapping("/load")
    public String loa(HttpServletRequest request) {
        String date = request.getParameter("queRenShiJain");
        String areaname = request.getParameter("areaname");
        String nianXian = request.getParameter("nianXian");
        String anNiu = request.getParameter("anNiu");//这个用来判断是否是点击1，3，5，10这4个时间按钮传过来的值
        //用年限来判断是不是第一次进入这里，如果不等于null说明不是第一次进入，把areaname返回去，如果等于null说明第一次进入，给他地区赋初始值
        if (nianXian != null) {
            request.setAttribute("areaname", areaname);
        } else {
            request.setAttribute("areaname", ReadProperties.readProperties("influenceLongTerm"));
        }
        if (anNiu == null && date == null) {  //第一次加载的时候是空的
            Date dates = new Date();//获取当前日期也可以用Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            date = sdf.format(dates);
            anNiu = "0";
        } else if ((anNiu != null && date == null) || (anNiu != null && date == "")) {
            //代表了是点击1，3，5，10这4个时间按钮传过来的值
            if (anNiu.equals("1")) {
                Date dates = new Date();//获取当前日期也可以用Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                date = sdf.format(dates);
                anNiu = "1";
            } else {
                Date dates = new Date();//获取当前日期也可以用Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                date = sdf.format(dates);
                anNiu = "0";
            }
        }
        if (nianXian != null) {
            if (anNiu.equals("1")) {
                request.setAttribute("nianXian", nianXian);
            } else {
                request.setAttribute("nianXian", nianXian);
            }
        } else {
            request.setAttribute("nianXian", "5");
        }
        request.setAttribute("shiJian", date);
        request.setAttribute("anNiu", anNiu);

        return "influencingFactorAnalysis/longTerm";
    }

    /**
     * 全社会用电总量的折线图，
     * 这个方法是后加的，如果查的是多年的X轴显示的是，多个时间年，
     * 如果是只查一年X轴显示的是这一年的12个月，和其他的折线图有区别，所以分开做
     */
    @RequestMapping("/selectYongDianLiang")
    @ResponseBody
    public List<ChartsOption> selectYongDianLiang(HttpServletRequest request) {
        String date = request.getParameter("queRenShiJain");
        int number = 0;
        number = date.length();
        if (number > 6) {
            date = date.substring(0, 4) + "-" + date.substring(number - 4, number);
        }
        String areaname = request.getParameter("areaname");
        String nianXian = request.getParameter("nianXian");
        String anNiu = request.getParameter("anNiu");//这个用来判断是否是点击1，3，5，10这4个时间按钮传过来的值
        //获取社会用电总量
        UserTypeArea userTypeArea = new UserTypeArea();
        userTypeArea = userTypeAreaService.selectQuery(areaname);
        List<ChartsOption> societyCapacityList = new LinkedList<ChartsOption>();//返回值的集合
        if (userTypeArea != null) {
            societyCapacityList = societyCapacityService.selectQuery(date, areaname, userTypeArea.getNum() + "", nianXian, anNiu);
        }
        return societyCapacityList;
    }

    /**
     * 这个是查询折线图的
     *
     * @param request
     * @return
     */
    @RequestMapping("/selectQuery")
    @ResponseBody
    public List<List<ChartsOption>> selectQuery(HttpServletRequest request) {
        List<List<ChartsOption>> li = new LinkedList<List<ChartsOption>>();
        String date = request.getParameter("queRenShiJain");
        String anNiu = request.getParameter("anNiu");//这个用来判断是否是点击1，3，5，10这4个时间按钮传过来的值
        int number = 0;
        number = date.length();
        if (number > 6) {
            date = date.substring(0, 4) + "-" + date.substring(number - 4, number);
        }
        String areaname = request.getParameter("areaname");
        String nianXian = request.getParameter("nianXian");


        //获取生产总值
        List<ChartsOption> outPutValueList = new LinkedList<ChartsOption>();//返回值的集合
        outPutValueList = outPutValueService.selectQuery(areaname, date, nianXian);

        //城镇居民人均收入
        List<ChartsOption> perIncomeListList = new LinkedList<ChartsOption>();//返回值的集合
        perIncomeListList = perIncomeService.selectQuery(areaname, date, nianXian);


        List<ChartsOption> societyCapacityList = new LinkedList<ChartsOption>();//返回值的集合
        List<ChartsOption> perYdlList = new LinkedList<>();
        //这个是 用来区分查的单年的还是多年的，1年的从society_capacity表拿，查多年数据从per_ydl表查
        if (Integer.parseInt(nianXian) == 1) {
            //获取社会用电总量
            UserTypeArea userTypeArea = new UserTypeArea();
            userTypeArea = userTypeAreaService.selectQuery(areaname);
            if (userTypeArea != null) {
                societyCapacityList = societyCapacityService.selectQuery(date, areaname, userTypeArea.getNum() + "", nianXian, anNiu);
            }
        } else {
            perYdlList = perYdlService.selectPerYdl(areaname, date, nianXian);
        }

        li.add(outPutValueList);
        li.add(perIncomeListList);
        if (Integer.parseInt(nianXian) == 1) {
            li.add(societyCapacityList);
        } else {
            li.add(perYdlList);
        }
        return li;
    }

    public Map StringToMinMaxDate(String areaname) {
        List<String> list = new ArrayList<String>();
        UserTypeArea userTypeArea = new UserTypeArea();
        userTypeArea = userTypeAreaService.selectQuery(areaname);
        Map societyCapacityMap = null;
        if (userTypeArea != null) {
            societyCapacityMap = societyCapacityService.selectMaxDate(areaname, userTypeArea.getNum() + "");
        }
        String socMax = null;
        String socMin = null;
        if (societyCapacityMap != null) {
            socMax = societyCapacityMap.get("maxdate").toString();
            socMin = societyCapacityMap.get("mindate").toString();
        }
        Map outPutValueMap = outPutValueService.selectMaxDate(areaname);
        String outMax = null;
        String outMin = null;
        if (outPutValueMap != null) {
            outMax = outPutValueMap.get("max(maxdate)").toString();
            outMin = outPutValueMap.get("min(mindate)").toString();
        }
        Map<String, String> map = new HashMap<>();
        if (outPutValueMap != null && societyCapacityMap != null) {
            if (Integer.parseInt(socMax) > Integer.parseInt(outMax)) {
                map.put("dateMax", socMax);
            } else {
                map.put("dateMax", outMax);
            }
            if (Integer.parseInt(socMin) > Integer.parseInt(outMin)) {
                map.put("dateMin", socMin);
            } else {
                map.put("dateMin", outMin);
            }
        } else if (outPutValueMap == null && societyCapacityMap != null) {
            map.put("dateMax", socMax);
            map.put("dateMin", socMin);
        } else if (outPutValueMap != null && societyCapacityMap == null) {
            map.put("dateMax", outMax);
            map.put("dateMin", outMin);
        }
        return map;
    }


    /**
     * 这个是查询柱状图的
     *
     * @param request
     * @return
     */
    @RequestMapping("/selectColumnar")
    @ResponseBody
    public List<List<ChartsOption>> selectColumnar(HttpServletRequest request) {
        List<List<ChartsOption>> li = new LinkedList<List<ChartsOption>>();
        String date = request.getParameter("queRenShiJain");
        int number = 0;
        number = date.length();
        if (number > 6) {
            date = date.substring(0, 4) + "-" + date.substring(number - 4, number);
        }
        String areaname = request.getParameter("areaname");
        String nianXian = request.getParameter("nianXian");

        //各行业用电量
        List<ChartsOption> perYdl = new LinkedList<ChartsOption>();//返回值的集合
        perYdl = perYdlService.selectQuery(areaname, date, nianXian);

        //人均GDP
        List<ChartsOption> perGdpList = new LinkedList<ChartsOption>();//返回值的集合
        perGdpList = perGdpService.selectQuery(areaname, date, nianXian);

        //三产产值占比
        List<ChartsOption> industryStuctrueListList = new LinkedList<ChartsOption>();//返回值的集合
        industryStuctrueListList = industryStructureService.selectQuery(areaname, date, nianXian);

        li.add(perYdl);
        li.add(perGdpList);
        li.add(industryStuctrueListList);

        return li;
    }

    @RequestMapping("/selectRelationSupport")
    @ResponseBody
    public ChartsOption selectRelationSupport(HttpServletRequest request) {
        String date = request.getParameter("queRenShiJain");
        int number = 0;
        number = date.length();
        if (number > 6) {
            date = date.substring(0, 4) + "-" + date.substring(number - 4, number);
        }
        String areaname = request.getParameter("areaname");
        String nianXian = request.getParameter("nianXian");

        ChartsOption chartsOption = new ChartsOption();
        chartsOption = relationSupportService.selectQuery(areaname, date, nianXian);
        return chartsOption;
    }

}
