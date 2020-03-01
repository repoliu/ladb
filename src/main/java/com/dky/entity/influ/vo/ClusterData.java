package com.dky.entity.influ.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 15:46 2018/1/10
 */
public class ClusterData implements Serializable {
    private Integer area;
    private String areaname;
    private String year;
    private String flag;
    private String type;
    private BigDecimal maxTemperature;
    private BigDecimal minTemperature;
    private int days;
    private BigDecimal avgload;
    private BigDecimal maxload;
    private BigDecimal minload;
    private String temperature;
    private int countDays;
    private int reload;
    private int intAvgload;
    private boolean tempSelect;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean getTempSelect() {
        return tempSelect;
    }

    public void setTempSelect(boolean tempSelect) {
        this.tempSelect = tempSelect;
    }

    public int getIntAvgload() {
        return intAvgload;
    }

    public void setIntAvgload(int intAvgload) {
        this.intAvgload = intAvgload;
    }

    public String getType() {
        return type;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BigDecimal getAvgload() {
        return avgload;
    }

    public void setAvgload(BigDecimal avgload) {
        this.avgload = avgload;
    }

    public BigDecimal getMaxload() {
        return maxload;
    }

    public void setMaxload(BigDecimal maxload) {
        this.maxload = maxload;
    }

    public BigDecimal getMinload() {
        return minload;
    }

    public void setMinload(BigDecimal minload) {
        this.minload = minload;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
