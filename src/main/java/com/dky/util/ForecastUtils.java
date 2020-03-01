package com.dky.util;

import com.dky.entity.LoadForesult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForecastUtils {





    //七个按钮的时间
    public  static  String [] strings(String string){
        String [] str=new String[14];
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Calendar ca1 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca2 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca3 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca4 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca5 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca6 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        Calendar ca7 = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        try {
            Date date = format.parse(string);
            ca1.setTime(date);
            ca2.setTime(date);
            ca3.setTime(date);
            ca4.setTime(date);
            ca5.setTime(date);
            ca6.setTime(date);
            ca7.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ca1.add(Calendar.DAY_OF_MONTH, -3);//
        ca2.add(Calendar.DAY_OF_MONTH, -2);//
        ca3.add(Calendar.DAY_OF_MONTH, -1);//
        ca4.add(Calendar.DAY_OF_MONTH, +0);//
        ca5.add(Calendar.DAY_OF_MONTH, +1);//
        ca6.add(Calendar.DAY_OF_MONTH, +2);//
        ca7.add(Calendar.DAY_OF_MONTH, +3);//

        str[0]=format.format(ca1.getTime()).toString();
        str[1]=format.format(ca2.getTime()).toString();
        str[2]=format.format(ca3.getTime()).toString();
        str[3]=format.format(ca4.getTime()).toString();
        str[4]=format.format(ca5.getTime()).toString();
        str[5]=format.format(ca6.getTime()).toString();
        str[6]=format.format(ca7.getTime()).toString();


        str[7]=str[0].substring(5,10);
        str[8]=str[1].substring(5,10);
        str[9]=str[2].substring(5,10);
        str[10]=str[3].substring(5,10);
        str[11]=str[4].substring(5,10);
        str[12]=str[5].substring(5,10);
        str[13]=str[6].substring(5,10);



      //  2014-02-02

        return str;
    }
    public static String[] toStrings(LoadForesult loadForesult) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (loadForesult!=null){
            map.put("f00",	loadForesult.getF00().toString());
            map.put("f01",	loadForesult.getF01().toString());
            map.put("f02",	loadForesult.getF02().toString());
            map.put("f03",	loadForesult.getF03().toString());
            map.put("f04",	loadForesult.getF04().toString());
            map.put("f05",	loadForesult.getF05().toString());
            map.put("f06",	loadForesult.getF06().toString());
            map.put("f07",	loadForesult.getF07().toString());
            map.put("f08",	loadForesult.getF08().toString());
            map.put("f09",	loadForesult.getF09().toString());
            map.put("f10",	loadForesult.getF10().toString());
            map.put("f11",	loadForesult.getF11().toString());
            map.put("f12",	loadForesult.getF12().toString());
            map.put("f13",	loadForesult.getF13().toString());
            map.put("f14",	loadForesult.getF14().toString());
            map.put("f15",	loadForesult.getF15().toString());
            map.put("f16",	loadForesult.getF16().toString());
            map.put("f17",	loadForesult.getF17().toString());
            map.put("f18",	loadForesult.getF18().toString());
            map.put("f19",	loadForesult.getF19().toString());
            map.put("f20",	loadForesult.getF20().toString());
            map.put("f21",	loadForesult.getF21().toString());
            map.put("f22",	loadForesult.getF22().toString());
            map.put("f23",	loadForesult.getF23().toString());
            map.put("f24",	loadForesult.getF24().toString());
            map.put("f25",	loadForesult.getF25().toString());
            map.put("f26",	loadForesult.getF26().toString());
            map.put("f27",	loadForesult.getF27().toString());
            map.put("f28",	loadForesult.getF28().toString());
            map.put("f29",	loadForesult.getF29().toString());
            map.put("f30",	loadForesult.getF30().toString());
            map.put("f31",	loadForesult.getF31().toString());
            map.put("f32",	loadForesult.getF32().toString());
            map.put("f33",	loadForesult.getF33().toString());
            map.put("f34",	loadForesult.getF34().toString());
            map.put("f35",	loadForesult.getF35().toString());
            map.put("f36",	loadForesult.getF36().toString());
            map.put("f37",	loadForesult.getF37().toString());
            map.put("f38",	loadForesult.getF38().toString());
            map.put("f39",	loadForesult.getF39().toString());
            map.put("f40",	loadForesult.getF40().toString());
            map.put("f41",	loadForesult.getF41().toString());
            map.put("f42",	loadForesult.getF42().toString());
            map.put("f43",	loadForesult.getF43().toString());
            map.put("f44",	loadForesult.getF44().toString());
            map.put("f45",	loadForesult.getF45().toString());
            map.put("f46",	loadForesult.getF46().toString());
            map.put("f47",	loadForesult.getF47().toString());
            map.put("f48",	loadForesult.getF48().toString());
            map.put("f49",	loadForesult.getF49().toString());
            map.put("f50",	loadForesult.getF50().toString());
            map.put("f51",	loadForesult.getF51().toString());
            map.put("f52",	loadForesult.getF52().toString());
            map.put("f53",	loadForesult.getF53().toString());
            map.put("f54",	loadForesult.getF54().toString());
            map.put("f55",	loadForesult.getF55().toString());
            map.put("f56",	loadForesult.getF56().toString());
            map.put("f57",	loadForesult.getF57().toString());
            map.put("f58",	loadForesult.getF58().toString());
            map.put("f59",	loadForesult.getF59().toString());
            map.put("f60",	loadForesult.getF60().toString());
            map.put("f61",	loadForesult.getF61().toString());
            map.put("f62",	loadForesult.getF62().toString());
            map.put("f63",	loadForesult.getF63().toString());
            map.put("f64",	loadForesult.getF64().toString());
            map.put("f65",	loadForesult.getF65().toString());
            map.put("f66",	loadForesult.getF66().toString());
            map.put("f67",	loadForesult.getF67().toString());
            map.put("f68",	loadForesult.getF68().toString());
            map.put("f69",	loadForesult.getF69().toString());
            map.put("f70",	loadForesult.getF70().toString());
            map.put("f71",	loadForesult.getF71().toString());
            map.put("f72",	loadForesult.getF72().toString());
            map.put("f73",	loadForesult.getF73().toString());
            map.put("f74",	loadForesult.getF74().toString());
            map.put("f75",	loadForesult.getF75().toString());
            map.put("f76",	loadForesult.getF76().toString());
            map.put("f77",	loadForesult.getF77().toString());
            map.put("f78",	loadForesult.getF78().toString());
            map.put("f79",	loadForesult.getF79().toString());
            map.put("f80",	loadForesult.getF80().toString());
            map.put("f81",	loadForesult.getF81().toString());
            map.put("f82",	loadForesult.getF82().toString());
            map.put("f83",	loadForesult.getF83().toString());
            map.put("f84",	loadForesult.getF84().toString());
            map.put("f85",	loadForesult.getF85().toString());
            map.put("f86",	loadForesult.getF86().toString());
            map.put("f87",	loadForesult.getF87().toString());
            map.put("f88",	loadForesult.getF88().toString());
            map.put("f89",	loadForesult.getF89().toString());
            map.put("f90",	loadForesult.getF90().toString());
            map.put("f91",	loadForesult.getF91().toString());
            map.put("f92",	loadForesult.getF92().toString());
            map.put("f93",	loadForesult.getF93().toString());
            map.put("f94",	loadForesult.getF94().toString());
            map.put("f95",	loadForesult.getF95().toString());
            map.put("f96",	loadForesult.getF96().toString());
        }else {
            map.put("f00", 	 "0");
            map.put("f01",	 "0");
            map.put("f02",	 "0");
            map.put("f03",	 "0");
            map.put("f04",	 "0");
            map.put("f05",	 "0");
            map.put("f06",	 "0");
            map.put("f07",	 "0");
            map.put("f08",	 "0");
            map.put("f09",	 "0");
            map.put("f10",	 "0");
            map.put("f11",	 "0");
            map.put("f12",	 "0");
            map.put("f13",	 "0");
            map.put("f14",	 "0");
            map.put("f15",	 "0");
            map.put("f16",	 "0");
            map.put("f17",	 "0");
            map.put("f18",	 "0");
            map.put("f19",	 "0");
            map.put("f20",	 "0");
            map.put("f21",	 "0");
            map.put("f22",	 "0");
            map.put("f23",	 "0");
            map.put("f24",	 "0");
            map.put("f25",	 "0");
            map.put("f26",	 "0");
            map.put("f27",	 "0");
            map.put("f28",	 "0");
            map.put("f29",	 "0");
            map.put("f30",	 "0");
            map.put("f31",	 "0");
            map.put("f32",	 "0");
            map.put("f33",	 "0");
            map.put("f34",	 "0");
            map.put("f35",	 "0");
            map.put("f36",	 "0");
            map.put("f37",	 "0");
            map.put("f38",	 "0");
            map.put("f39",	 "0");
            map.put("f40",	 "0");
            map.put("f41",	 "0");
            map.put("f42",	 "0");
            map.put("f43",	 "0");
            map.put("f44",	 "0");
            map.put("f45",	 "0");
            map.put("f46",	 "0");
            map.put("f47",	 "0");
            map.put("f48",	 "0");
            map.put("f49",	 "0");
            map.put("f50",	 "0");
            map.put("f51",	 "0");
            map.put("f52",	 "0");
            map.put("f53",	 "0");
            map.put("f54",	 "0");
            map.put("f55",	 "0");
            map.put("f56",	 "0");
            map.put("f57",	 "0");
            map.put("f58",	 "0");
            map.put("f59",	 "0");
            map.put("f60",	 "0");
            map.put("f61",	 "0");
            map.put("f62",	 "0");
            map.put("f63",	 "0");
            map.put("f64",	 "0");
            map.put("f65",	 "0");
            map.put("f66",	 "0");
            map.put("f67",	 "0");
            map.put("f68",	 "0");
            map.put("f69",	 "0");
            map.put("f70",	 "0");
            map.put("f71",	 "0");
            map.put("f72",	 "0");
            map.put("f73",	 "0");
            map.put("f74",	 "0");
            map.put("f75",	 "0");
            map.put("f76",	 "0");
            map.put("f77",	 "0");
            map.put("f78",	 "0");
            map.put("f79",	 "0");
            map.put("f80",	 "0");
            map.put("f81",	 "0");
            map.put("f82",	 "0");
            map.put("f83",	 "0");
            map.put("f84",	 "0");
            map.put("f85",	 "0");
            map.put("f86",	 "0");
            map.put("f87",	 "0");
            map.put("f88",	 "0");
            map.put("f89",	 "0");
            map.put("f90",	 "0");
            map.put("f91",	 "0");
            map.put("f92",	 "0");
            map.put("f93",	 "0");
            map.put("f94",	 "0");
            map.put("f95",	 "0");
            map.put("f96",	 "0");
        }
        String[] strings = new String[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            strings[i] = map.get(key);
            i++;
        }
        return strings;
    }

}
