package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Dbarea extends DbareaKey implements Serializable {
    private String cname;

    private String dname;

    private String parentId;

    private String nodeNum;

    private BigDecimal areaRate;

    private static final long serialVersionUID = 1L;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum == null ? null : nodeNum.trim();
    }

    public BigDecimal getAreaRate() {
        return areaRate;
    }

    public void setAreaRate(BigDecimal areaRate) {
        this.areaRate = areaRate;
    }
}