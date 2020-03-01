package com.dky.entity;

import java.io.Serializable;
import java.util.Date;

public class LdBusDvld1 implements Serializable {

    private static final long serialVersionUID = 1L;


    private String ldId;//设备ID
    private String ldDescr;//负荷名称
    private String stId;//所属厂站ID
    private String stDescr;//所属厂站名称
    private String dcc1Id;//一级电网id
    private String dcc1Descr;//一级电网名称
    private String dcc2Id;//二级电网id
    private String dcc2Descr;//二级电网名称
    private String dcc3Id;//三级电网id
    private String dcc3Descr;//三级电网名称
    private String dcc4Id;//四级电网id
    private String dcc4Descr;//四级电网名称
    private String dcc5Id;//五级电网id
    private String dcc5Descr;//五级电网名称
    private String dayFlag;//参与日前预测标识
    private String ultradayFlag;//参与日内预测标识
    private Integer ldVl;//电压等级
    private Date verTime;//录入时间（精确到分钟）


    public String getLdId() {
        return ldId;
    }

    public void setLdId(String ldId) {
        this.ldId = ldId;
    }

    public String getLdDescr() {
        return ldDescr;
    }

    public void setLdDescr(String ldDescr) {
        this.ldDescr = ldDescr;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getStDescr() {
        return stDescr;
    }

    public void setStDescr(String stDescr) {
        this.stDescr = stDescr;
    }

    public String getDcc1Id() {
        return dcc1Id;
    }

    public void setDcc1Id(String dcc1Id) {
        this.dcc1Id = dcc1Id;
    }

    public String getDcc1Descr() {
        return dcc1Descr;
    }

    public void setDcc1Descr(String dcc1Descr) {
        this.dcc1Descr = dcc1Descr;
    }

    public String getDcc2Id() {
        return dcc2Id;
    }

    public void setDcc2Id(String dcc2Id) {
        this.dcc2Id = dcc2Id;
    }

    public String getDcc2Descr() {
        return dcc2Descr;
    }

    public void setDcc2Descr(String dcc2Descr) {
        this.dcc2Descr = dcc2Descr;
    }

    public String getDcc3Id() {
        return dcc3Id;
    }

    public void setDcc3Id(String dcc3Id) {
        this.dcc3Id = dcc3Id;
    }

    public String getDcc3Descr() {
        return dcc3Descr;
    }

    public void setDcc3Descr(String dcc3Descr) {
        this.dcc3Descr = dcc3Descr;
    }

    public String getDcc4Id() {
        return dcc4Id;
    }

    public void setDcc4Id(String dcc4Id) {
        this.dcc4Id = dcc4Id;
    }

    public String getDcc4Descr() {
        return dcc4Descr;
    }

    public void setDcc4Descr(String dcc4Descr) {
        this.dcc4Descr = dcc4Descr;
    }

    public String getDcc5Id() {
        return dcc5Id;
    }

    public void setDcc5Id(String dcc5Id) {
        this.dcc5Id = dcc5Id;
    }

    public String getDcc5Descr() {
        return dcc5Descr;
    }

    public void setDcc5Descr(String dcc5Descr) {
        this.dcc5Descr = dcc5Descr;
    }

    public String getDayFlag() {
        return dayFlag;
    }

    public void setDayFlag(String dayFlag) {
        this.dayFlag = dayFlag;
    }

    public String getUltradayFlag() {
        return ultradayFlag;
    }

    public void setUltradayFlag(String ultradayFlag) {
        this.ultradayFlag = ultradayFlag;
    }

    public Integer getLdVl() {
        return ldVl;
    }

    public void setLdVl(Integer ldVl) {
        this.ldVl = ldVl;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }
}
