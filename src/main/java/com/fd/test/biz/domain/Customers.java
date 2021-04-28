package com.fd.test.biz.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private Long orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Customers(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public Customers() {
		super();
	}
}
