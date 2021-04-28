package com.fd.test.biz.service;

import com.fd.test.biz.dao.ICustomersDao;
import com.fd.test.biz.dao.ITrainningDao;

public interface IBaseService {
	public ITrainningDao getTrainningDao();

	ICustomersDao getCustomersDao();
}
