package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LoadHisdataKey implements Serializable {
    private Integer area;

    private BigDecimal id;

    private String mdate;
    private List<String> mdateList;

    private String madeOne;//第一个时间
    private String madeTwo;//第二个时间

    public String getMadeOne() {        return madeOne;    }
    public void setMadeOne(String madeOne) {        this.madeOne = madeOne;    }


    public String getMadeTwo() {        return madeTwo;    }
    public void setMadeTwo(String madeTwo) {        this.madeTwo = madeTwo;    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    public List<String> getMdateList() {
        return mdateList;
    }

    public void setMdateList(List<String> mdateList) {
        this.mdateList = mdateList;
    }
}