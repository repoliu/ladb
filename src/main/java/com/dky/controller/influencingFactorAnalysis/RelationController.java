package com.dky.controller.influencingFactorAnalysis;

import com.dky.entity.influ.vo.WeatherResult;
import com.dky.service.common.DbareaService;
import com.dky.service.influencingFactorAnalysis.RelationService;
import com.dky.service.influencingFactorAnalysis.impl.RelationServiceImpl;
import com.dky.util.DateUtils;
import com.dky.util.PythonOperation;
import com.dky.util.ReadProperties;
import com.dky.util.TimePoint;
import com.dky.util.daoOperate.DeleteFunction;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description: 全年气象因素分析
 * @Date: Create in 15:38 2018/1/15
 */
@Controller
@RequestMapping("/influence/relation")
public class RelationController {
    private final RelationService relationService;
    private final DbareaService dbareaService;
    @Autowired
    public RelationController(RelationService relationService,
                              DbareaService dbareaService){
        this.relationService = relationService;
        this.dbareaService = dbareaService;
    }

    Logger logger = Logger.getLogger(RelationController.class.getName());
    /**
     * 返回全年分析页面以及图表得数据
     * @param area 地区id
     * @param year 年
     * @param model 模型
     * @param wenDu 温度标签
     * @param jiangYu 降雨量标签
     * @return 返回页面
     * @throws ParseException
     */
    @RequestMapping("load")
    public String load(@Param("area") Integer area,@Param("year") String year, Model model,@Param("wenDu") String  wenDu,@Param("jiangYu") String  jiangYu) throws ParseException {
        WeatherResult result = new WeatherResult();

        if ( area == null){
            area = Integer.parseInt(ReadProperties.readProperties("influWeatherArea"));

        }
        result.setArea(area);

        // 此处对年进行赋值及分析
        if (year == null || year.equals("")){

            year = relationService.findLastMdateByArea(area);

            if (year == null || year.equals("")){
                year = DateUtils.format(new Date(),DateUtils.Y_P);
                result.setYear(year);
                result.setAreaname(dbareaService.findAreanameByArea(area));

                result.setYearButton(TimePoint.createTimeButtonValueForYear(year));
                model.addAttribute("isEmpty",true);
                model.addAttribute("weather",result);
                return "influencingFactorAnalysis/relationAllYear";
            } else {
                year = year.substring(0,4);
            }
        }
        result.setYear(year);

        //设置查询参数，进行查询
        Map<String,String> params = new HashMap<>();
        params.put("startYear",year+"01");
        params.put("endYear",year+"12");
        params.put("area",area.toString());
        relationService.setResultByParams(result,params,wenDu,jiangYu);


        model.addAttribute("weather",result);
        model.addAttribute("wenDu",wenDu);//XLY加的，这个是用来判断单选按钮的
        model.addAttribute("jiangYu",jiangYu);//XLY加的，这个是用来判断单选按钮的
        model.addAttribute("area",area);//XLY加的，这个是用来判断单选按钮的
        if (result.getRainRelations() == null && result.getTempRelations() == null && result.getCosnRelations() == null && result.getHumdRelations() == null){
            model.addAttribute("isEmpty",true);
        }



        return "influencingFactorAnalysis/relationAllYear";
    }

    /**
     * 调用全年分析页面得支持度分析按钮，进行python分析
     * @param areaname 地区名
     * @param mdate 日期
     * @return 返回分析成功或失败
     */
    @RequestMapping(value = "/clusterAnalysis", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean clusterAnalysis(String areaname, String mdate)  {
        String path = WeatherAnalysisController.class.getResource("/").getPath().substring(1);
        boolean result = false;
        try {
            //areaname = new String(areaname.toString().getBytes("UTF-8"));
            String[] args11 = new String[]{"python", path + "pythonScript/weatherMonthCf.py", areaname, mdate};
            result = PythonOperation.pythonExcute(args11);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
