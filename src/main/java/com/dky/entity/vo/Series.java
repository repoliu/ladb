package com.dky.entity.vo;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 17:11
 *
 */
public class Series implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String name;//图例的名称
    private Boolean smooth;//是否为平滑的曲线，true：是
    private String [] data;//代表了实际的数据，数据库查出来的实际值
    private String type; //折线图表示（生成温度曲线）line   //柱状图表示bar
    private Boolean showAllSymbol = true; //标志图形默认只有主轴显示（随主轴标签间隔隐藏策略），如需全部显示可把 showAllSymbol 设为 true。
    private Integer yAxisIndex;//使用第几个Y轴，默认为0
    private Label label; //这个类的作用是在柱状里边显示数值
    private MarkLine markLine;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public MarkLine getMarkLine() {
        return markLine;
    }

    public void setMarkLine(MarkLine markLine) {
        this.markLine = markLine;
    }

    public Integer getYAxisIndex() {
        return yAxisIndex;
    }

    public void setYAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public Label getLabel() {        return label;    }
    public void setLabel(Label label) {        this.label = label;    }

    public Boolean getShowAllSymbol() {
        return showAllSymbol;
    }
    public void setShowAllSymbol(Boolean showAllSymbol) {
        this.showAllSymbol = showAllSymbol;
    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public Boolean getSmooth() {        return smooth;    }
    public void setSmooth(Boolean smooth) {        this.smooth = smooth;    }

    public String[] getData() {        return data;    }
    public void setData(String[] data) {        this.data = data;    }

    public String getType() {        return type;    }
    public void setType(String type) {        this.type = type;    }
}
