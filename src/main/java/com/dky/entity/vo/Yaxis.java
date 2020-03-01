package com.dky.entity.vo;

public class Yaxis implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private  String type;//这个是输出格式
    private  String name;//Y轴数值的单位
    private  AxisLabel axisLabel;//这是个实体类，他是输出格式和type相对应

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}
