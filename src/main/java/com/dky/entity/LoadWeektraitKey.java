package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadWeektraitKey implements Serializable {
    private Integer area;

    private BigDecimal id;

    private Integer min;

    private String startDay;

    private String endDay;

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

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay == null ? null : startDay.trim();
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay == null ? null : endDay.trim();
    }
}