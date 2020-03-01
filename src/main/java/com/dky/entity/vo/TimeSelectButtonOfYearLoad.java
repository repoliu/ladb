package com.dky.entity.vo;

import java.io.Serializable;

public class TimeSelectButtonOfYearLoad implements Serializable {
    private long longTime;
    private String yearTime;
    private String year;

    public TimeSelectButtonOfYearLoad(long longTime, String yearTime, String year) {
        this.longTime = longTime;
        this.yearTime = yearTime;
        this.year = year;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
