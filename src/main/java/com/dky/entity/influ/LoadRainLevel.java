package com.dky.entity.influ;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadRainLevel  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String levelName;
    private BigDecimal rainMin;
    private BigDecimal rainMax;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public BigDecimal getRainMin() {
        return rainMin;
    }

    public void setRainMin(BigDecimal rainMin) {
        this.rainMin = rainMin;
    }

    public BigDecimal getRainMax() {
        return rainMax;
    }

    public void setRainMax(BigDecimal rainMax) {
        this.rainMax = rainMax;
    }
}
