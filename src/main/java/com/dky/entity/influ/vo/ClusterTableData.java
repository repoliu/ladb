package com.dky.entity.influ.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:03 2018/2/1
 */
public class ClusterTableData implements Serializable {
    private String mdateN;
    private BigDecimal tempB;
    private BigDecimal maxloadB;
    private BigDecimal avgloadB;
    private String mdate;
    private Integer temp;
    private Integer maxload;
    private Integer avgload;

    public String getMdateN() {
        return mdateN;
    }

    public void setMdateN(String mdateN) {
        this.mdateN = mdateN;
    }

    public BigDecimal getTempB() {
        return tempB;
    }

    public void setTempB(BigDecimal tempB) {
        this.tempB = tempB;
    }

    public BigDecimal getMaxloadB() {
        return maxloadB;
    }

    public void setMaxloadB(BigDecimal maxloadB) {
        this.maxloadB = maxloadB;
    }

    public BigDecimal getAvgloadB() {
        return avgloadB;
    }

    public void setAvgloadB(BigDecimal avgloadB) {
        this.avgloadB = avgloadB;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getMaxload() {
        return maxload;
    }

    public void setMaxload(Integer maxload) {
        this.maxload = maxload;
    }

    public Integer getAvgload() {
        return avgload;
    }

    public void setAvgload(Integer avgload) {
        this.avgload = avgload;
    }
}
