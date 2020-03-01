package com.dky.entity.vo;

import java.io.Serializable;
import java.util.List;

public class TreeDataTemp implements Serializable{
    private String name;
    private Boolean spread;
    private Integer area;
    private List<TreeDataTemp> children;
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private String id;//这个是后加的

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public List<TreeDataTemp> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDataTemp> children) {
        this.children = children;
    }
}
