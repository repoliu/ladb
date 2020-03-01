package com.dky.entity.vo;

/**
 * 这个实体类是在做雷达图的时候用到的，
 */
public class Indicator {

    private String name;//图例的名称

    private int  max=1;//每一个点的最大值是1，数据库中存的都是小数，这直接默认是1

    private String [] value;//代表每一年的实际值的集合

    public String[] getValue() {        return value;    }
    public void setValue(String[] value) {        this.value = value;    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
