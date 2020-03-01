package com.dky.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: YangSL
 * @Description: 将系统中常用的日期格式化转为常量，减少系统垃圾得产生
 * @Date: Create in 15:27 2017/12/19
 */
public class DateUtils {

    //使每个线程拥有自己得SimpleDateFormat对象副本（多线程安全考虑）
    private static ThreadLocal<SimpleDateFormat> local_ymdh = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> local_ymdn = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> local_mdh = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> local_ymdhmsh = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> local_hm = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> local_y = new ThreadLocal<>();

    //常量区，内容为基础格式常量
    public static final String YMDH_P = "yyyy-MM-dd";
    public static final String YMDN_P = "yyyyMMdd";
    public static final String MDH_P = "MM-dd";
    public static final String YMDHMSH_P = "yyyy-MM-dd HH:mm:ss";
    public static final String HM_P = "HH:mm";
    public static final String Y_P = "yyyy";

    //时间格式化类（线程不安全），不推荐
    public static final SimpleDateFormat YMDH_FMT = new SimpleDateFormat(YMDH_P);
    public static final SimpleDateFormat YMDN_FMT = new SimpleDateFormat(YMDN_P);
    public static final SimpleDateFormat MDH_FMT = new SimpleDateFormat(MDH_P);
    public static final SimpleDateFormat YMDHMSH_FMT = new SimpleDateFormat(YMDHMSH_P);
    public static final SimpleDateFormat HM_FMT = new SimpleDateFormat(HM_P);
    public static final SimpleDateFormat Y_FMT = new SimpleDateFormat(Y_P);
    private DateUtils() {
    }

    /**
     * 时间字符串根据相应格式转换成时间格式（线程安全）
     * @param str 时间字符串
     * @param pattern 格式
     * @return 时间类型
     */
    public static Date parse(String str, String pattern) throws ParseException {
        ThreadLocal<SimpleDateFormat> local = switchThreadLocal(pattern);
        SimpleDateFormat sdf = local.get();
        if (sdf == null){
            sdf = new SimpleDateFormat(pattern);
            local.set(sdf);
        }
        return sdf.parse(str);
    }

    /**
     * 时间格式根据相应根式转换成字符串格式（线程安全）
     * @param date 时间类型参数
     * @param pattern 格式
     * @return 相应格式得时间字符串
     */
    public static String format(Date date, String pattern) {
        ThreadLocal<SimpleDateFormat> local = switchThreadLocal(pattern);
        SimpleDateFormat sdf = local.get();
        if (sdf == null){
            sdf = new SimpleDateFormat(pattern);
            local.set(sdf);
        }
        return sdf.format(date);
    }

    /**
     * 计算某个日期前30天得日期
     * @param str 时间字符串（格式：yyyyMMdd）
     * @return 返回30天前（格式：yyyyMMdd）
     * @throws ParseException
     */
    public static String findLastThirtyDayMdate(String str) throws ParseException {
        long now = parse(str,YMDN_P).getTime();
        long then = now - 2592000000L;
        return format(new Date(then),YMDN_P);
    }

    /**
     * 将“yyyyMMdd”格式字符串转换为“yyyy-MM-dd”格式字符串
     * @param str 时间字符串（格式：yyyyMMdd）
     * @return 返回时间字符串（格式：yyyy-MM-dd）
     * @throws ParseException 类型转换异常
     */
    public static String nToH(String str) throws ParseException {
        return format(parse(str,YMDN_P),YMDH_P);
    }

    /**
     * 将“yyyy-MM-dd”格式字符串转换为“yyyyMMdd”格式字符串
     * @param str 时间字符串（格式：yyyy-MM-dd）
     * @return 返回时间字符串（格式：yyyyMMdd）
     * @throws ParseException 类型转换异常
     */
    public static String hToN(String str) throws ParseException {
        return format(parse(str,YMDH_P),YMDN_P);
    }

    /**
     * 传入两个时间类型对象，计算相差天数
     * @param date1 Date类型参数
     * @param date2 Date类型参数
     * @return 返回相差天数
     */
    public static long compareByDates(Date date1,Date date2){
        long value1 = date1.getTime();
        long value2 = date2.getTime();
        long differ = Math.abs(value1-value2);
        return differ/(86400000);
    }

    /**
     * 通过传入给定格式得两个时间字符串，计算两个时间相差几天
     * @param str1 时间字符串（格式：yyyyMMdd）
     * @param str2 时间字符串（格式：yyyyMMdd）
     * @return 返回相差得天数
     * @throws ParseException 格式转换异常
     */
    public static long compareByYMDN(String str1,String str2) throws ParseException {
        long value1 = parse(str1, YMDN_P).getTime();
        long value2 = parse(str2, YMDN_P).getTime();
        long differ = Math.abs(value1-value2);
        return differ/(86400000);
    }

    /**
     * 通过传入得时间格式，返回对应得sdf得本地线程对象
     * @param pattern 时间格式
     * @return 本地线程对象
     */
    private static ThreadLocal<SimpleDateFormat> switchThreadLocal(String pattern) {
       switch (pattern){
            case YMDH_P:
                return local_ymdh;
            case YMDN_P:
                return local_ymdn;
            case MDH_P:
                return local_mdh;
            case YMDHMSH_P:
                return local_ymdhmsh;
            case HM_P:
                return local_hm;
            case Y_P:
                return local_y;
            default:
                return new ThreadLocal<>();
        }
    }
}
