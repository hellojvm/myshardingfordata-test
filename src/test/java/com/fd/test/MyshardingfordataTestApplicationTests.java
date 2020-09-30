package com.fd.test;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.fd.myshardingfordata.em.Operate;
import com.fd.myshardingfordata.em.PmType;
import com.fd.myshardingfordata.em.StatisticsType;
import com.fd.myshardingfordata.helper.GroupFun;
import com.fd.myshardingfordata.helper.MyObjectUtils;
import com.fd.myshardingfordata.helper.ObData;
import com.fd.myshardingfordata.helper.Param;
import com.fd.test.biz.dao.ITrainningDao;
import com.fd.test.biz.domain.Trainning;

@SpringBootTest
class MyshardingfordataTestApplicationTests {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Resource
	private ITrainningDao trainningDao;

	@Test
	void save() {
		IntStream.rangeClosed(1, 10).forEach(i -> {

			Trainning pojo = new Trainning(String.valueOf(System.currentTimeMillis()),
					ThreadLocalRandom.current().nextInt(10000), ThreadLocalRandom.current().nextInt(100000),
					ThreadLocalRandom.current().nextDouble(200000), ThreadLocalRandom.current().nextDouble(50000000));
			trainningDao.save(pojo);
			log.info("curId:{}", pojo.getSid());
		});
	}

	@Test
	void update() {
		Integer i = trainningDao
				.update(Param.getParams(new Param("createDate", Operate.GT, LocalDate.now().minusDays(10)),
						new Param("sid", Operate.EQ, 1)), MyObjectUtils.getMap("name", "大大大", "i1", 11, "d1", 20D));
		log.info("修改的行数:{}", i);
	}

	@Test
	void delete() {
		Integer i = trainningDao.delete(Param.getParams(
				new Param("createDate", Operate.GE, LocalDate.now().minusDays(100)), new Param("sid", Operate.EQ, 2)));
		log.info("删除的行数:{}", i);
	}

	@Test
	void get() {
		Trainning o = trainningDao.findFirst(Param.getParams(new Param("sid", Operate.EQ, 1)));
		log.info("name is :{}", o.getName());
	}

	@Test
	void list() {
		List<Trainning> list = trainningDao.getList(1, 20, ObData.getSet(new ObData("sid", true)),
				Param.getParams(new Param("sid", Operate.GT, 3)));
		log.info("list size is :{}", list.size());
	}

	@Test
	void groupby() {
		List<Object[]> groups = trainningDao.getGroupList(1, 30,
				ObData.getSet(new ObData("d2", StatisticsType.SUM, true), new ObData("i1", true)),
				Param.getParams(new Param("d2", Operate.GT, 1, StatisticsType.SUM, PmType.FUN),
						new Param("d1", Operate.GT, 1), new Param("i2", Operate.GE, 10)),
				GroupFun.getSet(new GroupFun(StatisticsType.SUM, "i2"), new GroupFun(StatisticsType.SUM, "d2")), "i1",
				"d1");
		// SELECT SUM(i2),SUM(d2),i1,d1 FROM ( SELECT i1,d1,i2,d2 FROM trainning WHERE
		// d1>? AND i2>=?) gdt GROUP BY i1,d1 ORDER BY SUM(d2) DESC ,i1 DESC LIMIT 0,30
		log.info("返回的对象数组：{}", MyJsonUtils.getJsonString(groups));
	}

	@Test
	void groupbyCount() {
		Long c = trainningDao
				.getGroupbyCount(Param.getParams(new Param("d2", Operate.GT, 1, StatisticsType.SUM, PmType.FUN),
						new Param("d1", Operate.GT, 1), new Param("i2", Operate.GE, 10)), "i1", "d1", "d2");
		log.info("分组数量：{}", c);
	}
}
