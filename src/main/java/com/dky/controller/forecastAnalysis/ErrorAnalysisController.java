package com.dky.controller.forecastAnalysis;

import com.dky.entity.LoadDayerrvalue;
import com.dky.entity.LoadShortdayerrAll;
import com.dky.entity.forecast.DygraphsEntity;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.service.forecastAnalysis.ErrorAnalysisService;
import com.dky.util.String96;
import com.dky.util.forecast.DygraphsTableToArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("errorAnalysis")
public class ErrorAnalysisController {


    @Autowired
    private ErrorAnalysisService errorAnalysisService;

    //这个是加载的时候的方法
    @RequestMapping(value = "/load")
    public String load(@Param("area") String area,@Param("date") String date, HttpServletRequest request) {
         String dateChoose="";
        if (date==null){
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            date=format.format(new Date());
            dateChoose=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
        }else {
            if (date.length()>8){
                date=date.substring(0,4)+""+date.substring(5,7)+""+date.substring(8,10);
            }
            dateChoose=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
        }
        if (area==""||area==null){
            area="53";
        }
        request.setAttribute("area", area);
        request.setAttribute("date", date);
        request.setAttribute("dateChoose", dateChoose);
        return "forecastAnalysis/errorAnalysis";
    }

    /**
     * 这个获得的是实际曲线和预测曲线
     * @param area
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectContrastCurve")
    public Map selectContrastCurve(@Param("area") String area,  @Param("date") String date) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapList = new HashMap<>();
        if (date == null) {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            date = format.format(new Date());
        }else {
            if (date.length()>8){
                date=date.substring(0,4)+""+date.substring(5,7)+""+date.substring(8,10);
            }
        }
        mapList= errorAnalysisService.selectContrastCurve(area, date);
        if (mapList!=null&&mapList.size()>0){
            map.put("listList", mapList);
        }else {
            map=null;
        }
        return map;
    }

    /**
     * 这个获得的是误差曲线和表格数据
     * @param area
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectErrorCurve")
    public Map selectErrorCurve(@Param("area") String area,  @Param("date") String date) {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> listError = new HashMap<>();

        if (date == null) {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            date = format.format(new Date());
        }else {
            if (date.length()>8){
                date=date.substring(0,4)+""+date.substring(5,7)+""+date.substring(8,10);
            }
        }

        //这个返回的是预测曲线，
        listError = errorAnalysisService.selectAreaDate(area, date);
        if (listError!=null&&listError.size()>0){
            map.put("listError", listError);
            map.put("date96", String96.jihe96());
            List<List<DygraphsTable>> listList = new LinkedList<>();//表格的第一行，时间
            List<DygraphsEntity> listListData = new LinkedList<>();//表格的其他行，具体数据
            listList.add(DygraphsTableToArray.toArray(String96.jihe96(), "时间"));
            String [] list1=(String[])listError.get("list1");
            String [] list2=(String[])listError.get("list2");
            String [] list3=(String[])listError.get("list3");
            listListData.add(DygraphsTableToArray.toArrayData(list1, "实际值" ));
            listListData.add(DygraphsTableToArray.toArrayData(list2, "预测值" ));
            listListData.add(DygraphsTableToArray.toArrayData(list3, "误差（%）" ));
            map.put("listList",listList);
            map.put("listListData",listListData);
        }else {
            map=null;
        }

        //这个返回的是一个实体，记录的是这一天对应最大误差时间，误差率等
     /*   LoadDayerrvalue loadDayerrvalue = errorAnalysisService.selectAreaDate(Integer.parseInt(area), date);
        map.put("loadDayerrvalue", loadDayerrvalue);*/

        return map;
    }
    /**
     * 多种算法误差分析
     * @param area
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findLoadShortdayerr")
    public List<ChartsOption> findLoadShortdayerr(@Param("area") Integer area, @Param("date") String date) {
        List<ChartsOption> chartsOptionList = new LinkedList<ChartsOption>();
        if (date == null) {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            date = format.format(new Date());
        } else {
            if (date.length() > 8) {
                date = date.substring(0, 4) + "" + date.substring(5, 7) + "" + date.substring(8, 10);
            }
        }
        List<LoadShortdayerrAll> loadShortdayerr = errorAnalysisService.findLoadShortdayerr(area, date);
        if (loadShortdayerr != null && loadShortdayerr.size() > 0) {
            for (int i = 0; i < loadShortdayerr.size(); i++) {
                String[] err = new String[]{loadShortdayerr.get(i).getErr().toString()};
                ChartsOption chartsOption = new ChartsOption();
                chartsOption.setHengZhou(new String[]{loadShortdayerr.get(i).getAlgoName()});
                chartsOption.setLegend(loadShortdayerr.get(i).getAlgoName());
                Series series = new Series();
                series.setSmooth(true);
                series.setType("line");
                series.setName(loadShortdayerr.get(i).getAlgoName());
                series.setData(err);
                chartsOption.setSeries(series);
                chartsOptionList.add(chartsOption);
            }
        }else{
            chartsOptionList = null;
        }
        return chartsOptionList;
    }
  /**
     * 这个获得的是误差的，最大误差时间，最大误差，准确率等等
     * @param area
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectLoadDayerrvalue")
    public LoadDayerrvalue selectLoadDayerrvalue(@Param("area") String area,  @Param("date") String date) {
        if (date == null) {
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            date = format.format(new Date());
        }else {
            if (date.length()>8){
                date=date.substring(0,4)+""+date.substring(5,7)+""+date.substring(8,10);
            }
        }
        if (area==""||area==null){
            area="53";
        }
        //这个返回的是一个实体，记录的是这一天对应最大误差时间，误差率等
        LoadDayerrvalue loadDayerrvalue =new LoadDayerrvalue();
        loadDayerrvalue=errorAnalysisService.selectAreaDate(Integer.parseInt(area), date);

        return loadDayerrvalue;
    }


}
