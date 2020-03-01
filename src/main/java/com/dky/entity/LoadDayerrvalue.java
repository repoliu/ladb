package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadDayerrvalue implements Serializable {

    private int area;//地区标识符
    private String areaname;//地区名称
    private String forday;//时间 格式20180808
    private String maxtime;//最大误差出现时间
    private BigDecimal maxErr;//最大误差
    private BigDecimal aveRate;//准确率
    private BigDecimal peakErr;//尖峰准确率
    private BigDecimal valeyErr;//低谷准确率

    private static final long serialVersionUID = 1L;


    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getForday() {
        return forday;
    }

    public void setForday(String forday) {
        this.forday = forday;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getMaxErr() {
        return maxErr;
    }

    public void setMaxErr(BigDecimal maxErr) {
        this.maxErr = maxErr;
    }

    public BigDecimal getAveRate() {
        return aveRate;
    }

    public void setAveRate(BigDecimal aveRate) {
        this.aveRate = aveRate;
    }

    public BigDecimal getPeakErr() {
        return peakErr;
    }

    public void setPeakErr(BigDecimal peakErr) {
        this.peakErr = peakErr;
    }

    public BigDecimal getValeyErr() {
        return valeyErr;
    }

    public void setValeyErr(BigDecimal valeyErr) {
        this.valeyErr = valeyErr;
    }
}
