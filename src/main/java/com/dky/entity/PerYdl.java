package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class PerYdl implements Serializable {


    private static final long serialVersionUID = 1L;


    private String areaName;//地区名称

    private String mDate;//时间

    private String unit;//用电的量的数值单位

    private BigDecimal allYdl;//各行业用电量中和，用电量只记录的是年

    private BigDecimal yiChanYdl;//一产用电量，用电量只记录的是年

    private BigDecimal erChanYdl;//二产用电量，用电量只记录的是年

    private BigDecimal sanChanYdl;//三产用电量，用电量只记录的是年

    private BigDecimal juMingYdl;//居民用电量，用电量只记录的是年

    private String minDate;//这个在数据库中是没有的，我用来做查询用一下的

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getAllYdl() {        return allYdl;    }

    public void setAllYdl(BigDecimal allYdl) {        this.allYdl = allYdl;    }

    public BigDecimal getYiChanYdl() {
        return yiChanYdl;
    }

    public void setYiChanYdl(BigDecimal yiChanYdl) {
        this.yiChanYdl = yiChanYdl;
    }

    public BigDecimal getErChanYdl() {
        return erChanYdl;
    }

    public void setErChanYdl(BigDecimal erChanYdl) {
        this.erChanYdl = erChanYdl;
    }

    public BigDecimal getSanChanYdl() {
        return sanChanYdl;
    }

    public void setSanChanYdl(BigDecimal sanChanYdl) {
        this.sanChanYdl = sanChanYdl;
    }

    public BigDecimal getJuMingYdl() {
        return juMingYdl;
    }

    public void setJuMingYdl(BigDecimal juMingYdl) {
        this.juMingYdl = juMingYdl;
    }
}
