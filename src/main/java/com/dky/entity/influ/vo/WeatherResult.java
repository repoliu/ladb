package com.dky.entity.influ.vo;

import com.dky.entity.influ.ForWeather;
import com.dky.entity.vo.TimeSelectButtonOfDayLoad;
import com.dky.entity.vo.TimeSelectButtonOfYearLoad;

import java.io.Serializable;
import java.util.List;

public class WeatherResult implements Serializable {
    private String maxDate;
    private String minDate;
    private String year;
    private String analysisType;
    private Long longMaxTime;
    private Long longMinTime;
    private Integer area;
    private String areaname;
    private String mdate;
    private String lastMdate;
    private String tList;
    private String rList;
    private ForWeather forWeather;
    private String sList;
    private String iList;
    private String nowList;
    private String lastList;
    private WeatherInfo weatherInfo;
    private String tempRelations;
    private String humdRelations;
    private String rainRelations;
    private String cosnRelations;
    private Boolean tempSelect;
    private List<TimeSelectButtonOfDayLoad> timeButton;
    private List<TimeSelectButtonOfYearLoad> yearButton;

    public Boolean getTempSelect() {
        return tempSelect;
    }

    public void setTempSelect(Boolean tempSelect) {
        this.tempSelect = tempSelect;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<TimeSelectButtonOfYearLoad> getYearButton() {
        return yearButton;
    }

    public void setYearButton(List<TimeSelectButtonOfYearLoad> yearButton) {
        this.yearButton = yearButton;
    }

    public String getTempRelations() {
        return tempRelations;
    }

    public void setTempRelations(String tempRelations) {
        this.tempRelations = tempRelations;
    }

    public String getHumdRelations() {
        return humdRelations;
    }

    public void setHumdRelations(String humdRelations) {
        this.humdRelations = humdRelations;
    }

    public String getRainRelations() {
        return rainRelations;
    }

    public void setRainRelations(String rainRelations) {
        this.rainRelations = rainRelations;
    }

    public String getCosnRelations() {
        return cosnRelations;
    }

    public void setCosnRelations(String cosnRelations) {
        this.cosnRelations = cosnRelations;
    }

    public String getLastMdate() {
        return lastMdate;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public void setLastMdate(String lastMdate) {
        this.lastMdate = lastMdate;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getNowList() {
        return nowList;
    }

    public void setNowList(String nowList) {
        this.nowList = nowList;
    }

    public String getLastList() {
        return lastList;
    }

    public void setLastList(String lastList) {
        this.lastList = lastList;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Long getLongMaxTime() {
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
    }

    public List<TimeSelectButtonOfDayLoad> getTimeButton() {
        return timeButton;
    }

    public void setTimeButton(List<TimeSelectButtonOfDayLoad> timeButton) {
        this.timeButton = timeButton;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getTList() {
        return tList;
    }

    public void setTList(String tList) {
        this.tList = tList;
    }

    public String getRList() {
        return rList;
    }

    public void setRList(String rList) {
        this.rList = rList;
    }

    public ForWeather getForWeather() {
        return forWeather;
    }

    public void setForWeather(ForWeather forWeather) {
        this.forWeather = forWeather;
    }

    public String getSList() {
        return sList;
    }

    public void setSList(String sList) {
        this.sList = sList;
    }

    public String getIList() {
        return iList;
    }

    public void setIList(String iList) {
        this.iList = iList;
    }
}
