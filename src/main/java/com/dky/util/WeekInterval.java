package com.dky.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author liuhaijian
 * @date Create in 2017/10/28
 */
public class WeekInterval {
    //本方法计算下两周，下一周，本周，上一周、上两周的日期区间
    public  String[][] getFiveWeekInterval(Date date) {
        String[][] weekInterval=new String[5][2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

        //下两周
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 20);
        String nextWeekEnd= DateUtils.format(cal.getTime(),DateUtils.YMDN_P);
        cal.add(Calendar.DATE, -6);
        String nextWeekBegin= DateUtils.format(cal.getTime(),DateUtils.YMDN_P);
        weekInterval[0][0]=nextWeekBegin;
        weekInterval[0][1]=nextWeekEnd;
        //System.out.println("下两周" +nextWeekBegin+"_"+ nextWeekEnd);
        for(int i=1;i<weekInterval.length;i++){
            //本周
            cal.add(Calendar.DATE, -1);
            String imptimeEnd = DateUtils.format(cal.getTime(),DateUtils.YMDN_P);
            weekInterval[i][1]=imptimeEnd;
            cal.add(Calendar.DATE,-6 );
            String imptimeBegin = DateUtils.format(cal.getTime(),DateUtils.YMDN_P);
            weekInterval[i][0]=imptimeBegin;
            //System.out.println("输出区间："+imptimeBegin+"_"+imptimeEnd);
        }

        return weekInterval;
    }



}
