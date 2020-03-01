package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoadWeektrait extends LoadWeektraitKey implements Serializable {
    private BigDecimal maxload;

    private String maxdate;

    private BigDecimal minload;

    private String mindate;

    private BigDecimal aveload;

    private BigDecimal loadrate;

    private BigDecimal minloadrate;

    private BigDecimal maxdiffer;

    private String maxddate;

    private BigDecimal maxdifferrate;

    private String maxdrdate;

    private BigDecimal mindiffer;

    private String minddate;

    private BigDecimal mindifferrate;

    private String mindrdate;

    private BigDecimal avediffer;

    private BigDecimal avedifferrate;

    private String refDay;

    private Date verTime;

    private static final long serialVersionUID = 1L;

    public BigDecimal getMaxload() {
        return maxload;
    }

    public void setMaxload(BigDecimal maxload) {
        this.maxload = maxload;
    }

    public String getMaxdate() {
        return maxdate;
    }

    public void setMaxdate(String maxdate) {
        this.maxdate = maxdate == null ? null : maxdate.trim();
    }

    public BigDecimal getMinload() {
        return minload;
    }

    public void setMinload(BigDecimal minload) {
        this.minload = minload;
    }

    public String getMindate() {
        return mindate;
    }

    public void setMindate(String mindate) {
        this.mindate = mindate == null ? null : mindate.trim();
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

    public BigDecimal getMaxdiffer() {
        return maxdiffer;
    }

    public void setMaxdiffer(BigDecimal maxdiffer) {
        this.maxdiffer = maxdiffer;
    }

    public String getMaxddate() {
        return maxddate;
    }

    public void setMaxddate(String maxddate) {
        this.maxddate = maxddate == null ? null : maxddate.trim();
    }

    public BigDecimal getMaxdifferrate() {
        return maxdifferrate;
    }

    public void setMaxdifferrate(BigDecimal maxdifferrate) {
        this.maxdifferrate = maxdifferrate;
    }

    public String getMaxdrdate() {
        return maxdrdate;
    }

    public void setMaxdrdate(String maxdrdate) {
        this.maxdrdate = maxdrdate == null ? null : maxdrdate.trim();
    }

    public BigDecimal getMindiffer() {
        return mindiffer;
    }

    public void setMindiffer(BigDecimal mindiffer) {
        this.mindiffer = mindiffer;
    }

    public String getMinddate() {
        return minddate;
    }

    public void setMinddate(String minddate) {
        this.minddate = minddate == null ? null : minddate.trim();
    }

    public BigDecimal getMindifferrate() {
        return mindifferrate;
    }

    public void setMindifferrate(BigDecimal mindifferrate) {
        this.mindifferrate = mindifferrate;
    }

    public String getMindrdate() {
        return mindrdate;
    }

    public void setMindrdate(String mindrdate) {
        this.mindrdate = mindrdate == null ? null : mindrdate.trim();
    }

    public BigDecimal getAvediffer() {
        return avediffer;
    }

    public void setAvediffer(BigDecimal avediffer) {
        this.avediffer = avediffer;
    }

    public BigDecimal getAvedifferrate() {
        return avedifferrate;
    }

    public void setAvedifferrate(BigDecimal avedifferrate) {
        this.avedifferrate = avedifferrate;
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