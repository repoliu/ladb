package com.dky.entity.influ.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 9:48 2018/1/10
 */
public class WeatherInfo implements Serializable{
    private Date time;
    private Integer maxTemp;
    private Integer minTemp;
    private Integer avgTemp;
    private Integer minWp;
    private Integer maxWp;
    private Integer avgWp;
    private String wp;//风力
    private Integer humidity;
    private Integer rainfall;
    private String weatherType;
    private Integer cosiness;

    public Integer getRainfall() {
        return rainfall;
    }

    public void setRainfall(Integer rainfall) {
        this.rainfall = rainfall;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }

    public Integer getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Integer avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Integer getMinWp() {
        return minWp;
    }

    public void setMinWp(Integer minWp) {
        this.minWp = minWp;
    }

    public Integer getMaxWp() {
        return maxWp;
    }

    public void setMaxWp(Integer maxWp) {
        this.maxWp = maxWp;
    }

    public Integer getAvgWp() {
        return avgWp;
    }

    public void setAvgWp(Integer avgWp) {
        this.avgWp = avgWp;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public Integer getCosiness() {
        return cosiness;
    }

    public void setCosiness(Integer cosiness) {
        this.cosiness = cosiness;
    }
}
