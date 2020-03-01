package com.dky.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhaijian
 * @date Create in 2017/10/31
 */
public class ArrayOperation {

    //将传入的数组转化为带括号的字符串，用于sql查询中
    public static String arrayToField(String[] array){
        String field="";
        if(array!=null&&array.length>0){
            for(int i=0;i<array.length;i++){
                field+=array[i]+",";
            }
            field="("+field.substring(0,field.lastIndexOf(","))+")";
        }else{
            field="('')";
        }
        return field;
    }

    /*
	 * location代表是数组的某个下标，
	 * 从下标0比较到下标location;
	 * 从数组中取出最大值
	 * */
    public static Map<String,BigDecimal> getMaxValue(String[] args,int start,int end){
        Map<String,BigDecimal> map=new HashMap<>();
        if(args.length>0){
            BigDecimal max=new BigDecimal(args[0]);
            int index=start;
            for(int i=start;i<end;i++){
                if(new BigDecimal(args[i]).compareTo(max)==1){
                    max=new BigDecimal(args[i]);
                    index=i;
                }
            }
            map.put("value",max);
        }
        return map;
    }

    // 从数组中取出最大值下标
    public static Map<String,Integer> getMaxValIndex(String[] args,int start,int end){
        Map<String,Integer> map=new HashMap<>();
        if(args.length>0){
            BigDecimal max=new BigDecimal(args[0]);
            int index=start;
            for(int i=start;i<end;i++){
                if(new BigDecimal(args[i]).compareTo(max)==1){
                    max=new BigDecimal(args[i]);
                    index=i;
                }

            }
            map.put("index",index);
        }
        return map;
    }

//===============================================================

    /*
     * location代表是数组的某个下标，
     * 从下标0比较到下标location;
     * 从数组中取出最小值
     * */
    public static Map<String,BigDecimal> getMinValue(String[] args, int start,int end){
        Map<String,BigDecimal> map=new HashMap<>();
        if(args.length>0){
            BigDecimal min=new BigDecimal(args[0]);
            int index=0;
            for(int i=start;i<end;i++){
                if(new BigDecimal(args[i]).compareTo(min)==-1){
                    min=new BigDecimal(args[i]);
                    index=i;
                }
            }
            map.put("value",min);
        }
        return map;
    }
//从数组中取出最小值下标
    public static Map<String,Integer> getMinValIndex(String[] args, int start,int end){
        Map<String,Integer> map=new HashMap<>();
        if(args.length>0){
            BigDecimal min=new BigDecimal(args[0]);
            int index=start;
            for(int i=start;i<end;i++){
                if(new BigDecimal(args[i]).compareTo(min)==-1){
                    min=new BigDecimal(args[i]);
                    index=i;
                }
            }
            map.put("index",index);
        }
        return map;
    }


}
