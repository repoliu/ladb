package com.dky.entity.vo;

public class Normal implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    //这个类的作用是在柱状里边显示数值
    private boolean show=true;
    private String position="inside";

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
