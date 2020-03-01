package com.dky.entity.influ.vo;

import java.io.Serializable;
import java.util.Date;

public class ImportWeatherRecord implements Serializable {
    private String verTime;
    private String status = "成功";
    private String areaname;

    public String getVerTime() {
        return verTime;
    }

    public void setVerTime(String verTime) {
        this.verTime = verTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
}
