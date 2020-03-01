package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadWeektypeload96Key implements Serializable {
    private Integer area;

    private BigDecimal id;

    private String startDay;

    private Integer daytype;

    private static final long serialVersionUID = 1L;

    private String startDate;

    private String endDate;

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

    public String getStartDay() {
        return startDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay == null ? null : startDay.trim();
    }

    public Integer getDaytype() {
        return daytype;
    }

    public void setDaytype(Integer daytype) {
        this.daytype = daytype;
    }


}