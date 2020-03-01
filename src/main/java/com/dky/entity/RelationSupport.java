package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/*
这个表是关联分析表
 */
public class RelationSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer area;//地区id

	private String mdate;//时间

	private Long id;//id没有用到，但是在数据库是必填项

	private String areaname;//地区名称

	private String relationType;//这个是用电的行业

	private BigDecimal relationDegree;//这个是各行业用电的比例是小数

	private Date verTime;//这个没有用到

	private String minDate;//这个在数据库中是没有的，我用来做查询用一下的

	public String getMinDate() {        return minDate;    }
	public void setMinDate(String minDate) {        this.minDate = minDate;    }

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

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType == null ? null : relationType.trim();
	}

	public BigDecimal getRelationDegree() {
		return relationDegree;
	}

	public void setRelationDegree(BigDecimal relationDegree) {
		this.relationDegree = relationDegree;
	}

	public Date getVerTime() {
		return verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}
}