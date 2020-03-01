package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoadAirconditionerAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer area;//地区id

    private String mdate;//时间

    private String aday;

    private BigDecimal bday;

    private String cday;

    private String resultType;//分析结果类型

    private BigDecimal result;//分析结果

    private Date verTime;

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

    public String getAday() {
        return aday;
    }

    public void setAday(String aday) {
        this.aday = aday;
    }

    public BigDecimal getBday() {
        return bday;
    }

    public void setBday(BigDecimal bday) {
        this.bday = bday;
    }

    public String getCday() {
        return cday;
    }

    public void setCday(String cday) {
        this.cday = cday;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }
}
