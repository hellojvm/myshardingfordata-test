package com.fd.test;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fd.myshardingfordata.helper.ConnectionManager;
import com.fd.myshardingfordata.helper.TransManager;

@SpringBootApplication
public class MyshardingfordataTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyshardingfordataTestApplication.class, args);
	}

	@Resource
	private DataSource dataSource;

	@Primary
	@Bean
	public TransManager transManager() {
		TransManager trans = new TransManager();
		trans.setConnectionManager(connectionManager());
		return trans;
	}

	@Primary
	@Bean
	public ConnectionManager connectionManager() {
		ConnectionManager conm = new ConnectionManager();
		conm.setGenerateDdl(true);
		conm.setShowSql(true);
		conm.setInitConnect("set  names  utf8mb4");
		conm.setDataSource(dataSource);
		conm.setReadDataSources(Arrays.asList(dataSource));
		return conm;
	}

}
