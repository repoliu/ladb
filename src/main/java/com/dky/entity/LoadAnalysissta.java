package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadAnalysissta extends LoadAnalysisstaKey implements Serializable {
    private BigDecimal stahigh;

    private static final long serialVersionUID = 1L;

    public BigDecimal getStahigh() {
        return stahigh;
    }

    public void setStahigh(BigDecimal stahigh) {
        this.stahigh = stahigh;
    }
}