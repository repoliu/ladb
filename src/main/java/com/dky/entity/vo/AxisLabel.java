package com.dky.entity.vo;

public class AxisLabel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String formatter;//确认了他展现数据的格式，和实体类【Yaxis】中的type相对应

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
