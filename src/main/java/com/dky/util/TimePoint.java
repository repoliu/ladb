package com.dky.util;

import com.dky.entity.vo.TimeSelectButtonOfDayLoad;
import com.dky.entity.vo.TimeSelectButtonOfYearLoad;

import java.text.ParseException;
import java.util.*;

/**
 * @author liuhaijian
 * @date Create in 2017/10/24
 */
public class TimePoint {
    //生成96，288个时间点的方法
    public static String[] timeCreate(Integer point) {
        String[] result = new String[point+1];
        int temp = 60*24/point;
        int count = 0;
        for(int i = 0;i<24;i++){
            for (int j = 0;j < 60;j=j + temp){
                if ( i < 10) {
                    if ( j < 10 ) {
                        result[count] = "0" + i + ":" + "0" + j;
                    } else {
                        result[count] = "0" + i + ":" + j;
                    }
                } else {
                    if ( j < 10 ) {
                        result[count] = i + ":" + "0" + j;
                    } else {
                        result[count] = i + ":" + j;
                    }
                }
                count++;
            }
        }
        result[count] = "24:00";
        return result;
    }

    /**
     * 生成多日按钮的工具方法
     * @param mdate 传入的日期
     * @return 返回多日按钮list集合
     * @throws ParseException 类型转换异常，基本不会出现
     */
    public static List<TimeSelectButtonOfDayLoad> createTimeButtonValue(String mdate) throws ParseException {
        List<TimeSelectButtonOfDayLoad> list = new ArrayList<>();
        long beginTime = DateUtils.parse(mdate,DateUtils.YMDN_P).getTime();
        long a = 86400000;
        for(int i = 3;i > 0;i--){
            long tempLong = beginTime-a*i;
            String tempYear = DateUtils.format(new Date(tempLong),DateUtils.YMDH_P);
            String tempMonth = DateUtils.format(new Date(tempLong),DateUtils.MDH_P);
            TimeSelectButtonOfDayLoad temp = new TimeSelectButtonOfDayLoad(tempLong,tempYear,tempMonth);
            list.add(temp);
        }
        for(int i = 0;i < 4;i++){
            long tempLong = beginTime+a*i;
            String tempYear = DateUtils.format(new Date(tempLong),DateUtils.YMDH_P);
            String tempMonth = DateUtils.format(new Date(tempLong),DateUtils.MDH_P);
            TimeSelectButtonOfDayLoad temp = new TimeSelectButtonOfDayLoad(tempLong,tempYear,tempMonth);
            list.add(temp);
        }
        return list;
    }

    /**
     * 生成多年按钮的工具方法
     * @param year 初始年
     * @return 返回多年的list集合
     * @throws ParseException 转换异常，基本不会出现
     */
    public static List<TimeSelectButtonOfYearLoad> createTimeButtonValueForYear(String year) throws ParseException {
        List<TimeSelectButtonOfYearLoad> list = new ArrayList<>();
        for(int i = 3;i > 0;i--){
            Integer yearTemp = Integer.valueOf(year) - i;
            long tempLong = DateUtils.parse(yearTemp+"-12-31",DateUtils.YMDH_P).getTime();
            String tempYearTime = DateUtils.format(new Date(tempLong),DateUtils.YMDH_P);
            String tempYear = yearTemp.toString();
            TimeSelectButtonOfYearLoad temp = new TimeSelectButtonOfYearLoad(tempLong,tempYearTime,tempYear);
            list.add(temp);
        }
        for(int i = 0;i < 4;i++){
            Integer yearTemp = Integer.valueOf(year) + i;
            long tempLong = DateUtils.parse(yearTemp+"-01-01",DateUtils.YMDH_P).getTime();
            String tempYearTime = DateUtils.format(new Date(tempLong),DateUtils.YMDH_P);
            String tempYear = yearTemp.toString();
            TimeSelectButtonOfYearLoad temp = new TimeSelectButtonOfYearLoad(tempLong,tempYearTime,tempYear);
            list.add(temp);
        }
        return list;
    }

    /**
     * 通过传入一个date日期，返回此日期的00：00分和下一天的00：00分，主要用于查找某一天的数据
     * @param date 传入某一天的日期
     * @return 返回当天的00：00分和下一天的00：00分
     * @throws ParseException 转换异常，基本不会出现
     */
    public static Map<String,String> timeSelectForWeatherRecord(Date date) throws ParseException {
        Map<String,String> result = new HashMap<>();
        String start = DateUtils.format(date,DateUtils.YMDH_P);
        long a = DateUtils.parse(start,DateUtils.YMDH_P).getTime();
        long b = a + 86400000L-1000;
        Date ad = new Date(a);
        Date bd = new Date(b);
        String as = DateUtils.format(ad,DateUtils.YMDHMSH_P);
        String bs = DateUtils.format(bd,DateUtils.YMDHMSH_P);
        result.put("startTime",as);
        result.put("endTime",bs);
        return result;
    }

    /*
     * 求出曲线图中某个点代表的时间点是多少，i是某个点，point代表曲线图有多少点
     */
    public static String createTimeByPoint(int i ,int point) {
        int a = point/24;
        int b = 60/a;
        int h = i/a;
        int s = (i%a)*b;
        StringBuilder sb = new StringBuilder();
        if (h<10) {
            sb.append("0");
        }
        sb.append(h).append(":");
        if (s<10) {
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }

    public static String[] create96XAxisByDays(int days) {
        String[] result = new String[days*96];
        int index = 0;
        for (int i = 1; i < days+1;i++){
            for (int j = 0; j < 24 ;j++){
                for (int z = 0;z < 4 ; z++){
                    if (j == 0 && z == 0){
                        result[index++] = "第" + i + "天 00:00";
                    } else if (j < 10 ){
                        if (z == 0){
                            result[index++] = "第" + i + "天 0"+ j + ":00";
                        } else {
                            result[index++] = "第" + i + "天 0"+j + ":" + (z*15);
                        }
                    } else {
                        if (z == 0){
                            result[index++] ="第" + i + "天"+ j + ":00";
                        } else {
                            result[index++] = "第" + i + "天"+j + ":" + (z*15);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String[] createDaysXAxisByDays(int size) {
        String[] result = new String[size];
        for (int i = 1; i < size+1;i++){
            result[i-1] = "第"+ i + "天";
        }
        return result;
    }
}
