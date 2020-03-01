package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class PerIncome  implements Serializable {

    private static final long serialVersionUID = 1L;


    private String areaName;//地区名称

    private String mDate;//时间

    private String unit;//城镇居民人均收入的单位

    private BigDecimal perIncome;//城镇居民人均收入

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPerIncome() {
        return perIncome;
    }

    public void setPerIncome(BigDecimal perIncome) {
        this.perIncome = perIncome;
    }
}
