package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CharacteristicIndexBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mdate;
    private String devId;
    private String devDescr;
    private String daytraitType;
    private BigDecimal daytraitValue;
    private Date verTime;
    private String dt;

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevDescr() {
        return devDescr;
    }

    public void setDevDescr(String devDescr) {
        this.devDescr = devDescr;
    }

    public String getDaytraitType() {
        return daytraitType;
    }

    public void setDaytraitType(String daytraitType) {
        this.daytraitType = daytraitType;
    }

    public BigDecimal getDaytraitValue() {
        return daytraitValue;
    }

    public void setDaytraitValue(BigDecimal daytraitValue) {
        this.daytraitValue = daytraitValue;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
