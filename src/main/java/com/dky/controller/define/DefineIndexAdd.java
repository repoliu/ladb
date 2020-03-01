package com.dky.controller.define;

import com.dky.entity.DefineBean;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.util.forecast.DygraphsTableUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DefineIndexAdd {

    public static List<DygraphsTable> toData() {
        String[] strings = new String[6];
        strings[0] = "应用模块";
        strings[1] = "功能组件";
        strings[2] = "服务名";
        strings[3] = "服务号";
        strings[4] = "程序名";
        strings[5] = "操作";

        String[] str = new String[strings.length];
        str[0] = "appModule";
        str[1] = "funComponent";
        str[2] = "serviceName";
        str[3] = "serviceNumber";
        str[4] = "programName";
        str[5] = "operation";

        List<DygraphsTable> list = new LinkedList<>();
        for (int i = 0; i < strings.length; i++) {
            DygraphsTable dygraphsTable = new DygraphsTable();
            dygraphsTable.setUnresize(DygraphsTableUtil.UNRESIZE);
            dygraphsTable.setFixed(DygraphsTableUtil.FIXED);
            dygraphsTable.setTitle(strings[i]);//具体显示什么
            dygraphsTable.setField(str[i]);//表明这个是什么
            dygraphsTable.setAlign(DygraphsTableUtil.ALIGN);
//            dygraphsTable.setEdit("text");
            list.add(dygraphsTable);
        }
        return list;
    }

    public static List<DefineBean> conversionReturnValue(List<DefineBean> list) {
        List<DefineBean> listReturnValue = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DefineBean DefineBean = list.get(i);
            DefineBean DefineBeanIndex = new DefineBean();
            DefineBeanIndex.setDefineId(DefineBean.getDefineId());
            DefineBeanIndex.setAppModule(DefineBean.getAppModule());
            DefineBeanIndex.setFunComponent(DefineBean.getFunComponent());
            DefineBeanIndex.setServiceName(DefineBean.getServiceName()+"<a href='#'><i class=\"layui-icon\" style='float: right' onclick=\"popup()\"></i></a>");
            DefineBeanIndex.setServiceNumber(DefineBean.getServiceNumber());
            DefineBeanIndex.setProgramName(DefineBean.getProgramName());
            DefineBeanIndex.setOperation("<button class=\"layui-btn\" onclick=\"del("+DefineBean.getAppModule()+","+DefineBean.getDefineId()+")\">删除</button><button class=\"layui-btn\" onclick=\"writes("+DefineBean.getDefineId()+")\">编辑</button>");
            listReturnValue.add(DefineBeanIndex);
        }
        return listReturnValue;
    }
}
