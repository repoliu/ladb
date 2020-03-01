package com.dky.entity.vo;

public class Label implements java.io.Serializable {
    private static final long serialVersionUID = 1L;



    private Normal normal; //这个类的作用是在柱状里边显示数值

    public Normal getNormal() {        return normal;    }
    public void setNormal(Normal normal) {        this.normal = normal;    }
}
