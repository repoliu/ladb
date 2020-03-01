package com.dky.entity.influ.vo;

import java.io.Serializable;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 14:53 2018/1/8
 */
public class RelationValue implements Serializable{
    private String dayTime;
    private Integer value;

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
