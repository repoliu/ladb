package com.dky.controller;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.Dbarea;
import com.dky.entity.vo.TreeDataTemp;
import com.dky.service.common.DbareaService;
import com.dky.util.ReadProperties;
import com.dky.util.TreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 生成树状图的Controller类
 * @author YangSL
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    private DbareaService dbareaService;
    /**
     * 使用构造方法注入service，可以明确成员变量的加载顺序；
     * 加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变；
     */
    @Autowired
    public AreaController(DbareaService dbareaService){
        this.dbareaService = dbareaService;
    }

    /**
     * 横向地区的树
     * @return 异步返回树的节点信息
     */
    @RequestMapping(value = "findAreaByArea",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<Dbarea> findAreaByArea(int area) {
        List<Dbarea> dbareaList = dbareaService.findAreaByArea(area);
        return dbareaList;
    }
    /**
     * 生成全节点地区的树
     * @return 异步返回树的节点信息
     */
    @RequestMapping(value = "loadTree",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadTree() {
        List<Dbarea> dbareaList = dbareaService.findAllArea();
        //ParserConfig.getGlobalInstance().setAsmEnable(false);
        List<TreeDataTemp> treeDataTemp = TreeData.createTreeData(dbareaList, ReadProperties.readProperties("parentId"));
        return JSONArray.toJSONString(treeDataTemp);
    }

    /**
     * 生成三级地区树，也就是到省级截止
     * @return 返回三级地区树
     */
    @RequestMapping(value = "loadProvinceTree",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadProvinceTree() {
        List<Dbarea> dbareaList = dbareaService.findAllArea();
        //ParserConfig.getGlobalInstance().setAsmEnable(false);
        List<TreeDataTemp> treeDataTemp = TreeData.createProvinceTreeData(dbareaList,ReadProperties.readProperties("parentId"));
        return JSONArray.toJSONString(treeDataTemp);
    }

}
