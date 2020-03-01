package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class IndustryStructure implements Serializable {

    private static final long serialVersionUID = 1L;


    private String areaName;

    private String mDate;

    private String unit;

    private BigDecimal primary;

    private BigDecimal second;

    private BigDecimal tertiary;

    private String minDate;

    public String getMinDate() {        return minDate;    }
    public void setMinDate(String minDate) {        this.minDate = minDate;    }

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

    public BigDecimal getPrimary() {        return primary;    }
    public void setPrimary(BigDecimal primary) {        this.primary = primary;    }

    public BigDecimal getSecond() {
        return second;
    }

    public void setSecond(BigDecimal second) {
        this.second = second;
    }

    public BigDecimal getTertiary() {
        return tertiary;
    }

    public void setTertiary(BigDecimal tertiary) {
        this.tertiary = tertiary;
    }



}
