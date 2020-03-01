package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadMonthtypeload96Key implements Serializable {
    private Integer area;//地区id，数据库必填项

    private BigDecimal id;//id没有用到，但是在数据库是必填项

    private String startDay;//开始时间

    private Integer daytype;//1代表了工作日，0休息日

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

    public String getStartDay() {
        return startDay;
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