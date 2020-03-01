package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadShortdayerrAll implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer area;
    private Integer id;
    private String mdate;
    private BigDecimal err;
    private BigDecimal peak;
    private BigDecimal valey;
    private BigDecimal hispeak;
    private BigDecimal forpeak;
    private BigDecimal hisvaley;
    private BigDecimal forvaley;
    private BigDecimal ratepeak;
    private BigDecimal ratevaley;
    private String ddate;
    private Integer algoId;
    private String algoName;


    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getErr() {
        return err;
    }

    public void setErr(BigDecimal err) {
        this.err = err;
    }

    public BigDecimal getPeak() {
        return peak;
    }

    public void setPeak(BigDecimal peak) {
        this.peak = peak;
    }

    public BigDecimal getValey() {
        return valey;
    }

    public void setValey(BigDecimal valey) {
        this.valey = valey;
    }

    public BigDecimal getHispeak() {
        return hispeak;
    }

    public void setHispeak(BigDecimal hispeak) {
        this.hispeak = hispeak;
    }

    public BigDecimal getForpeak() {
        return forpeak;
    }

    public void setForpeak(BigDecimal forpeak) {
        this.forpeak = forpeak;
    }

    public BigDecimal getHisvaley() {
        return hisvaley;
    }

    public void setHisvaley(BigDecimal hisvaley) {
        this.hisvaley = hisvaley;
    }

    public BigDecimal getForvaley() {
        return forvaley;
    }

    public void setForvaley(BigDecimal forvaley) {
        this.forvaley = forvaley;
    }

    public BigDecimal getRatepeak() {
        return ratepeak;
    }

    public void setRatepeak(BigDecimal ratepeak) {
        this.ratepeak = ratepeak;
    }

    public BigDecimal getRatevaley() {
        return ratevaley;
    }

    public void setRatevaley(BigDecimal ratevaley) {
        this.ratevaley = ratevaley;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public Integer getAlgoId() {
        return algoId;
    }

    public void setAlgoId(Integer algoId) {
        this.algoId = algoId;
    }
}
