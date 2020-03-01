package com.dky.controller.generatrixAnalysis;

import com.dky.entity.BusloadType;
import com.dky.entity.CharacteristicIndexBean;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.vo.BusReturnValue;
import com.dky.util.forecast.DygraphsTableUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 这个类时母线分类数据获取的时候用的
 * xiali add
 */
public class BusJumpAdditional {



    public static List<DygraphsTable> toData(String typeDegree ) {
        //支持度现在先不展示了，所以下边这个集合长度改成1
        String[] strings = new String[1];
        strings[0] = typeDegree;
     //   strings[1] = "支持度";

        String[] str = new String[strings.length];
        str[0] = "devDescr";
       // str[1] = "typeDegree";
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



    public static List<BusReturnValue> conversionBusReturnValue(List<BusloadType> list) {
        List<BusReturnValue> listBusReturnValue = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BusloadType busloadType = list.get(i);
            BusReturnValue busReturnValue = new BusReturnValue();
            busReturnValue.setDevDescr(busloadType.getDevDescr());
            busReturnValue.setTypeDegree(busloadType.getTypeDegree() + "");
            listBusReturnValue.add(busReturnValue);
        }
        return listBusReturnValue;
    }
}
