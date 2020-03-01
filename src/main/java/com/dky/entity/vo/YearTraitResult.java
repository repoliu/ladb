package com.dky.entity.vo;

import com.dky.entity.LoadYeartrait;

import java.io.Serializable;
import java.util.List;

public class YearTraitResult implements Serializable {
    private String minYear;
    private String maxYear;
    private LoadYeartrait loadYeartrait;
    /*private Long longMaxTime;
    private Long longMinTime;*/
    private String year;
    private String areaname;
    private List<TimeSelectButtonOfYearLoad> timeSelectButton;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    /*public Long getLongMaxTime() {
        return longMaxTime;
    }

    public void setLongMaxTime(Long longMaxTime) {
        this.longMaxTime = longMaxTime;
    }

    public Long getLongMinTime() {
        return longMinTime;
    }

    public void setLongMinTime(Long longMinTime) {
        this.longMinTime = longMinTime;
    }*/

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public List<TimeSelectButtonOfYearLoad> getTimeSelectButton() {
        return timeSelectButton;
    }

    public void setTimeSelectButton(List<TimeSelectButtonOfYearLoad> timeSelectButton) {
        this.timeSelectButton = timeSelectButton;
    }

    public LoadYeartrait getLoadYeartrait() {
        return loadYeartrait;
    }

    public void setLoadYeartrait(LoadYeartrait loadYeartrait) {
        this.loadYeartrait = loadYeartrait;
    }

    public String getMinYear() {
        return minYear;
    }

    public void setMinYear(String minYear) {
        this.minYear = minYear;
    }

    public String getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(String maxYear) {
        this.maxYear = maxYear;
    }
}
