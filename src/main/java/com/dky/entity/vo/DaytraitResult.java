package com.dky.entity.vo;

import com.dky.entity.LoadDaytrait;

import java.io.Serializable;
import java.util.List;

public class DaytraitResult implements Serializable {
    private String maxMdate;
    private String minMdate;
    //private Long longMaxTime;
    //private Long longMinTime;
    private Integer pointResponse;
    private String mdate;
    private LoadDaytrait loadDay;
    private List<TimeSelectButtonOfDayLoad> timeSelectButton;
    private String areaname;

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMaxMdate() {
        return maxMdate;
    }

    public void setMaxMdate(String maxMdate) {
        this.maxMdate = maxMdate;
    }

    public String getMinMdate() {
        return minMdate;
    }

    public void setMinMdate(String minMdate) {
        this.minMdate = minMdate;
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

    public Integer getPointResponse() {
        return pointResponse;
    }

    public void setPointResponse(Integer pointResponse) {
        this.pointResponse = pointResponse;
    }

    public LoadDaytrait getLoadDay() {
        return loadDay;
    }

    public void setLoadDay(LoadDaytrait loadDay) {
        this.loadDay = loadDay;
    }

    public List<TimeSelectButtonOfDayLoad> getTimeSelectButton() {
        return timeSelectButton;
    }

    public void setTimeSelectButton(List<TimeSelectButtonOfDayLoad> timeSelectButton) {
        this.timeSelectButton = timeSelectButton;
    }
}
