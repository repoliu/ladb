package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OutputValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;//地区名称

    private String mDate;//时间

    private BigDecimal outPutValue;//生产总值

    private BigDecimal outPutIndex;//生产指数

    private String minDate;//这个在数据库中是没有的，我用来做查询用一下的

    public String getMinDate() {        return minDate;    }
    public void setMinDate(String minDate) {        this.minDate = minDate;    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public BigDecimal getOutPutValue() {
        return outPutValue;
    }

    public void setOutPutValue(BigDecimal outPutValue) {
        this.outPutValue = outPutValue;
    }

    public BigDecimal getOutPutIndex() {
        return outPutIndex;
    }

    public void setOutPutIndex(BigDecimal outPutIndex) {
        this.outPutIndex = outPutIndex;
    }
}
