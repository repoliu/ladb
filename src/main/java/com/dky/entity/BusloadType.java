package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BusloadType implements Serializable {
    private static final long serialVersionUID = 1L;

    private String devId;//设备id
    private String devDescr;//负荷名称
    private String busloadType;//母线负荷类型
    private String dccDwscr;//母线负荷所属地区
    private BigDecimal typeDegree;
    private Date   verTime;//数据录入时间


    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevDescr() {
        return devDescr;
    }

    public void setDevDescr(String devDescr) {
        this.devDescr = devDescr;
    }

    public String getBusloadType() {
        return busloadType;
    }

    public void setBusloadType(String busloadType) {
        this.busloadType = busloadType;
    }

    public String getDccDwscr() {
        return dccDwscr;
    }

    public void setDccDwscr(String dccDwscr) {
        this.dccDwscr = dccDwscr;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }

    public BigDecimal getTypeDegree() {
        return typeDegree;
    }

    public void setTypeDegree(BigDecimal typeDegree) {
        this.typeDegree = typeDegree;
    }
}
