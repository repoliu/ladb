package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadAnalysis288Key implements Serializable {
    private Integer area;

    private BigDecimal id;

    private Integer nweek;

    private String enddate;

    private String flag;

    private String mdate;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }
}