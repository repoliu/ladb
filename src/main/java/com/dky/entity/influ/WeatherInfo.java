package com.dky.entity.influ;

import java.io.Serializable;
import java.util.Date;

public class WeatherInfo implements Serializable {
    private Date time;

    private String areaName;

    private String weatherResult;

    private String notes;

    private String verTime;

    public String getVerTime() {
        return verTime;
    }

    public void setVerTime(String verTime) {
        this.verTime = verTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getWeatherResult() {
        return weatherResult;
    }

    public void setWeatherResult(String weatherResult) {
        this.weatherResult = weatherResult == null ? null : weatherResult.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }
}