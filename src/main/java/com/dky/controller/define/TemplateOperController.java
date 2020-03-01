package com.dky.controller.define;

import com.dky.entity.define.Template;
import com.dky.service.define.TemplateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/templateOperController")
public class TemplateOperController {

    @Autowired
    private TemplateService templateService;

    //返回服务名服务号
    @RequestMapping(value="/templateInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,String>>templateInfo(){
        List<Map<String,String>> list=new ArrayList<>();

        for (Map<String,Object> map:templateService.getNameAndNumber()){
            Map<String,String> map1=new HashMap<>();
            for (String key:map.keySet()){
                map1.put(key,String.valueOf(map.get(key)));
               }
            list.add(map1);
        }
        return  list;
    }


    //插入模板信息到数据库
    @RequestMapping(value="/addTemplateInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public List<String> addTemplateInfo(@Param("servicename") String servicename,@Param("templateid1") String templateid1,
                                @Param("templateid2") String templateid2,@Param("templateid3") String templateid3,
                                @Param("templateid4") String templateid4,@Param("templateid5") String templateid5,
                                @Param("templateid6") String templateid6,@Param("templateid7") String templateid7,
                                @Param("templateid8") String templateid8,@Param("templateid9") String templateid9) {

        List<String> infoList=new ArrayList<>();

        if (templateService.judgeNameSame(servicename)) {
            String info="已存在相同服务名";
            infoList.add(info);
            return infoList;
        }
        Map<String, String> map = new HashMap<>();
        map.put("数据源编码设置", templateid1);
        map.put("熟数据设置", templateid2);
        map.put("量测编码设置", templateid3);
        map.put("开始时间设置", templateid4);
        map.put("结束时间设置", templateid5);
        map.put("数据周期设置", templateid6);
        map.put("数据步长设置", templateid7);
        map.put("特征值类型设置", templateid8);
        map.put("特征值统计周期设置", templateid9);
        List<Template> list = new ArrayList<>();
        int serviceNumber=templateService.numberAdd();
        for (String key : map.keySet()) {
            Template template = new Template();
            template.setServiceName(servicename);
            template.setServiceNumber(serviceNumber);
            template.setSetName(key);
            template.setSetValue(map.get(key));
            list.add(template);
        }
        templateService.insert(list);
        String info="模板信息插入成功";
        infoList.add(info);
        return infoList;
    }



}
