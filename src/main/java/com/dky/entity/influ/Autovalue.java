package com.dky.entity.influ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Autovalue implements Serializable {
	private Date time;

	private Date timeMin;

	private String stcd;//地区的id

	private Short temperature;//温度

	private Short rain;//降雨量

	private BigDecimal rainMax;
	private BigDecimal rainMin;



	private Short enpresure;

	private Short wp;//风力

	private Short wd;//风向

	private Short humidity;//湿度

	private Short vtype;

	private String stringDate;

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public BigDecimal getRainMax() {		return rainMax;	}

	public void setRainMax(BigDecimal rainMax) {		this.rainMax = rainMax;	}

	public void setRainMin(BigDecimal rainMin) {		this.rainMin = rainMin;	}

	public BigDecimal getRainMin() {		return rainMin;	}

	public Date getTimeMin() {		return timeMin;	}

	public void setTimeMin(Date timeMin) {		this.timeMin = timeMin;	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd == null ? null : stcd.trim();
	}

	public Short getTemperature() {
		return temperature;
	}

	public void setTemperature(Short temperature) {
		this.temperature = temperature;
	}

	public Short getRain() {
		return rain;
	}

	public void setRain(Short rain) {
		this.rain = rain;
	}

	public Short getEnpresure() {
		return enpresure;
	}

	public void setEnpresure(Short enpresure) {
		this.enpresure = enpresure;
	}

	public Short getWp() {
		return wp;
	}

	public void setWp(Short wp) {
		this.wp = wp;
	}

	public Short getWd() {
		return wd;
	}

	public void setWd(Short wd) {
		this.wd = wd;
	}

	public Short getHumidity() {
		return humidity;
	}

	public void setHumidity(Short humidity) {
		this.humidity = humidity;
	}

	public Short getVtype() {
		return vtype;
	}

	public void setVtype(Short vtype) {
		this.vtype = vtype;
	}
}