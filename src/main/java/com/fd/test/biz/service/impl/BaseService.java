package com.fd.test.biz.service.impl;

import javax.annotation.Resource;

import com.fd.test.biz.dao.ITrainningDao;
import com.fd.test.biz.service.IBaseService;

public class BaseService implements IBaseService {
	@Resource
	private ITrainningDao trainningDao;

	@Override
	public ITrainningDao getTrainningDao() {
		return trainningDao;
	}
}
