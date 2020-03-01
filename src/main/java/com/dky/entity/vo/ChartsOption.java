package com.dky.entity.vo;

import java.util.List;
import java.util.Map;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 17:07
 */
public class ChartsOption implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private  String legend;//这个是在图上方显示的图例，

    private  String []  hengZhou;//返回的是值，现在的柱状图，折线图用到了

    private  Series  series;//返回的是值，现在的柱状图，折线图，雷达图都用到了
    private Map map;

    private Yaxis yaxis;//代表了Y轴

    private List<Indicator> indicatorList;//这个是用来做影响因素的雷达图的，代表每一个点的名称和这个点的最大值，默认的是1，因为i在数据库存的实际值都是小数

    private List<Indicator> dataInfluence;//这个是用来做影响因素的雷达图的，代表每一个点的实际值，数据库存的都是小数，

    private String [] color;//这个是用于柱状图的每一个柱状的颜色不一样的，返回的颜色

    private List<BusNameValue> nameValueList;//这个是后加的，用来返回母线负荷分类的
    private  String []  data;//这个是后加的，用来返回母线负荷分类的













    /**
     * 下边的都是get()和set()方法
     * @return
     */
    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public List<BusNameValue> getNameValueList() {
        return nameValueList;
    }

    public void setNameValueList(List<BusNameValue> nameValueList) {
        this.nameValueList = nameValueList;
    }

    public String[] getColor() {        return color;    }
    public void setColor(String[] color) {        this.color = color;    }


    public List<Indicator> getDataInfluence() {
        return dataInfluence;
    }

    public void setDataInfluence(List<Indicator> dataInfluence) {
        this.dataInfluence = dataInfluence;
    }

    public List<Indicator> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
    }

    public Yaxis getYaxis() {
        return yaxis;
    }

    public void setYaxis(Yaxis yaxis) {
        this.yaxis = yaxis;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getLegend() {        return legend;    }
    public void setLegend(String legend) {        this.legend = legend;    }

    public String[] getHengZhou() {        return hengZhou;    }
    public void setHengZhou(String[] hengZhou) {        this.hengZhou = hengZhou;    }

    public Series getSeries() {        return series;    }
    public void setSeries(Series series) {        this.series = series;    }
}
