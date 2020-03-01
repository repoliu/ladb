package com.dky.controller.generatrixAnalysis;

import com.dky.entity.BusloadType;
import com.dky.entity.CharacteristicIndexBean;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.vo.BusReturnValue;
import com.dky.util.forecast.DygraphsTableUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharacteristicIndexAdd {

    public static List<DygraphsTable> toData(String daytraitType) {
        String[] strings = new String[2];
//        strings[0] = "日期";
        strings[0] = "设备名称";
        strings[1] = daytraitType;

        String[] str = new String[strings.length];
//        str[0] = "mdate";
        str[0] = "devDescr";
        str[1] = "daytraitValue";

        List<DygraphsTable> list = new LinkedList<>();
        for (int i = 0; i < strings.length; i++) {
            DygraphsTable dygraphsTable = new DygraphsTable();
            dygraphsTable.setUnresize(DygraphsTableUtil.UNRESIZE);
            dygraphsTable.setFixed(DygraphsTableUtil.FIXED);
            dygraphsTable.setTitle(strings[i]);//具体显示什么
            dygraphsTable.setField(str[i]);//表明这个是什么
            dygraphsTable.setAlign(DygraphsTableUtil.ALIGN);
            list.add(dygraphsTable);
        }
        return list;
    }

    public static List<CharacteristicIndexBean> conversionReturnValue(List<CharacteristicIndexBean> list) {
        List<CharacteristicIndexBean> listReturnValue = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CharacteristicIndexBean characteristicIndexBean = list.get(i);
            CharacteristicIndexBean characteristicIndex = new CharacteristicIndexBean();
            characteristicIndex.setMdate(characteristicIndexBean.getMdate());
            characteristicIndex.setDevDescr("<a onclick=\"queryGeneratrixAnalysis('"+characteristicIndexBean.getDevId()+"','"+characteristicIndexBean.getDevDescr()+"')\">"+characteristicIndexBean.getDevDescr()+"</a>");
            characteristicIndex.setDaytraitValue(characteristicIndexBean.getDaytraitValue());
            listReturnValue.add(characteristicIndex);
        }
        return listReturnValue;
    }
}
