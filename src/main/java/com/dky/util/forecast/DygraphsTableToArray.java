package com.dky.util.forecast;

import com.dky.entity.forecast.DygraphsEntity;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.influ.Autovalue;
import com.dky.entity.influ.LoadRainInfo;
import com.dky.entity.influ.vo.RainSelect;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DygraphsTableToArray {
    public static List<DygraphsTable> toArray(String[] strings, String string) {
        List<DygraphsTable> list = new LinkedList<>();
        String[] strData = new String[97];
        strData = data96();
        for (int i = 0; i < 99; i++) {
            DygraphsTable dygraphsTable = new DygraphsTable();
            if (i == 0) {
                dygraphsTable.setType(DygraphsTableUtil.TYPE);
                dygraphsTable.setFixed(DygraphsTableUtil.FIXED);
            } else if (i == 1) {
                dygraphsTable.setTitle(string);
                dygraphsTable.setMinWidth(DygraphsTableUtil.TITLEOne);
                dygraphsTable.setField("date");
                dygraphsTable.setFixed(DygraphsTableUtil.FIXED);
            } else {
                dygraphsTable.setTitle(strings[i - 2]);
                dygraphsTable.setMinWidth(DygraphsTableUtil.TITLE);
                dygraphsTable.setField(strData[i - 2]);
            }

            dygraphsTable.setAlign(DygraphsTableUtil.ALIGN);
            list.add(dygraphsTable);
        }
        return list;
    }


    public static List<DygraphsTable> selectRain( ) {
        String[] strings = new String[6];
        strings[0] = "时间";
        strings[1] = "降雨";
        strings[2] = "温度";
        strings[3] = "风力";
        strings[4] = "全天降雨量";
        strings[5] = "降雨级别";

        String[] str = new String[6];
        str[0] = "time";
        str[1] = "rain";
        str[2] = "temperature";
        str[3] = "wp";
        str[4] = "rainAll";
        str[5] = "rains";
        List<DygraphsTable> list = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            DygraphsTable dygraphsTable = new DygraphsTable();
            if (i==0){
                dygraphsTable.setSort(true);
            }
            dygraphsTable.setTitle(strings[i]);//具体显示什么
            dygraphsTable.setField(str[i]);//表明这个是什么
            dygraphsTable.setAlign(DygraphsTableUtil.ALIGN);
            list.add(dygraphsTable);
        }
        return list;
    }

    public static RainSelect selectRains(Autovalue autovalue, String str) {
        RainSelect rainSelect = new RainSelect();
        rainSelect.setId(autovalue.getStcd());
        //数据库的这个时间是Date类型的，把他转成字符串
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            ca1.setTime(autovalue.getTime());
        } catch (Exception e) {
        }
        ca1.add(Calendar.DAY_OF_MONTH, +0);//
        //转成字符串直接赋值
        rainSelect.setTime(format.format(ca1.getTime()));
        rainSelect.setRain(autovalue.getRain().toString());
        rainSelect.setTemperature(autovalue.getTemperature().toString());
        rainSelect.setWp(autovalue.getWp().toString());
        rainSelect.setRains(str);
        return rainSelect;
    }

    public static RainSelect selectRainArrayAutovalue(Autovalue autovalue) {
        RainSelect rainSelect = new RainSelect();
        rainSelect.setId(autovalue.getStcd());//代表了大小雨
        //数据库的这个时间是Date类型的，把他转成字符串
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            ca1.setTime(autovalue.getTime());
        } catch (Exception e) {
        }
        ca1.add(Calendar.DAY_OF_MONTH, +0);//
        //转成字符串直接赋值
        rainSelect.setTime(format.format(ca1.getTime()));//时间
        //数据库得到的降雨量上在除以10，
        rainSelect.setRain(autovalue.getRain().doubleValue()/10+"");//每一天的雨量
        //在求出的温度上在除以10，显示摄氏温度
        rainSelect.setTemperature(autovalue.getTemperature()/10+"");
        //风力
        if(autovalue.getWp()!=null){
            rainSelect.setWp(autovalue.getWp().toString());
        }else {
            rainSelect.setWp("0");
        }
        //降雨级别
        rainSelect.setRains(autovalue.getStcd());
        //数据库得到的当天总降雨量上在除以10，
        rainSelect.setRainAll(autovalue.getWd().doubleValue()/10+"");//总降雨量
        return rainSelect;
    }
    public static RainSelect selectRainArrayLoadRainInfo(LoadRainInfo loadRainInfo){
        RainSelect rainSelect = new RainSelect();
        rainSelect.setId(loadRainInfo.getAreaname());
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            ca1.setTime(loadRainInfo.getTime());
        } catch (Exception e) {
        }
        ca1.add(Calendar.DAY_OF_MONTH, +0);//
        rainSelect.setTime(format.format(ca1.getTime()));
        //数据库得到的降雨量上在除以10，
        rainSelect.setRain(loadRainInfo.getRain().doubleValue()/10+"");
        //在求出的温度上在除以10，显示摄氏温度
        rainSelect.setTemperature(loadRainInfo.getTemperature()/10+"");
        //风力
        if (loadRainInfo.getWp()==(null)){
            rainSelect.setWp("0");
        }else {
            rainSelect.setWp(loadRainInfo.getWp().toString());
        }


        //降雨级别
        rainSelect.setRains(loadRainInfo.getRainLeve());
        //数据库得到的当天总降雨量上在除以10，
        rainSelect.setRainAll(loadRainInfo.getRainDay()/10+"");
        return rainSelect;
    }







    public static DygraphsEntity toArrayData(String[] strings, String string) {
        DygraphsEntity dygraphsEntity = new DygraphsEntity();
        dygraphsEntity.setDate(string);
        dygraphsEntity.setF00(strings[0]);
        dygraphsEntity.setF01(strings[1]);
        dygraphsEntity.setF02(strings[2]);
        dygraphsEntity.setF03(strings[3]);
        dygraphsEntity.setF04(strings[4]);
        dygraphsEntity.setF05(strings[5]);
        dygraphsEntity.setF06(strings[6]);
        dygraphsEntity.setF07(strings[7]);
        dygraphsEntity.setF08(strings[8]);
        dygraphsEntity.setF09(strings[9]);
        dygraphsEntity.setF10(strings[10]);
        dygraphsEntity.setF11(strings[11]);
        dygraphsEntity.setF12(strings[12]);
        dygraphsEntity.setF13(strings[13]);
        dygraphsEntity.setF14(strings[14]);
        dygraphsEntity.setF15(strings[15]);
        dygraphsEntity.setF16(strings[16]);
        dygraphsEntity.setF17(strings[17]);
        dygraphsEntity.setF18(strings[18]);
        dygraphsEntity.setF19(strings[19]);
        dygraphsEntity.setF20(strings[20]);
        dygraphsEntity.setF21(strings[21]);
        dygraphsEntity.setF22(strings[22]);
        dygraphsEntity.setF23(strings[23]);
        dygraphsEntity.setF24(strings[24]);
        dygraphsEntity.setF25(strings[25]);
        dygraphsEntity.setF26(strings[26]);
        dygraphsEntity.setF27(strings[27]);
        dygraphsEntity.setF28(strings[28]);
        dygraphsEntity.setF29(strings[29]);
        dygraphsEntity.setF30(strings[30]);
        dygraphsEntity.setF31(strings[31]);
        dygraphsEntity.setF32(strings[32]);
        dygraphsEntity.setF33(strings[33]);
        dygraphsEntity.setF34(strings[34]);
        dygraphsEntity.setF35(strings[35]);
        dygraphsEntity.setF36(strings[36]);
        dygraphsEntity.setF37(strings[37]);
        dygraphsEntity.setF38(strings[38]);
        dygraphsEntity.setF39(strings[39]);
        dygraphsEntity.setF40(strings[40]);
        dygraphsEntity.setF41(strings[41]);
        dygraphsEntity.setF42(strings[42]);
        dygraphsEntity.setF43(strings[43]);
        dygraphsEntity.setF44(strings[44]);
        dygraphsEntity.setF45(strings[45]);
        dygraphsEntity.setF46(strings[46]);
        dygraphsEntity.setF47(strings[47]);
        dygraphsEntity.setF48(strings[48]);
        dygraphsEntity.setF49(strings[49]);
        dygraphsEntity.setF50(strings[50]);
        dygraphsEntity.setF51(strings[51]);
        dygraphsEntity.setF52(strings[52]);
        dygraphsEntity.setF53(strings[53]);
        dygraphsEntity.setF54(strings[54]);
        dygraphsEntity.setF55(strings[55]);
        dygraphsEntity.setF56(strings[56]);
        dygraphsEntity.setF57(strings[57]);
        dygraphsEntity.setF58(strings[58]);
        dygraphsEntity.setF59(strings[59]);
        dygraphsEntity.setF60(strings[60]);
        dygraphsEntity.setF61(strings[61]);
        dygraphsEntity.setF62(strings[62]);
        dygraphsEntity.setF63(strings[63]);
        dygraphsEntity.setF64(strings[64]);
        dygraphsEntity.setF65(strings[65]);
        dygraphsEntity.setF66(strings[66]);
        dygraphsEntity.setF67(strings[67]);
        dygraphsEntity.setF68(strings[68]);
        dygraphsEntity.setF69(strings[69]);
        dygraphsEntity.setF70(strings[70]);
        dygraphsEntity.setF71(strings[71]);
        dygraphsEntity.setF72(strings[72]);
        dygraphsEntity.setF73(strings[73]);
        dygraphsEntity.setF74(strings[74]);
        dygraphsEntity.setF75(strings[75]);
        dygraphsEntity.setF76(strings[76]);
        dygraphsEntity.setF77(strings[77]);
        dygraphsEntity.setF78(strings[78]);
        dygraphsEntity.setF79(strings[79]);
        dygraphsEntity.setF80(strings[80]);
        dygraphsEntity.setF81(strings[81]);
        dygraphsEntity.setF82(strings[82]);
        dygraphsEntity.setF83(strings[83]);
        dygraphsEntity.setF84(strings[84]);
        dygraphsEntity.setF85(strings[85]);
        dygraphsEntity.setF86(strings[86]);
        dygraphsEntity.setF87(strings[87]);
        dygraphsEntity.setF88(strings[88]);
        dygraphsEntity.setF89(strings[89]);
        dygraphsEntity.setF90(strings[90]);
        dygraphsEntity.setF91(strings[91]);
        dygraphsEntity.setF92(strings[92]);
        dygraphsEntity.setF93(strings[93]);
        dygraphsEntity.setF94(strings[94]);
        dygraphsEntity.setF95(strings[95]);
        dygraphsEntity.setF96(strings[96]);
        return dygraphsEntity;
    }

    public static String[] data96() {
        String[] str = {"f00", "f01", "f02", "f03", "f04", "f05", "f06", "f07", "f08", "f09", "f10", "f11", "f12", "f13", "f14", "f15", "f16", "f17", "f18", "f19",
                "f20", "f21", "f22", "f23", "f24", "f25", "f26", "f27", "f28", "f29", "f30", "f31", "f32", "f33", "f34", "f35", "f36", "f37", "f38", "f39",
                "f40", "f41", "f42", "f43", "f44", "f45", "f46", "f47", "f48", "f49", "f50", "f51", "f52", "f53", "f54", "f55", "f56", "f57", "f58", "f59",
                "f60", "f61", "f62", "f63", "f64", "f65", "f66", "f67", "f68", "f69", "f70", "f71", "f72", "f73", "f74", "f75", "f76", "f77", "f78", "f79",
                "f80", "f81", "f82", "f83", "f84", "f85", "f86", "f87", "f88", "f89", "f90", "f91", "f92", "f93", "f94", "f95", "f96"
        };

        return str;
    }


}
