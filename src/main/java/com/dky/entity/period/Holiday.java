package com.dky.entity.period;

import java.io.Serializable;

/**
 * @Author: YangSL
 * @Description: 节假日信息实体类
 * @Date: Create in 9:33 2018/2/28
 */
public class Holiday implements Serializable {
    private String holname;
    private String myear;
    private String startday;
    private String endday;
    private Integer days;

    public String getHolname() {
        return holname;
    }

    public void setHolname(String holname) {
        this.holname = holname;
    }

    public String getMyear() {
        return myear;
    }

    public void setMyear(String myear) {
        this.myear = myear;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
