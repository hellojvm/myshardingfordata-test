package com.fd.test.biz.service.impl;

import javax.annotation.Resource;

import com.fd.test.biz.dao.ICustomersDao;
import com.fd.test.biz.dao.ITrainningDao;
import com.fd.test.biz.service.IBaseService;

public class BaseService implements IBaseService {
	@Resource
	private ITrainningDao trainningDao;
	@Resource
	private  ICustomersDao  customersDao ;

	@Override
	public ITrainningDao getTrainningDao() {
		return trainningDao;
	}
	@Override
	public ICustomersDao getCustomersDao() {
		return customersDao;
	}
}
