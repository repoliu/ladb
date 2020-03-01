package com.dky.controller.influencingFactorAnalysis;

import com.dky.entity.influ.vo.WeatherResult;
import com.dky.entity.vo.ChartsOption;
import com.dky.service.common.DbareaService;
import com.dky.service.influencingFactorAnalysis.AirConditionerLoadService;
import com.dky.util.DateUtils;
import com.dky.util.PythonOperation;
import com.dky.util.ReadProperties;
import com.dky.util.TimePoint;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lq
 * @Description: 空调负荷分析
 * @Date: Create in 15:38 2018/9/25
 */
@Controller
@RequestMapping("/influence/airConditionerLoad")
public class AirConditionerLoadController {
    private final AirConditionerLoadService airConditionerLoadService;
    private final DbareaService dbareaService;
    @Autowired
    public AirConditionerLoadController(AirConditionerLoadService airConditionerLoadService, DbareaService dbareaService){
        this.airConditionerLoadService = airConditionerLoadService;
        this.dbareaService = dbareaService;
    }

    Logger logger = Logger.getLogger(AirConditionerLoadController.class.getName());
    /**
     * 返回空调负荷分析分析页面以及图表得数据
     * @param area 地区id
     * @param year 年
     * @param model 模型
     * @return 返回页面
     * @throws ParseException
     */
    @RequestMapping("load")
    public String load(@Param("area") Integer area,@Param("year") String year, Model model) throws ParseException {
        WeatherResult result = new WeatherResult();

        if ( area == null){
            area = Integer.parseInt(ReadProperties.readProperties("influWeatherArea"));
        }
        result.setArea(area);

        // 此处对年进行赋值及分析
        if (year == null || year.equals("")){
            year = airConditionerLoadService.findLastMdateByArea(area);
            if (year == null || year.equals("")){
                year = DateUtils.format(new Date(),DateUtils.Y_P);
                result.setYear(year);
                result.setAreaname(dbareaService.findAreanameByArea(area));
                result.setYearButton(TimePoint.createTimeButtonValueForYear(year));
                model.addAttribute("isEmpty",true);
                model.addAttribute("weather",result);
                return "influencingFactorAnalysis/airConditionerLoad";
            } else {
                year = year.substring(0,4);
            }
        }
        result.setYear(year);

        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("area",area.toString());
        params.put("year",years(year));
        airConditionerLoadService.setResultByParams(result,params);
        model.addAttribute("weather",result);
        if (result.getRainRelations() == null && result.getCosnRelations() == null){
            model.addAttribute("isEmpty",true);
        }
        return "influencingFactorAnalysis/airConditionerLoad";
    }
    @RequestMapping("/selectPieChartData")
    @ResponseBody
    public List<ChartsOption> selectPieChartData(@Param("area") Integer area,@Param("year") String year) throws ParseException {
        WeatherResult result = new WeatherResult();
        result.setArea(area);
        result.setMdate(year);
        result.setYear(year);
        result.setYearButton(TimePoint.createTimeButtonValueForYear(year));
        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("area",area.toString());
        params.put("year",year);
        return airConditionerLoadService.setMaxProportion(result,params);
    }
    @RequestMapping("/selectMaxEnergyData")
    @ResponseBody
    public List<ChartsOption> selectMaxEnergyData(@Param("area") Integer area,@Param("year") String year){
        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("area",area.toString());
        params.put("year",years(year));
        return airConditionerLoadService.setMaxEnergy(params);
    }
    @RequestMapping("/selectLoadByLoadDaytrait")
    @ResponseBody
    public String selectMinLoadByLoadDaytrait(@Param("area") Integer area, @Param("year") String year,int choose){
        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("area",area.toString());
        params.put("year",years(year));
        return airConditionerLoadService.selectLoadByLoadDaytrait(params,choose);
    }
    @RequestMapping("/selectMaxLoad")
    @ResponseBody
    public String selectMaxLoad(@Param("area") Integer area, @Param("year") String year){
        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("area",area.toString());
        params.put("year",years(year));
        return airConditionerLoadService.selectMaxLoad(params);
    }
    /**
     * 调用空调负荷分析按钮，进行python分析
     * @param area 地区id
     * @param mdate 日期
     * @return 返回分析成功或失败
     */
    @RequestMapping(value = "/airConditionerAnalysis", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean clusterAnalysis(int area, String mdate)  {
        String path = AirConditionerLoadController.class.getResource("/").getPath().substring(1);
        boolean result = false;
        try {
//            areaname = new String(areaname.toString().getBytes("UTF-8"));
            String areaname = dbareaService.findAreanameByArea(area);
            String[] args11 = new String[]{"python", path + "pythonScript/airConditionCal.py", areaname, mdate};
            result = PythonOperation.pythonExcute(args11);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String years(String year) {
        StringBuffer str = new StringBuffer();
        for (int i = 1; i < 4; i++) {
            str.append("'"+String.valueOf(Integer.parseInt(year) + i)+"'"+",");
            str.append("'"+String.valueOf(Integer.parseInt(year) - i)+"'"+",");
        }
        return str+"'"+year+"'";
    }
}
