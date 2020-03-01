package com.dky.util.forecast;

public class DygraphsTableUtil {



    public static final int  TITLEOne=140;// 预测负荷的表格第一列的宽度
    public static final int  TITLE=100;// 预测负荷的表格其他列的宽度
    public static final String   ALIGN="center";// 预测负荷的表格中数据剧中
    public static final boolean   SORT=true;// 预测负荷的表格数据后有个上下点击的按钮
    public static final Double     MAXNUMBER  =1.3;
    public static final Double     MAX  =30000.0;

    public static final String     FIXED="left";//确定不能动的那一列或者几列
    public static final String     TYPE="numbers";//选定的这样行拖动滚动条的时候不动

    public static final String     YUCE=":预测";// 表格显示时间的时候加上这个是代表是预测曲线
    public static final String     SHIJI=":实际";// 表格显示时间的时候加上这个是代表是实际曲线


    public static final String    UNRESIZE="true";//是否禁用拖拽列宽（默认：false）。默认情况下会根据列类型（type）来决定是否禁用，
                                              //如复选框列，会自动禁用。而其它普通列，默认允许拖拽列宽，当然你也可以设置 true 来禁用该功能。







}
