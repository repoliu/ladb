package com.dky.entity.vo;

import java.io.Serializable;

public class TimeSelectButtonOfDayLoad implements Serializable{
    private long longTime;
    private String yearTime;
    private String monthTime;

    public TimeSelectButtonOfDayLoad(long longTime, String yearTime, String monthTime) {
        this.longTime = longTime;
        this.yearTime = yearTime;
        this.monthTime = monthTime;
    }

    public long getLongTime() {
        return longTime;
    }

    public void setLongTime(long longTime) {
        this.longTime = longTime;
    }

    public String getYearTime() {
        return yearTime;
    }

    public void setYearTime(String yearTime) {
        this.yearTime = yearTime;
    }

    public String getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(String monthTime) {
        this.monthTime = monthTime;
    }
}
