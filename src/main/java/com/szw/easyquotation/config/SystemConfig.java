package com.szw.easyquotation.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class SystemConfig {

	public static String ZMQ_HOST = "";

	@Autowired
	private Environment env;

	@PostConstruct
	public void init() {
		this.ZMQ_HOST = env.getProperty("marketdata.zeromq.host");
	}

}
