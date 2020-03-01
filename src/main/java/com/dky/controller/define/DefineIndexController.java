package com.dky.controller.define;

import com.dky.entity.DefineBean;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.service.define.DefineIndexService;
import com.dky.util.daoOperate.DeleteFunction;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/define")
public class DefineIndexController {
    @Autowired
    private DefineIndexService defineIndexService;
    /**
     * @return 首页所在地址的字符串形式
     */
    @RequestMapping(value="/defineIndex" ,method = RequestMethod.GET)
    public String defineIndex(){
        return "define/defineIndex";
    }

    @RequestMapping("templateOperator")
    public String rainload() {
        return "define/templateOperator";
    }




    @RequestMapping(value="/formDefine" ,method = RequestMethod.GET)
    public String formDefine(){
        return "define/formDefine";
    }

    @RequestMapping(value="/add" ,method = RequestMethod.GET)
    public String add(){
        return "define/addDefine";
    }
    @RequestMapping(value="/writes" ,method = RequestMethod.GET)
    public String writes(String id, Model model){
        DefineBean serviceAllById = defineIndexService.findAllById(id);
        model.addAttribute("serviceAllById",serviceAllById);
        return "define/writesDefine";
    }


    @RequestMapping("/findAll")
    @ResponseBody
    public Map findAll(){
        Map<String, Object> map = Maps.newHashMap();
        List<DefineBean> defineBeans = new ArrayList<>();
        try {
            defineBeans = defineIndexService.findAll();
            //这个代表了表格的第一行
            List<List<DygraphsTable>> dygraphs = new ArrayList<>();
            dygraphs.add(DefineIndexAdd.toData());
            //这个代表了其他行数据
            List<DefineBean> returnValue = new ArrayList<>();
            returnValue = DefineIndexAdd.conversionReturnValue(defineBeans);
            map.put("dygraphs", dygraphs);
            map.put("defineReturnValue", returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value="/addDate" ,method = RequestMethod.POST)
    @ResponseBody
    public List<String> addDate(DefineBean defineBean){
        List<String> list = new ArrayList<>();
        defineBean.setDefineId(getIdByUUId());
        defineBean.setVerTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        defineIndexService.insertDefine(defineBean);
        list.add("数据插入成功！！");
        return list;
    }
    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    @ResponseBody
    public List<String> delete(String id){
        List<String> list = new ArrayList<>();
        DeleteFunction deleteFunction = new DeleteFunction();
        String tableName = "loadfor.define";
        String whereCondition = "define_id = "+id;
        try {
            int deleteTable = deleteFunction.deleteTable(tableName, whereCondition);
            if (deleteTable == 1) {
                list.add("数据删除成功！！");
            }else {
                list.add("数据删除失败！！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @RequestMapping(value="/writes" ,method = RequestMethod.POST)
    @ResponseBody
    public List<String> writes(String defineId, String appModule, String funComponent, String serviceName, String serviceNumber, String programName){
        List<String> list = new ArrayList<>();
        DeleteFunction deleteFunction = new DeleteFunction();
        String verTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String tableName = "loadfor.define";
        String whereCondition = "define_id = "+defineId;
        String strKey = "{app_module,fun_component,service_name,service_number,program_name,ver_time}";
        String strValue = "{"+appModule+","+funComponent+","+serviceName+","+serviceNumber+","+programName+","+verTime+"}";
        try {
            int deleteTable = deleteFunction.updateTable(tableName,strKey,strValue,whereCondition);
            if (deleteTable == 1) {
                list.add("数据修改成功！！");
            }else {
                list.add("数据修改失败！！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 通过UUID生成16位唯一ID号
    public static String getIdByUUId() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
    //  0 代表前面补充0
    //  4 代表长度为4
    //  d 代表参数为正数型
        return String.format("1%015d", hashCodeV);
    }
}
