package com.fd.test.biz.dao.impl;

import com.fd.test.biz.domain.Customers;

import org.springframework.stereotype.Repository;

import com.fd.myshardingfordata.dao.base.impl.DaoShardingBase;

import com.fd.test.biz.dao.ICustomersDao;


@Repository
public class CustomersDao extends DaoShardingBase<Customers> implements ICustomersDao {

}