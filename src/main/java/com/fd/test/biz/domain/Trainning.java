package com.fd.test.biz.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fd.myshardingfordata.annotation.ColumnRule;
import com.fd.myshardingfordata.em.RuleType;

public class Trainning {
	@Id
	@ColumnRule(ruleType = RuleType.RANGE, value = 14)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long sid;
	private String name;
	private Integer i1;
	private Integer i2;
	private Double d1;
	private Double d2;
	private Date createDate = new Date();

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Trainning() {
		super();
	}

	public Trainning(String name, Integer i1, Integer i2, Double d1, Double d2) {
		super();
		this.name = name;
		this.i1 = i1;
		this.i2 = i2;
		this.d1 = d1;
		this.d2 = d2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getI1() {
		return i1;
	}

	public void setI1(Integer i1) {
		this.i1 = i1;
	}

	public Integer getI2() {
		return i2;
	}

	public void setI2(Integer i2) {
		this.i2 = i2;
	}

	public Double getD1() {
		return d1;
	}

	public void setD1(Double d1) {
		this.d1 = d1;
	}

	public Double getD2() {
		return d2;
	}

	public void setD2(Double d2) {
		this.d2 = d2;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
