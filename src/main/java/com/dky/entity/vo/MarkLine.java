package com.dky.entity.vo;

import com.dky.entity.influ.Yaxis;

import java.util.List;

public class MarkLine implements java.io.Serializable {
    private static final long serialVersionUID = 1L;



    private boolean silent;
    private List<com.dky.entity.influ.Yaxis> data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public List<Yaxis> getData() {
        return data;
    }

    public void setData(List<Yaxis> data) {
        this.data = data;
    }
}
