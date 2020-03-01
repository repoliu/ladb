package com.dky.entity.forecast;

import java.io.Serializable;

public class DygraphsTable implements Serializable {

    private static final long serialVersionUID = 1L;


    private String  title;// 预测负荷的表格其他列的宽度

    private int  minWidth;

    private String  align;// 预测负荷的表格中数据剧中

    private String field;

    private boolean sort;// 预测负荷的表格数据后有个上下点击的按钮

    private String fixed;//确定不能动的那一列或者几列

    private String type;//选定的这样行拖动滚动条的时候不动

    private String unresize;//是否禁用拖拽列宽（默认：false）。默认情况下会根据列类型（type）来决定是否禁用，
                            // 如复选框列，会自动禁用。而其它普通列，默认允许拖拽列宽，当然你也可以设置 true 来禁用该功能。

    private String edit;

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getUnresize() {
        return unresize;
    }

    public void setUnresize(String unresize) {
        this.unresize = unresize;
    }

    public String getType() {        return type;    }

    public void setType(String type) {        this.type = type;    }

    public String getFixed() {        return fixed;    }

    public void setFixed(String fixed) {        this.fixed = fixed;    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}
