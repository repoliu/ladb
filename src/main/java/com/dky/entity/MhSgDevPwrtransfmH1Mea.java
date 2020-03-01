package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class MhSgDevPwrtransfmH1Mea implements Serializable {

    private static final long serialVersionUID = 1L;

    private String createDatetime;
    private String datasouceId;
    private String devId;
    private BigDecimal measValue;
    private String measName;
    private String statisName;
    private String dt;
    private String msType;
    private int clusterType;

    public int getClusterType() {
        return clusterType;
    }

    public void setClusterType(int clusterType) {
        this.clusterType = clusterType;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getDatasouceId() {
        return datasouceId;
    }

    public void setDatasouceId(String datasouceId) {
        this.datasouceId = datasouceId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public BigDecimal getMeasValue() {
        return measValue;
    }

    public void setMeasValue(BigDecimal measValue) {
        this.measValue = measValue;
    }

    public String getMeasName() {
        return measName;
    }

    public void setMeasName(String measName) {
        this.measName = measName;
    }

    public String getStatisName() {
        return statisName;
    }

    public void setStatisName(String statisName) {
        this.statisName = statisName;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getMsType() {
        return msType;
    }

    public void setMsType(String msType) {
        this.msType = msType;
    }
}
