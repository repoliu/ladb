package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadDaytraitKey implements Serializable {
    private Integer area;

    private BigDecimal id;

    private Integer min;

    private String mdate;
    private String madeOme;//第一个时间
    private String madeTwo;//第二个时间

    private String legend;//用于存放传进来的多个图例，在数据库用 in查询

    public String getMadeOme() {        return madeOme;    }
    public void setMadeOme(String madeOme) {        this.madeOme = madeOme;    }

    public String getMadeTwo() {        return madeTwo;    }
    public void setMadeTwo(String madeTwo) {        this.madeTwo = madeTwo;    }

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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }
}