package com.dky.entity.influ;

import java.io.Serializable;

public class Qstation implements Serializable {
    private String stcd;//地区编号

    private String code;

    private String weatherCode;

    private String stnm;//地区名称

    private Long numlong;

    private Long numlati;

    private Long longData;

    private Long lati;

    private String state;

    private String area;

    private String epro;

    private String province;

    private String pronm;

    private String disnm;

    private String counnm;

    private String enName;

    private String basinnm;

    private String subnm;

    private String isDiscapital;

    private String subtypeCit;

    private String subtypeDis;

    private String subtypePro;

    private String subtypeArea;

    private String isProcapital;

    private String sortid;

    private Long x;

    private Long y;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd == null ? null : stcd.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode == null ? null : weatherCode.trim();
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm == null ? null : stnm.trim();
    }

    public Long getNumlong() {
        return numlong;
    }

    public void setNumlong(Long numlong) {
        this.numlong = numlong;
    }

    public Long getNumlati() {
        return numlati;
    }

    public void setNumlati(Long numlati) {
        this.numlati = numlati;
    }

    public Long getLongData() {
        return longData;
    }

    public void setLongData(Long longData) {
        this.longData = longData;
    }

    public Long getLati() {
        return lati;
    }

    public void setLati(Long lati) {
        this.lati = lati;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getEpro() {
        return epro;
    }

    public void setEpro(String epro) {
        this.epro = epro == null ? null : epro.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getPronm() {
        return pronm;
    }

    public void setPronm(String pronm) {
        this.pronm = pronm == null ? null : pronm.trim();
    }

    public String getDisnm() {
        return disnm;
    }

    public void setDisnm(String disnm) {
        this.disnm = disnm == null ? null : disnm.trim();
    }

    public String getCounnm() {
        return counnm;
    }

    public void setCounnm(String counnm) {
        this.counnm = counnm == null ? null : counnm.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getBasinnm() {
        return basinnm;
    }

    public void setBasinnm(String basinnm) {
        this.basinnm = basinnm == null ? null : basinnm.trim();
    }

    public String getSubnm() {
        return subnm;
    }

    public void setSubnm(String subnm) {
        this.subnm = subnm == null ? null : subnm.trim();
    }

    public String getIsDiscapital() {
        return isDiscapital;
    }

    public void setIsDiscapital(String isDiscapital) {
        this.isDiscapital = isDiscapital == null ? null : isDiscapital.trim();
    }

    public String getSubtypeCit() {
        return subtypeCit;
    }

    public void setSubtypeCit(String subtypeCit) {
        this.subtypeCit = subtypeCit == null ? null : subtypeCit.trim();
    }

    public String getSubtypeDis() {
        return subtypeDis;
    }

    public void setSubtypeDis(String subtypeDis) {
        this.subtypeDis = subtypeDis == null ? null : subtypeDis.trim();
    }

    public String getSubtypePro() {
        return subtypePro;
    }

    public void setSubtypePro(String subtypePro) {
        this.subtypePro = subtypePro == null ? null : subtypePro.trim();
    }

    public String getSubtypeArea() {
        return subtypeArea;
    }

    public void setSubtypeArea(String subtypeArea) {
        this.subtypeArea = subtypeArea == null ? null : subtypeArea.trim();
    }

    public String getIsProcapital() {
        return isProcapital;
    }

    public void setIsProcapital(String isProcapital) {
        this.isProcapital = isProcapital == null ? null : isProcapital.trim();
    }

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid == null ? null : sortid.trim();
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}