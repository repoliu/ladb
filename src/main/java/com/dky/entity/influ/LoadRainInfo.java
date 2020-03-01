package com.dky.entity.influ;

import java.io.Serializable;
import java.util.Date;

public class LoadRainInfo   implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaname;//地区名称

    private Date time;//时间

    private Short temperature;//温度

    private Short rain;//降雨量

    private Short wp;//风力

    private Short rainDay;//全天降雨量

    private String rainLeve;//降雨级别

    private String stringDate;

    private String dateTimeMin;
    private String dateTimeMax;

    private String  model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDateTimeMin() {
        return dateTimeMin;
    }

    public void setDateTimeMin(String dateTimeMin) {
        this.dateTimeMin = dateTimeMin;
    }

    public String getDateTimeMax() {
        return dateTimeMax;
    }

    public void setDateTimeMax(String dateTimeMax) {
        this.dateTimeMax = dateTimeMax;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Short getTemperature() {
        return temperature;
    }

    public void setTemperature(Short temperature) {
        this.temperature = temperature;
    }

    public Short getRain() {
        return rain;
    }

    public void setRain(Short rain) {
        this.rain = rain;
    }

    public Short getWp() {
        return wp;
    }

    public void setWp(Short wp) {
        this.wp = wp;
    }

    public Short getRainDay() {
        return rainDay;
    }

    public void setRainDay(Short rainDay) {
        this.rainDay = rainDay;
    }

    public String getRainLeve() {
        return rainLeve;
    }

    public void setRainLeve(String rainLeve) {
        this.rainLeve = rainLeve;
    }
}
