package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoadDaytrait extends LoadDaytraitKey implements Serializable {
    private BigDecimal maxload;

    private String maxtime;

    private BigDecimal minload;

    private String mintime;

    private BigDecimal fmmaxload;

    private String fmmaxtime;

    private BigDecimal pmmaxload;

    private String pmmaxtime;

    private BigDecimal aveload;

    private BigDecimal loadrate;

    private BigDecimal minloadrate;

    private BigDecimal differ;

    private BigDecimal differrate;

    private String refDay;

    private Date verTime;

    private static final long serialVersionUID = 1L;

    public BigDecimal getMaxload() {
        return maxload;
    }

    public void setMaxload(BigDecimal maxload) {
        this.maxload = maxload;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime == null ? null : maxtime.trim();
    }

    public BigDecimal getMinload() {
        return minload;
    }

    public void setMinload(BigDecimal minload) {
        this.minload = minload;
    }

    public String getMintime() {
        return mintime;
    }

    public void setMintime(String mintime) {
        this.mintime = mintime == null ? null : mintime.trim();
    }

    public BigDecimal getFmmaxload() {
        return fmmaxload;
    }

    public void setFmmaxload(BigDecimal fmmaxload) {
        this.fmmaxload = fmmaxload;
    }

    public String getFmmaxtime() {
        return fmmaxtime;
    }

    public void setFmmaxtime(String fmmaxtime) {
        this.fmmaxtime = fmmaxtime == null ? null : fmmaxtime.trim();
    }

    public BigDecimal getPmmaxload() {
        return pmmaxload;
    }

    public void setPmmaxload(BigDecimal pmmaxload) {
        this.pmmaxload = pmmaxload;
    }

    public String getPmmaxtime() {
        return pmmaxtime;
    }

    public void setPmmaxtime(String pmmaxtime) {
        this.pmmaxtime = pmmaxtime == null ? null : pmmaxtime.trim();
    }

    public BigDecimal getAveload() {
        return aveload;
    }

    public void setAveload(BigDecimal aveload) {
        this.aveload = aveload;
    }

    public BigDecimal getLoadrate() {
        return loadrate;
    }

    public void setLoadrate(BigDecimal loadrate) {
        this.loadrate = loadrate;
    }

    public BigDecimal getMinloadrate() {
        return minloadrate;
    }

    public void setMinloadrate(BigDecimal minloadrate) {
        this.minloadrate = minloadrate;
    }

    public BigDecimal getDiffer() {
        return differ;
    }

    public void setDiffer(BigDecimal differ) {
        this.differ = differ;
    }

    public BigDecimal getDifferrate() {
        return differrate;
    }

    public void setDifferrate(BigDecimal differrate) {
        this.differrate = differrate;
    }

    public String getRefDay() {
        return refDay;
    }

    public void setRefDay(String refDay) {
        this.refDay = refDay == null ? null : refDay.trim();
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }
}