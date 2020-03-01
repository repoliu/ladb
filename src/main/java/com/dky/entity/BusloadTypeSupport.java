package com.dky.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *母线负荷分类
 * 支持度查询建立的表
 */
public class BusloadTypeSupport {
    private String  createDate;
    private String  busloadType;
    private String  dccDescr;
    private BigDecimal relationDegree;
    private Date verTime;


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBusloadType() {
        return busloadType;
    }

    public void setBusloadType(String busloadType) {
        this.busloadType = busloadType;
    }

    public String getDccDescr() {
        return dccDescr;
    }

    public void setDccDescr(String dccDescr) {
        this.dccDescr = dccDescr;
    }

    public BigDecimal getRelationDegree() {
        return relationDegree;
    }

    public void setRelationDegree(BigDecimal relationDegree) {
        this.relationDegree = relationDegree;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }
}
