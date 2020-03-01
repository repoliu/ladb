package com.dky.controller.generatrixAnalysis.base.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于构建bootsrap-treeview数据的实体类
 * Created by henry on 2017/4/7.
 */
public class Nodes {
    private String text;
    private String id;   // 从long类型修改为string,jsp无法获取超过20长度的long类型数值
    private String icon;
    private String selectedIcon;
    private String color;
    private String backColor;
    private boolean selectable = true;
    private State state;
    private String href;
    private List<String> tags;

    private List<Nodes> nodes = new ArrayList<Nodes>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Nodes> getNodes() {
        return nodes;
    }

    public void setNodes(List<Nodes> nodesList) {
        this.nodes = nodesList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", icon='" + icon + '\'' +
                ", selectedIcon='" + selectedIcon + '\'' +
                ", color='" + color + '\'' +
                ", backColor='" + backColor + '\'' +
                ", selectable=" + selectable +
                ", state=" + state +
                ", href='" + href + '\'' +
                ", tags=" + tags +
                ", nodes=" + nodes +
                '}';
    }
}
