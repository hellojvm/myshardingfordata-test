package com.fd.test.biz.dao.impl;

import com.fd.test.biz.domain.Trainning;

import org.springframework.stereotype.Repository;

import com.fd.myshardingfordata.dao.base.impl.DaoShardingBase;

import com.fd.test.biz.dao.ITrainningDao;


@Repository
public class TrainningDao extends DaoShardingBase<Trainning> implements ITrainningDao {

}