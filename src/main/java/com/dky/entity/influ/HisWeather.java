package com.dky.entity.influ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisWeather implements Serializable {
	private Integer area;

	private String mdate;

	private Long id;

	private String areaname;

	private String weatherType;

	private BigDecimal maxTemperature;

	private BigDecimal minTemperature;

	private String rainfall;

	private BigDecimal humidity;

	private Integer windpower;

	private String windway;

	private BigDecimal pressure;

	private String cosiness;

	private Date verTime;

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname == null ? null : areaname.trim();
	}

	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType == null ? null : weatherType.trim();
	}

	public BigDecimal getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(BigDecimal maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public BigDecimal getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(BigDecimal minTemperature) {
		this.minTemperature = minTemperature;
	}

	public String getRainfall() {
		return rainfall;
	}

	public void setRainfall(String rainfall) {
		this.rainfall = rainfall == null ? null : rainfall.trim();
	}

	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	public Integer getWindpower() {
		return windpower;
	}

	public void setWindpower(Integer windpower) {
		this.windpower = windpower;
	}

	public String getWindway() {
		return windway;
	}

	public void setWindway(String windway) {
		this.windway = windway == null ? null : windway.trim();
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	public String getCosiness() {
		return cosiness;
	}

	public void setCosiness(String cosiness) {
		this.cosiness = cosiness == null ? null : cosiness.trim();
	}

	public Date getVerTime() {
		return verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}
}