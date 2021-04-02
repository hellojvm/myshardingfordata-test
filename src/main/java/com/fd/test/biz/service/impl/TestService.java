package com.fd.test.biz.service.impl;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fd.myshardingfordata.annotation.MyTransaction;
import com.fd.myshardingfordata.em.StatisticsType;
import com.fd.test.biz.domain.Trainning;
import com.fd.test.biz.service.ITestService;

@Service
public class TestService extends BaseService implements ITestService {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	@MyTransaction
	public void testtransactional() {
		log.info("begin:{}", getTrainningDao().getStatisticsValue(null, "d1", StatisticsType.SUM));
		getTrainningDao().save(new Trainning("testtransaction", 1, 1, 10d, 10d));
		log.info("end:{}", getTrainningDao().getStatisticsValue(null, "d1", StatisticsType.SUM));
	}
}
