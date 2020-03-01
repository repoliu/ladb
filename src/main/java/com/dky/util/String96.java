package com.dky.util;

import com.alibaba.druid.util.StringUtils;
import com.dky.entity.LoadForesult;
import com.dky.entity.LoadHisdata;
import com.dky.entity.LoadMonthtypeload96;


import java.math.BigDecimal;
import java.util.*;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 21:59
 */
public class String96 {

    public static String StringToString(String string) {
            /* dateMin=dateMin.substring(0,6);//数据库获取的时间是年月日的，这个地方截取了年月
        dateMax=dateMax.substring(0,6);//数据库获取的时间是年月日的，这个地方截取了年月*/
        String str = null;
        if (!StringUtils.isEmpty(string)) {
            String str1 = string.substring(0, 4);
            String str2 = string.substring(4, 6);
            String str3 = "01";
            str = str1 + "-" + str2 + "-" + str3;
        }

        return str;
    }


    public static String StringToStringMax(String string) {
        String str = null;
        if (!StringUtils.isEmpty(string)) {
            String str1 = string.substring(0, 4);
            String str2 = string.substring(4, 6);
            String str3 = "31";
            str = str1 + "-" + str2 + "-" + str3;
        }

        return str;
    }

    public static String jinDuTiao(BigDecimal big) {
        String str = "";
        str = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+ "";
        int shu = str.indexOf(".", 0);
        if ((str.length() - shu) == 2) {
            str = str.substring(0, shu + 2);
        } else {
            str = str.substring(0, shu + 3);
        }
        return str;
    }


    public static String BigToString(BigDecimal big) {
        String str = "";
        Double dou = new Double(0);
        str = big.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()/ UtilType.PROGRESS_NUMBER_DOUBLE+"";//转换成double类型
        int shu = str.indexOf(".", 0);
        if ((str.length() - shu) == 2) {
            str = str.substring(0, shu + 2);
        } else {
            str = str.substring(0, shu + 3);
        }
        return str;
    }




    //求5个按钮的时间
    public static Map StringToMap(String string) {
        String one = "";
        String two = "";
        String three = "";
        String four = "";
        String five = "";
        String str = "";
        String strs = "";
        Map<String, String> map = new HashMap<String, String>();
        str = string.substring(4, 6);//只截取了月份
        strs = string.substring(0, 4);//只截取了年
        int number = 0;
        int num = 0;
        number = Integer.parseInt(str);
        num = Integer.parseInt(strs);
        if (number > 9) {//代表了10-11-12月
            if (number == 10) {
                one = num + "08";
                two = num + "09";
                three = num + "10";
                four = num + "11";
                five = num + "12";
            }
            if (number == 11) {
                one = num + "09";
                two = num + "10";
                three = num + "11";
                four = num + "12";
                five = num + 1 + "01";
            }
            if (number == 12) {
                one = num + "09";
                two = num + "11";
                three = num + "12";
                four = num + 1 + "01";
                five = num + 1 + "02";
            }
        } else {//代表了1-9月
            if (number == 1) {
                one = num - 1 + "11";
                two = num - 1 + "12";
                three = num + "01";
                four = num + "02";
                five = num + "03";
            } else if (number == 2) {
                one = num - 1 + "12";
                two = num + "01";
                three = num + "02";
                four = num + "03";
                five = num + "04";
            } else {
                one = num * 100 + number - 2 + "";
                two = num * 100 + number - 1 + "";
                three = num * 100 + number + "";
                four = num * 100 + number + 1 + "";
                five = num * 100 + number + 2 + "";
            }
        }
        map.put("one", one);
        map.put("two", two);
        map.put("three", three);
        map.put("four", four);
        map.put("five", five);
        return map;
    }

    public static LoadForesult toLoadForesult(String [] str) {
        LoadForesult loadForesult=new LoadForesult();
        loadForesult.setF00(new	BigDecimal(str[0]));
        loadForesult.setF01(new	BigDecimal(str[1]));
        loadForesult.setF02(new	BigDecimal(str[2]));
        loadForesult.setF03(new	BigDecimal(str[3]));
        loadForesult.setF04(new	BigDecimal(str[4]));
        loadForesult.setF05(new	BigDecimal(str[5]));
        loadForesult.setF06(new	BigDecimal(str[6]));
        loadForesult.setF07(new	BigDecimal(str[7]));
        loadForesult.setF08(new	BigDecimal(str[8]));
        loadForesult.setF09(new	BigDecimal(str[9]));
        loadForesult.setF10(new	BigDecimal(str[10]));
        loadForesult.setF11(new	BigDecimal(str[11]));
        loadForesult.setF12(new	BigDecimal(str[12]));
        loadForesult.setF13(new	BigDecimal(str[13]));
        loadForesult.setF14(new	BigDecimal(str[14]));
        loadForesult.setF15(new	BigDecimal(str[15]));
        loadForesult.setF16(new	BigDecimal(str[16]));
        loadForesult.setF17(new	BigDecimal(str[17]));
        loadForesult.setF18(new	BigDecimal(str[18]));
        loadForesult.setF19(new	BigDecimal(str[19]));
        loadForesult.setF20(new	BigDecimal(str[20]));
        loadForesult.setF21(new	BigDecimal(str[21]));
        loadForesult.setF22(new	BigDecimal(str[22]));
        loadForesult.setF23(new	BigDecimal(str[23]));
        loadForesult.setF24(new	BigDecimal(str[24]));
        loadForesult.setF25(new	BigDecimal(str[25]));
        loadForesult.setF26(new	BigDecimal(str[26]));
        loadForesult.setF27(new	BigDecimal(str[27]));
        loadForesult.setF28(new	BigDecimal(str[28]));
        loadForesult.setF29(new	BigDecimal(str[29]));
        loadForesult.setF30(new	BigDecimal(str[30]));
        loadForesult.setF31(new	BigDecimal(str[31]));
        loadForesult.setF32(new	BigDecimal(str[32]));
        loadForesult.setF33(new	BigDecimal(str[33]));
        loadForesult.setF34(new	BigDecimal(str[34]));
        loadForesult.setF35(new	BigDecimal(str[35]));
        loadForesult.setF36(new	BigDecimal(str[36]));
        loadForesult.setF37(new	BigDecimal(str[37]));
        loadForesult.setF38(new	BigDecimal(str[38]));
        loadForesult.setF39(new	BigDecimal(str[39]));
        loadForesult.setF40(new	BigDecimal(str[40]));
        loadForesult.setF41(new	BigDecimal(str[41]));
        loadForesult.setF42(new	BigDecimal(str[42]));
        loadForesult.setF43(new	BigDecimal(str[43]));
        loadForesult.setF44(new	BigDecimal(str[44]));
        loadForesult.setF45(new	BigDecimal(str[45]));
        loadForesult.setF46(new	BigDecimal(str[46]));
        loadForesult.setF47(new	BigDecimal(str[47]));
        loadForesult.setF48(new	BigDecimal(str[48]));
        loadForesult.setF49(new	BigDecimal(str[49]));
        loadForesult.setF50(new	BigDecimal(str[50]));
        loadForesult.setF51(new	BigDecimal(str[51]));
        loadForesult.setF52(new	BigDecimal(str[52]));
        loadForesult.setF53(new	BigDecimal(str[53]));
        loadForesult.setF54(new	BigDecimal(str[54]));
        loadForesult.setF55(new	BigDecimal(str[55]));
        loadForesult.setF56(new	BigDecimal(str[56]));
        loadForesult.setF57(new	BigDecimal(str[57]));
        loadForesult.setF58(new	BigDecimal(str[58]));
        loadForesult.setF59(new	BigDecimal(str[59]));
        loadForesult.setF60(new	BigDecimal(str[60]));
        loadForesult.setF61(new	BigDecimal(str[61]));
        loadForesult.setF62(new	BigDecimal(str[62]));
        loadForesult.setF63(new	BigDecimal(str[63]));
        loadForesult.setF64(new	BigDecimal(str[64]));
        loadForesult.setF65(new	BigDecimal(str[65]));
        loadForesult.setF66(new	BigDecimal(str[66]));
        loadForesult.setF67(new	BigDecimal(str[67]));
        loadForesult.setF68(new	BigDecimal(str[68]));
        loadForesult.setF69(new	BigDecimal(str[69]));
        loadForesult.setF70(new	BigDecimal(str[70]));
        loadForesult.setF71(new	BigDecimal(str[71]));
        loadForesult.setF72(new	BigDecimal(str[72]));
        loadForesult.setF73(new	BigDecimal(str[73]));
        loadForesult.setF74(new	BigDecimal(str[74]));
        loadForesult.setF75(new	BigDecimal(str[75]));
        loadForesult.setF76(new	BigDecimal(str[76]));
        loadForesult.setF77(new	BigDecimal(str[77]));
        loadForesult.setF78(new	BigDecimal(str[78]));
        loadForesult.setF79(new	BigDecimal(str[79]));
        loadForesult.setF80(new	BigDecimal(str[80]));
        loadForesult.setF81(new	BigDecimal(str[81]));
        loadForesult.setF82(new	BigDecimal(str[82]));
        loadForesult.setF83(new	BigDecimal(str[83]));
        loadForesult.setF84(new	BigDecimal(str[84]));
        loadForesult.setF85(new	BigDecimal(str[85]));
        loadForesult.setF86(new	BigDecimal(str[86]));
        loadForesult.setF87(new	BigDecimal(str[87]));
        loadForesult.setF88(new	BigDecimal(str[88]));
        loadForesult.setF89(new	BigDecimal(str[89]));
        loadForesult.setF90(new	BigDecimal(str[90]));
        loadForesult.setF91(new	BigDecimal(str[91]));
        loadForesult.setF92(new	BigDecimal(str[92]));
        loadForesult.setF93(new	BigDecimal(str[93]));
        loadForesult.setF94(new	BigDecimal(str[94]));
        loadForesult.setF95(new	BigDecimal(str[95]));
        loadForesult.setF96(new	BigDecimal(str[96]));
        return loadForesult;
    }

    public static String[] toStrings(LoadHisdata loadHisdata) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (loadHisdata!=null){
            map.put("f00", loadHisdata.getF00().toString());
            map.put("f15", loadHisdata.getF15().toString());
            map.put("f30", loadHisdata.getF30().toString());
            map.put("f45", loadHisdata.getF45().toString());
            map.put("f60", loadHisdata.getF60().toString());
            map.put("f75", loadHisdata.getF75().toString());
            map.put("f90", loadHisdata.getF90().toString());
            map.put("f105", loadHisdata.getF105().toString());
            map.put("f120", loadHisdata.getF120().toString());
            map.put("f135", loadHisdata.getF135().toString());
            map.put("f150", loadHisdata.getF150().toString());
            map.put("f165", loadHisdata.getF165().toString());
            map.put("f180", loadHisdata.getF180().toString());
            map.put("f195", loadHisdata.getF195().toString());
            map.put("f210", loadHisdata.getF210().toString());
            map.put("f225", loadHisdata.getF225().toString());
            map.put("f240", loadHisdata.getF240().toString());
            map.put("f255", loadHisdata.getF255().toString());
            map.put("f270", loadHisdata.getF270().toString());
            map.put("f285", loadHisdata.getF285().toString());
            map.put("f300", loadHisdata.getF300().toString());
            map.put("f315", loadHisdata.getF315().toString());
            map.put("f330", loadHisdata.getF330().toString());
            map.put("f345", loadHisdata.getF345().toString());
            map.put("f360", loadHisdata.getF360().toString());
            map.put("f375", loadHisdata.getF375().toString());
            map.put("f390", loadHisdata.getF390().toString());
            map.put("f405", loadHisdata.getF405().toString());
            map.put("f420", loadHisdata.getF420().toString());
            map.put("f435", loadHisdata.getF435().toString());
            map.put("f450", loadHisdata.getF450().toString());
            map.put("f465", loadHisdata.getF465().toString());
            map.put("f480", loadHisdata.getF480().toString());
            map.put("f495", loadHisdata.getF495().toString());
            map.put("f510", loadHisdata.getF510().toString());
            map.put("f525", loadHisdata.getF525().toString());
            map.put("f540", loadHisdata.getF540().toString());
            map.put("f555", loadHisdata.getF555().toString());
            map.put("f570", loadHisdata.getF570().toString());
            map.put("f585", loadHisdata.getF585().toString());
            map.put("f600", loadHisdata.getF600().toString());
            map.put("f615", loadHisdata.getF615().toString());
            map.put("f630", loadHisdata.getF630().toString());
            map.put("f645", loadHisdata.getF645().toString());
            map.put("f660", loadHisdata.getF660().toString());
            map.put("f675", loadHisdata.getF675().toString());
            map.put("f690", loadHisdata.getF690().toString());
            map.put("f705", loadHisdata.getF705().toString());
            map.put("f720", loadHisdata.getF720().toString());
            map.put("f735", loadHisdata.getF735().toString());
            map.put("f750", loadHisdata.getF750().toString());
            map.put("f765", loadHisdata.getF765().toString());
            map.put("f780", loadHisdata.getF780().toString());
            map.put("f795", loadHisdata.getF795().toString());
            map.put("f810", loadHisdata.getF810().toString());
            map.put("f825", loadHisdata.getF825().toString());
            map.put("f840", loadHisdata.getF840().toString());
            map.put("f855", loadHisdata.getF855().toString());
            map.put("f870", loadHisdata.getF870().toString());
            map.put("f885", loadHisdata.getF885().toString());
            map.put("f900", loadHisdata.getF900().toString());
            map.put("f915", loadHisdata.getF915().toString());
            map.put("f930", loadHisdata.getF930().toString());
            map.put("f945", loadHisdata.getF945().toString());
            map.put("f960", loadHisdata.getF960().toString());
            map.put("f975", loadHisdata.getF975().toString());
            map.put("f990", loadHisdata.getF990().toString());
            map.put("f1005", loadHisdata.getF1005().toString());
            map.put("f1020", loadHisdata.getF1020().toString());
            map.put("f1035", loadHisdata.getF1035().toString());
            map.put("f1050", loadHisdata.getF1050().toString());
            map.put("f1065", loadHisdata.getF1065().toString());
            map.put("f1080", loadHisdata.getF1080().toString());
            map.put("f1095", loadHisdata.getF1095().toString());
            map.put("f1110", loadHisdata.getF1110().toString());
            map.put("f1125", loadHisdata.getF1125().toString());
            map.put("f1140", loadHisdata.getF1140().toString());
            map.put("f1155", loadHisdata.getF1155().toString());
            map.put("f1170", loadHisdata.getF1170().toString());
            map.put("f1185", loadHisdata.getF1185().toString());
            map.put("f1200", loadHisdata.getF1200().toString());
            map.put("f1215", loadHisdata.getF1215().toString());
            map.put("f1230", loadHisdata.getF1230().toString());
            map.put("f1245", loadHisdata.getF1245().toString());
            map.put("f1260", loadHisdata.getF1260().toString());
            map.put("f1275", loadHisdata.getF1275().toString());
            map.put("f1290", loadHisdata.getF1290().toString());
            map.put("f1305", loadHisdata.getF1305().toString());
            map.put("f1320", loadHisdata.getF1320().toString());
            map.put("f1335", loadHisdata.getF1335().toString());
            map.put("f1350", loadHisdata.getF1350().toString());
            map.put("f1365", loadHisdata.getF1365().toString());
            map.put("f1380", loadHisdata.getF1380().toString());
            map.put("f1395", loadHisdata.getF1395().toString());
            map.put("f1410", loadHisdata.getF1410().toString());
            map.put("f1425", loadHisdata.getF1425().toString());
            map.put("f1440", loadHisdata.getF1440().toString());
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

    /**
     * 典型工作日和休息日的96点获得
     *
     * @param loadMonthtypeload96
     * @return
     */
    public static String[] toStringDianXing(LoadMonthtypeload96 loadMonthtypeload96) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if ( loadMonthtypeload96.getF00()==null){	    map.put("f00"	,0+"");}else {  	  map.put("f00",	loadMonthtypeload96.getF00().toString());}
        if ( loadMonthtypeload96.getF01()==null){	    map.put("f01"	,0+"");}else {  	  map.put("f01",	loadMonthtypeload96.getF01().toString());}
        if ( loadMonthtypeload96.getF02()==null){	    map.put("f02"	,0+"");}else {	  map.put("f02",	loadMonthtypeload96.getF02().toString());}
        if ( loadMonthtypeload96.getF03()==null){	    map.put("f03"	,0+"");}else {	  map.put("f03",	loadMonthtypeload96.getF03().toString());}
        if ( loadMonthtypeload96.getF04()==null){	    map.put("f04"	,0+"");}else {	  map.put("f04",	loadMonthtypeload96.getF04().toString());}
        if ( loadMonthtypeload96.getF05()==null){	    map.put("f05"	,0+"");}else {	  map.put("f05",	loadMonthtypeload96.getF05().toString());}
        if ( loadMonthtypeload96.getF06()==null){	    map.put("f06"	,0+"");}else {	  map.put("f06",	loadMonthtypeload96.getF06().toString());}
        if ( loadMonthtypeload96.getF07()==null){	    map.put("f07"	,0+"");}else {	  map.put("f07",	loadMonthtypeload96.getF07().toString());}
        if ( loadMonthtypeload96.getF08()==null){	    map.put("f08"	,0+"");}else {	  map.put("f08",	loadMonthtypeload96.getF08().toString());}
        if ( loadMonthtypeload96.getF09()==null){	    map.put("f09"	,0+"");}else {	  map.put("f09",	loadMonthtypeload96.getF09().toString());}
        if ( loadMonthtypeload96.getF10()==null){	    map.put("f10"	,0+"");}else {	  map.put("f10",	loadMonthtypeload96.getF10().toString());}
        if ( loadMonthtypeload96.getF11()==null){	    map.put("f11"	,0+"");}else {	  map.put("f11",	loadMonthtypeload96.getF11().toString());}
        if ( loadMonthtypeload96.getF12()==null){	    map.put("f12"	,0+"");}else {	  map.put("f12",	loadMonthtypeload96.getF12().toString());}
        if ( loadMonthtypeload96.getF13()==null){	    map.put("f13"	,0+"");}else {	  map.put("f13",	loadMonthtypeload96.getF13().toString());}
        if ( loadMonthtypeload96.getF14()==null){	    map.put("f14"	,0+"");}else {	  map.put("f14",	loadMonthtypeload96.getF14().toString());}
        if ( loadMonthtypeload96.getF15()==null){	    map.put("f15"	,0+"");}else {	  map.put("f15",	loadMonthtypeload96.getF15().toString());}
        if ( loadMonthtypeload96.getF16()==null){	    map.put("f16"	,0+"");}else {	  map.put("f16",	loadMonthtypeload96.getF16().toString());}
        if ( loadMonthtypeload96.getF17()==null){	    map.put("f17"	,0+"");}else {	  map.put("f17",	loadMonthtypeload96.getF17().toString());}
        if ( loadMonthtypeload96.getF18()==null){	    map.put("f18"	,0+"");}else {	  map.put("f18",	loadMonthtypeload96.getF18().toString());}
        if ( loadMonthtypeload96.getF19()==null){	    map.put("f19"	,0+"");}else {	  map.put("f19",	loadMonthtypeload96.getF19().toString());}
        if ( loadMonthtypeload96.getF20()==null){	    map.put("f20"	,0+"");}else {	  map.put("f20",	loadMonthtypeload96.getF20().toString());}
        if ( loadMonthtypeload96.getF21()==null){	    map.put("f21"	,0+"");}else {	  map.put("f21",	loadMonthtypeload96.getF21().toString());}
        if ( loadMonthtypeload96.getF22()==null){	    map.put("f22"	,0+"");}else {	  map.put("f22",	loadMonthtypeload96.getF22().toString());}
        if ( loadMonthtypeload96.getF23()==null){	    map.put("f23"	,0+"");}else {	  map.put("f23",	loadMonthtypeload96.getF23().toString());}
        if ( loadMonthtypeload96.getF24()==null){	    map.put("f24"	,0+"");}else {	  map.put("f24",	loadMonthtypeload96.getF24().toString());}
        if ( loadMonthtypeload96.getF25()==null){	    map.put("f25"	,0+"");}else {	  map.put("f25",	loadMonthtypeload96.getF25().toString());}
        if ( loadMonthtypeload96.getF26()==null){	    map.put("f26"	,0+"");}else {	  map.put("f26",	loadMonthtypeload96.getF26().toString());}
        if ( loadMonthtypeload96.getF27()==null){	    map.put("f27"	,0+"");}else {	  map.put("f27",	loadMonthtypeload96.getF27().toString());}
        if ( loadMonthtypeload96.getF28()==null){	    map.put("f28"	,0+"");}else {	  map.put("f28",	loadMonthtypeload96.getF28().toString());}
        if ( loadMonthtypeload96.getF29()==null){	    map.put("f29"	,0+"");}else {	  map.put("f29",	loadMonthtypeload96.getF29().toString());}
        if ( loadMonthtypeload96.getF30()==null){	    map.put("f30"	,0+"");}else {	  map.put("f30",	loadMonthtypeload96.getF30().toString());}
        if ( loadMonthtypeload96.getF31()==null){	    map.put("f31"	,0+"");}else {	  map.put("f31",	loadMonthtypeload96.getF31().toString());}
        if ( loadMonthtypeload96.getF32()==null){	    map.put("f32"	,0+"");}else {	  map.put("f32",	loadMonthtypeload96.getF32().toString());}
        if ( loadMonthtypeload96.getF33()==null){	    map.put("f33"	,0+"");}else {	  map.put("f33",	loadMonthtypeload96.getF33().toString());}
        if ( loadMonthtypeload96.getF34()==null){	    map.put("f34"	,0+"");}else {	  map.put("f34",	loadMonthtypeload96.getF34().toString());}
        if ( loadMonthtypeload96.getF35()==null){	    map.put("f35"	,0+"");}else {	  map.put("f35",	loadMonthtypeload96.getF35().toString());}
        if ( loadMonthtypeload96.getF36()==null){	    map.put("f36"	,0+"");}else {	  map.put("f36",	loadMonthtypeload96.getF36().toString());}
        if ( loadMonthtypeload96.getF37()==null){	    map.put("f37"	,0+"");}else {	  map.put("f37",	loadMonthtypeload96.getF37().toString());}
        if ( loadMonthtypeload96.getF38()==null){	    map.put("f38"	,0+"");}else {	  map.put("f38",	loadMonthtypeload96.getF38().toString());}
        if ( loadMonthtypeload96.getF39()==null){	    map.put("f39"	,0+"");}else {	  map.put("f39",	loadMonthtypeload96.getF39().toString());}
        if ( loadMonthtypeload96.getF40()==null){	    map.put("f40"	,0+"");}else {	  map.put("f40",	loadMonthtypeload96.getF40().toString());}
        if ( loadMonthtypeload96.getF41()==null){	    map.put("f41"	,0+"");}else {	  map.put("f41",	loadMonthtypeload96.getF41().toString());}
        if ( loadMonthtypeload96.getF42()==null){	    map.put("f42"	,0+"");}else {	  map.put("f42",	loadMonthtypeload96.getF42().toString());}
        if ( loadMonthtypeload96.getF43()==null){	    map.put("f43"	,0+"");}else {	  map.put("f43",	loadMonthtypeload96.getF43().toString());}
        if ( loadMonthtypeload96.getF44()==null){	    map.put("f44"	,0+"");}else {	  map.put("f44",	loadMonthtypeload96.getF44().toString());}
        if ( loadMonthtypeload96.getF45()==null){	    map.put("f45"	,0+"");}else {	  map.put("f45",	loadMonthtypeload96.getF45().toString());}
        if ( loadMonthtypeload96.getF46()==null){	    map.put("f46"	,0+"");}else {	  map.put("f46",	loadMonthtypeload96.getF46().toString());}
        if ( loadMonthtypeload96.getF47()==null){	    map.put("f47"	,0+"");}else {	  map.put("f47",	loadMonthtypeload96.getF47().toString());}
        if ( loadMonthtypeload96.getF48()==null){	    map.put("f48"	,0+"");}else {	  map.put("f48",	loadMonthtypeload96.getF48().toString());}
        if ( loadMonthtypeload96.getF49()==null){	    map.put("f49"	,0+"");}else {	  map.put("f49",	loadMonthtypeload96.getF49().toString());}
        if ( loadMonthtypeload96.getF50()==null){	    map.put("f50"	,0+"");}else {	  map.put("f50",	loadMonthtypeload96.getF50().toString());}
        if ( loadMonthtypeload96.getF51()==null){	    map.put("f51"	,0+"");}else {	  map.put("f51",	loadMonthtypeload96.getF51().toString());}
        if ( loadMonthtypeload96.getF52()==null){	    map.put("f52"	,0+"");}else {	  map.put("f52",	loadMonthtypeload96.getF52().toString());}
        if ( loadMonthtypeload96.getF53()==null){	    map.put("f53"	,0+"");}else {	  map.put("f53",	loadMonthtypeload96.getF53().toString());}
        if ( loadMonthtypeload96.getF54()==null){	    map.put("f54"	,0+"");}else {	  map.put("f54",	loadMonthtypeload96.getF54().toString());}
        if ( loadMonthtypeload96.getF55()==null){	    map.put("f55"	,0+"");}else {	  map.put("f55",	loadMonthtypeload96.getF55().toString());}
        if ( loadMonthtypeload96.getF56()==null){	    map.put("f56"	,0+"");}else {	  map.put("f56",	loadMonthtypeload96.getF56().toString());}
        if ( loadMonthtypeload96.getF57()==null){	    map.put("f57"	,0+"");}else {	  map.put("f57",	loadMonthtypeload96.getF57().toString());}
        if ( loadMonthtypeload96.getF58()==null){	    map.put("f58"	,0+"");}else {	  map.put("f58",	loadMonthtypeload96.getF58().toString());}
        if ( loadMonthtypeload96.getF59()==null){	    map.put("f59"	,0+"");}else {	  map.put("f59",	loadMonthtypeload96.getF59().toString());}
        if ( loadMonthtypeload96.getF60()==null){	    map.put("f60"	,0+"");}else {	  map.put("f60",	loadMonthtypeload96.getF60().toString());}
        if ( loadMonthtypeload96.getF61()==null){	    map.put("f61"	,0+"");}else {	  map.put("f61",	loadMonthtypeload96.getF61().toString());}
        if ( loadMonthtypeload96.getF62()==null){	    map.put("f62"	,0+"");}else {	  map.put("f62",	loadMonthtypeload96.getF62().toString());}
        if ( loadMonthtypeload96.getF63()==null){	    map.put("f63"	,0+"");}else {	  map.put("f63",	loadMonthtypeload96.getF63().toString());}
        if ( loadMonthtypeload96.getF64()==null){	    map.put("f64"	,0+"");}else {	  map.put("f64",	loadMonthtypeload96.getF64().toString());}
        if ( loadMonthtypeload96.getF65()==null){	    map.put("f65"	,0+"");}else {	  map.put("f65",	loadMonthtypeload96.getF65().toString());}
        if ( loadMonthtypeload96.getF66()==null){	    map.put("f66"	,0+"");}else {	  map.put("f66",	loadMonthtypeload96.getF66().toString());}
        if ( loadMonthtypeload96.getF67()==null){	    map.put("f67"	,0+"");}else {	  map.put("f67",	loadMonthtypeload96.getF67().toString());}
        if ( loadMonthtypeload96.getF68()==null){	    map.put("f68"	,0+"");}else {	  map.put("f68",	loadMonthtypeload96.getF68().toString());}
        if ( loadMonthtypeload96.getF69()==null){	    map.put("f69"	,0+"");}else {	  map.put("f69",	loadMonthtypeload96.getF69().toString());}
        if ( loadMonthtypeload96.getF70()==null){	    map.put("f70"	,0+"");}else {	  map.put("f70",	loadMonthtypeload96.getF70().toString());}
        if ( loadMonthtypeload96.getF71()==null){	    map.put("f71"	,0+"");}else {	  map.put("f71",	loadMonthtypeload96.getF71().toString());}
        if ( loadMonthtypeload96.getF72()==null){	    map.put("f72"	,0+"");}else {	  map.put("f72",	loadMonthtypeload96.getF72().toString());}
        if ( loadMonthtypeload96.getF73()==null){	    map.put("f73"	,0+"");}else {	  map.put("f73",	loadMonthtypeload96.getF73().toString());}
        if ( loadMonthtypeload96.getF74()==null){	    map.put("f74"	,0+"");}else {	  map.put("f74",	loadMonthtypeload96.getF74().toString());}
        if ( loadMonthtypeload96.getF75()==null){	    map.put("f75"	,0+"");}else {	  map.put("f75",	loadMonthtypeload96.getF75().toString());}
        if ( loadMonthtypeload96.getF76()==null){	    map.put("f76"	,0+"");}else {	  map.put("f76",	loadMonthtypeload96.getF76().toString());}
        if ( loadMonthtypeload96.getF77()==null){	    map.put("f77"	,0+"");}else {	  map.put("f77",	loadMonthtypeload96.getF77().toString());}
        if ( loadMonthtypeload96.getF78()==null){	    map.put("f78"	,0+"");}else {	  map.put("f78",	loadMonthtypeload96.getF78().toString());}
        if ( loadMonthtypeload96.getF79()==null){	    map.put("f79"	,0+"");}else {	  map.put("f79",	loadMonthtypeload96.getF79().toString());}
        if ( loadMonthtypeload96.getF80()==null){	    map.put("f80"	,0+"");}else {	  map.put("f80",	loadMonthtypeload96.getF80().toString());}
        if ( loadMonthtypeload96.getF81()==null){	    map.put("f81"	,0+"");}else {	  map.put("f81",	loadMonthtypeload96.getF81().toString());}
        if ( loadMonthtypeload96.getF82()==null){	    map.put("f82"	,0+"");}else {	  map.put("f82",	loadMonthtypeload96.getF82().toString());}
        if ( loadMonthtypeload96.getF83()==null){	    map.put("f83"	,0+"");}else {	  map.put("f83",	loadMonthtypeload96.getF83().toString());}
        if ( loadMonthtypeload96.getF84()==null){	    map.put("f84"	,0+"");}else {	  map.put("f84",	loadMonthtypeload96.getF84().toString());}
        if ( loadMonthtypeload96.getF85()==null){	    map.put("f85"	,0+"");}else {	  map.put("f85",	loadMonthtypeload96.getF85().toString());}
        if ( loadMonthtypeload96.getF86()==null){	    map.put("f86"	,0+"");}else {	  map.put("f86",	loadMonthtypeload96.getF86().toString());}
        if ( loadMonthtypeload96.getF87()==null){	    map.put("f87"	,0+"");}else {	  map.put("f87",	loadMonthtypeload96.getF87().toString());}
        if ( loadMonthtypeload96.getF88()==null){	    map.put("f88"	,0+"");}else {	  map.put("f88",	loadMonthtypeload96.getF88().toString());}
        if ( loadMonthtypeload96.getF89()==null){	    map.put("f89"	,0+"");}else {	  map.put("f89",	loadMonthtypeload96.getF89().toString());}
        if ( loadMonthtypeload96.getF90()==null){	    map.put("f90"	,0+"");}else {	  map.put("f90",	loadMonthtypeload96.getF90().toString());}
        if ( loadMonthtypeload96.getF91()==null){	    map.put("f91"	,0+"");}else {	  map.put("f91",	loadMonthtypeload96.getF91().toString());}
        if ( loadMonthtypeload96.getF92()==null){	    map.put("f92"	,0+"");}else {	  map.put("f92",	loadMonthtypeload96.getF92().toString());}
        if ( loadMonthtypeload96.getF93()==null){	    map.put("f93"	,0+"");}else {	  map.put("f93",	loadMonthtypeload96.getF93().toString());}
        if ( loadMonthtypeload96.getF94()==null){	    map.put("f94"	,0+"");}else {	  map.put("f94",	loadMonthtypeload96.getF94().toString());}
        if ( loadMonthtypeload96.getF95()==null){	    map.put("f95"	,0+"");}else {	  map.put("f95",	loadMonthtypeload96.getF95().toString());}
        if ( loadMonthtypeload96.getF96()==null){	    map.put("f96"	,0+"");}else {	  map.put("f96",	loadMonthtypeload96.getF96().toString());}


        String[] strings = new String[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            strings[i] = map.get(key);
            i++;
        }
        return strings;
    }

    public static String[] jihe96() {
        String[] jiHe = {
                "00:00", "00:15", "00:30", "00:45",
                "01:00", "01:15", "01:30", "01:45",
                "02:00", "02:15", "02:30", "02:45",
                "03:00", "03:15", "03:30", "03:45",
                "04:00", "04:15", "04:30", "04:45",
                "05:00", "05:15", "05:30", "05:45",
                "06:00", "06:15", "06:30", "06:45",
                "07:00", "07:15", "07:30", "07:45",
                "08:00", "08:15", "08:30", "08:45",
                "09:00", "09:15", "09:30", "09:45",
                "10:00", "10:15", "10:30", "10:45",
                "11:00", "11:15", "11:30", "11:45",
                "12:00", "12:15", "12:30", "12:45",
                "13:00", "13:15", "13:30", "13:45",
                "14:00", "14:15", "14:30", "14:45",
                "15:00", "15:15", "15:30", "15:45",
                "16:00", "16:15", "16:30", "16:45",
                "17:00", "17:15", "17:30", "17:45",
                "18:00", "18:15", "18:30", "18:45",
                "19:00", "19:15", "19:30", "19:45",
                "20:00", "20:15", "20:30", "20:45",
                "21:00", "21:15", "21:30", "21:45",
                "22:00", "22:15", "22:30", "22:45",
                "23:00", "23:15", "23:30", "23:45",
                "00:00"
        };
        return jiHe;
    }

}
