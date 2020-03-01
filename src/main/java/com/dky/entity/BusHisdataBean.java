package com.dky.entity;

import java.io.Serializable;

/**
 * Created by sks on 2017/3/28.
 */
public class BusHisdataBean extends BasicBean implements Serializable {

    private String mdate;

    private String id;

    private String code;

    private String descr;

    private String dv_id;

    private String source;

    private String flag;

    private String ver_time;

    private int clusterType;

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDv_id() {
        return dv_id;
    }

    public void setDv_id(String dv_id) {
        this.dv_id = dv_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getVer_time() {
        return ver_time;
    }

    public void setVer_time(String ver_time) {
        this.ver_time = ver_time;
    }

    public int getClusterType() {
        return clusterType;
    }

    public void setClusterType(int clusterType) {
        this.clusterType = clusterType;
    }
}
