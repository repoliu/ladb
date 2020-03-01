package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class SocietyCapacity  implements Serializable {

    private static final long serialVersionUID = 1L;


    private String areaName;//地区名称

    private String mdate;//时间

    private String minDate;//这个在数据库中是没有的，我用来做查询用一下的

    private String id;//id是必须的，查询的时候是从UserTypeArea实体类中拿到num赋值到id 上

    private BigDecimal capacityMon;//记录详细的用电量，到月份

    public String getMinDate() {        return minDate;    }
    public void setMinDate(String minDate) {        this.minDate = minDate;    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getMdate() {        return mdate;    }
    public void setMdate(String mdate) {        this.mdate = mdate;    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCapacityMon() {
        return capacityMon;
    }

    public void setCapacityMon(BigDecimal capacityMon) {
        this.capacityMon = capacityMon;
    }
}
