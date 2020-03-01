package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class DbareaKey implements Serializable {
    private Integer area;

    private BigDecimal keyValue;

    private static final long serialVersionUID = 1L;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public BigDecimal getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(BigDecimal keyValue) {
        this.keyValue = keyValue;
    }
}