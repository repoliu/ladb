package com.dky.entity.influ.vo;

import java.io.Serializable;
import java.util.Date;

/*

用来返回查询雨量的返回数据，数据库没有这个表
 */
public class RainSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String time;//时间

    private String rain;//降雨量

    private String temperature;//温度

    private String wp;//风力

    private String rains;//降雨级别

    private String rainAll;//这个代表了全天的降雨量

    public String getRainAll() {       return rainAll;    }

    public void setRainAll(String rainAll) {        this.rainAll = rainAll;    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }

    public String getRains() {
        return rains;
    }

    public void setRains(String rains) {
        this.rains = rains;
    }
}
