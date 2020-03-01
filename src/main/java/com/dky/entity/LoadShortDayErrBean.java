package com.dky.entity;

import java.io.Serializable;

/**
 * Created by sks on 2017/4/20.
 */
public class LoadShortDayErrBean implements Serializable {

    private String area;
    private String id;
    private String mdate;
    private String err;
    private String peak;

    private String valey;
    private String hispeak;
    private String forpeak;
    private String hisvaley;
    private String ddate;

    private String ratepeak;
    private String ratevaley;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getPeak() {
        return peak;
    }

    public void setPeak(String peak) {
        this.peak = peak;
    }

    public String getValey() {
        return valey;
    }

    public void setValey(String valey) {
        this.valey = valey;
    }

    public String getHispeak() {
        return hispeak;
    }

    public void setHispeak(String hispeak) {
        this.hispeak = hispeak;
    }

    public String getForpeak() {
        return forpeak;
    }

    public void setForpeak(String forpeak) {
        this.forpeak = forpeak;
    }

    public String getHisvaley() {
        return hisvaley;
    }

    public void setHisvaley(String hisvaley) {
        this.hisvaley = hisvaley;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getRatepeak() {
        return ratepeak;
    }

    public void setRatepeak(String ratepeak) {
        this.ratepeak = ratepeak;
    }

    public String getRatevaley() {
        return ratevaley;
    }

    public void setRatevaley(String ratevaley) {
        this.ratevaley = ratevaley;
    }
}
