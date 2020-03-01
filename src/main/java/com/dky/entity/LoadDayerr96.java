package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoadDayerr96 implements Serializable {


    private int area;   //地区标识
    private BigDecimal num;//序号，从0-96
    private String forday;//被做误差分析日的日期
    private String mtime;//96点时间，如：00:15/00:30/00:45，即15分钟一个点96点时间
    private BigDecimal loadfor;// 预测负荷值
    private BigDecimal loadact;//实际负荷值
    private BigDecimal err;// 96个点误差值
    private static final long serialVersionUID = 1L;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getForday() {
        return forday;
    }

    public void setForday(String forday) {
        this.forday = forday;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public BigDecimal getLoadfor() {
        return loadfor;
    }

    public void setLoadfor(BigDecimal loadfor) {
        this.loadfor = loadfor;
    }

    public BigDecimal getLoadact() {
        return loadact;
    }

    public void setLoadact(BigDecimal loadact) {
        this.loadact = loadact;
    }

    public BigDecimal getErr() {
        return err;
    }

    public void setErr(BigDecimal err) {
        this.err = err;
    }
}