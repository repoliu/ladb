package com.dky.controller.generatrixAnalysis;

import com.dky.entity.CharacteristicIndexBean;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.service.generatrixAnalysis.CharacteristicIndexService;
import com.dky.util.daoOperate.MultipleDataSource;
import com.google.common.collect.Maps;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/characteristicIndexController")
public class CharacteristicIndexController {

    @Autowired
    private CharacteristicIndexService characteristicIndexService;

    private String datasource = "dataSource2";

    @RequestMapping("/jump")
    public String jump(HttpServletRequest request){
        String busName = request.getParameter("busName");
        if (busName != null && busName != "") {
            request.setAttribute("busName", busName);
        } else {
            request.setAttribute("busName", "四川全网");
        }
        String date = null;
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            date = characteristicIndexService.findMaxDate();
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        request.setAttribute("defaultDate", date);
        return "generatrixAnalysis/characteristicIndex";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Map findAll(String year){
        Map<String, Object> map = Maps.newHashMap();
        List<CharacteristicIndexBean> characteristicIndexBeanList1 = new ArrayList<>();
        List<CharacteristicIndexBean> characteristicIndexBeanList2 = new ArrayList<>();
        List<CharacteristicIndexBean> characteristicIndexBeanList3 = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        try {
            String[] timelist = year.split("-"); //timelist[0]+""+timelist[1]+""+timelist[2]得到yyyyMMdd格式日期
            characteristicIndexBeanList1 = characteristicIndexService.findAllOne(timelist[0]+""+timelist[1]+""+timelist[2]);
            characteristicIndexBeanList2 = characteristicIndexService.findAllTwo(timelist[0]+""+timelist[1]+""+timelist[2]);
            characteristicIndexBeanList3 = characteristicIndexService.findAllThree(timelist[0]);
            //这个代表了表格的第一行
            List<List<DygraphsTable>> dygraphs1 = new ArrayList<>();
            List<List<DygraphsTable>> dygraphs2 = new ArrayList<>();
            List<List<DygraphsTable>> dygraphs3 = new ArrayList<>();
            dygraphs1.add(CharacteristicIndexAdd.toData("日平均负荷"));
            dygraphs2.add(CharacteristicIndexAdd.toData("负荷率"));
            dygraphs3.add(CharacteristicIndexAdd.toData("年最大负荷利用小时数"));
            //这个代表了其他行数据
            List<CharacteristicIndexBean> returnValue1 = new ArrayList<>();
            List<CharacteristicIndexBean> returnValue2 = new ArrayList<>();
            List<CharacteristicIndexBean> returnValue3 = new ArrayList<>();
            returnValue1 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList1);
            returnValue2 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList2);
            returnValue3 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList3);
            map.put("dygraphs1", dygraphs1);
            map.put("dygraphs2", dygraphs2);
            map.put("dygraphs3", dygraphs3);
            map.put("charaReturnValue1", returnValue1);
            map.put("charaReturnValue2", returnValue2);
            map.put("charaReturnValue3", returnValue3);
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        MultipleDataSource.clearDBType();
        return map;
    }

    @RequestMapping("/findAllByAreaAndYear")
    @ResponseBody
    public Map<String, Object> findAllByAreaAndYear(String area, String year){
        Map<String, Object> map = Maps.newHashMap();
        List<CharacteristicIndexBean> characteristicIndexBeanList1 = new ArrayList<>();
        List<CharacteristicIndexBean> characteristicIndexBeanList2 = new ArrayList<>();
        List<CharacteristicIndexBean> characteristicIndexBeanList3 = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        try {
            String[] timelist = year.split("-");
            characteristicIndexBeanList1 = characteristicIndexService.findAllByAreaAndYearOne(area, timelist[0]+""+timelist[1]+""+timelist[2]);
            characteristicIndexBeanList2 = characteristicIndexService.findAllByAreaAndYearTwo(area, timelist[0]+""+timelist[1]+""+timelist[2]);
            characteristicIndexBeanList3 = characteristicIndexService.findAllByAreaAndYearThree(area, timelist[0]);
            //这个代表了表格的第一行
            List<List<DygraphsTable>> dygraphs1 = new ArrayList<>();
            List<List<DygraphsTable>> dygraphs2 = new ArrayList<>();
            List<List<DygraphsTable>> dygraphs3 = new ArrayList<>();
            dygraphs1.add(CharacteristicIndexAdd.toData("日平均负荷"));
            dygraphs2.add(CharacteristicIndexAdd.toData("负荷率"));
            dygraphs3.add(CharacteristicIndexAdd.toData("年最大负荷利用小时数"));
            //这个代表了其他行数据
            List<CharacteristicIndexBean> returnValue1 = new ArrayList<>();
            List<CharacteristicIndexBean> returnValue2 = new ArrayList<>();
            List<CharacteristicIndexBean> returnValue3 = new ArrayList<>();
            returnValue1 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList1);
            returnValue2 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList2);
            returnValue3 = CharacteristicIndexAdd.conversionReturnValue(characteristicIndexBeanList3);
            map.put("dygraphs1", dygraphs1);
            map.put("dygraphs2", dygraphs2);
            map.put("dygraphs3", dygraphs3);
            map.put("charaReturnValue1", returnValue1);
            map.put("charaReturnValue2", returnValue2);
            map.put("charaReturnValue3", returnValue3);
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        MultipleDataSource.clearDBType();
        return map;
        }
}
