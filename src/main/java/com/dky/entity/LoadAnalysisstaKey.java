package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadAnalysisstaKey implements Serializable {
    private Integer area;

    private BigDecimal id;

    private Integer nweek;

    private String enddate;

    private Integer daynum;

    private static final long serialVersionUID = 1L;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getNweek() {
        return nweek;
    }

    public void setNweek(Integer nweek) {
        this.nweek = nweek;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public Integer getDaynum() {
        return daynum;
    }

    public void setDaynum(Integer daynum) {
        this.daynum = daynum;
    }
}