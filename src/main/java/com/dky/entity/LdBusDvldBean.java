package com.dky.entity;

import java.io.Serializable;

/**
 * Created by sks on 2017/3/29.
 */
public class LdBusDvldBean implements Serializable {

    private  String  LD_ID;
    private  String  LD_DESCR;
    private  String  LD_P_ID;
    private  String  LD_Q_ID;
    private  String  ST_ID;
    private  String  ST_DESCR;
    private  String  DV_ID;
    private  String  DV_DESCR;
    private  String  CO_ID;
    private  String  CO_DESCR;
    private  String  FLAG;
    private  String  LD_VL;
    private  String  VER_TIME;
    private  String  NEWREAD;

    public String getLD_ID() {
        return LD_ID;
    }

    public void setLD_ID(String LD_ID) {
        this.LD_ID = LD_ID;
    }

    public String getLD_DESCR() {
        return LD_DESCR;
    }

    public void setLD_DESCR(String LD_DESCR) {
        this.LD_DESCR = LD_DESCR;
    }

    public String getLD_P_ID() {
        return LD_P_ID;
    }

    public void setLD_P_ID(String LD_P_ID) {
        this.LD_P_ID = LD_P_ID;
    }

    public String getLD_Q_ID() {
        return LD_Q_ID;
    }

    public void setLD_Q_ID(String LD_Q_ID) {
        this.LD_Q_ID = LD_Q_ID;
    }

    public String getST_ID() {
        return ST_ID;
    }

    public void setST_ID(String ST_ID) {
        this.ST_ID = ST_ID;
    }

    public String getST_DESCR() {
        return ST_DESCR;
    }

    public void setST_DESCR(String ST_DESCR) {
        this.ST_DESCR = ST_DESCR;
    }

    public String getDV_ID() {
        return DV_ID;
    }

    public void setDV_ID(String DV_ID) {
        this.DV_ID = DV_ID;
    }

    public String getDV_DESCR() {
        return DV_DESCR;
    }

    public void setDV_DESCR(String DV_DESCR) {
        this.DV_DESCR = DV_DESCR;
    }

    public String getCO_ID() {
        return CO_ID;
    }

    public void setCO_ID(String CO_ID) {
        this.CO_ID = CO_ID;
    }

    public String getCO_DESCR() {
        return CO_DESCR;
    }

    public void setCO_DESCR(String CO_DESCR) {
        this.CO_DESCR = CO_DESCR;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getLD_VL() {
        return LD_VL;
    }

    public void setLD_VL(String LD_VL) {
        this.LD_VL = LD_VL;
    }

    public String getVER_TIME() {
        return VER_TIME;
    }

    public void setVER_TIME(String VER_TIME) {
        this.VER_TIME = VER_TIME;
    }

    public String getNEWREAD() {
        return NEWREAD;
    }

    public void setNEWREAD(String NEWREAD) {
        this.NEWREAD = NEWREAD;
    }
}
