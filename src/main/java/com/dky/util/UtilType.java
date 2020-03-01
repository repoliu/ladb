package com.dky.util;

public class UtilType {
    /**
     * 1代表了月典型工作日（表中的daytype字段）
     * 0代表了月典型休息日（表中的daytype字段）
     */
    public static final Integer MONTH_ONE=1;
    public static final Integer MONTH_TWO=0;


    /**
     * 计算进度条百分比的基数30000,由于计算百分比除以30000还要在乘以100，所有这个直接设置成300，不乘以100了
     */
    public static final Double PROGRESS_NUMBER_DOUBLE=new Double(300);

    /**
     *
     * 删除的时候用的模式
     */
    public static  final String MODEL="loadfor";

}
