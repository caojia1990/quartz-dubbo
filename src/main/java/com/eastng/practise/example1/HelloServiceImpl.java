package com.eastng.practise.example1;

import org.apache.log4j.Logger;

import com.eastng.practise.vo.DubboVo;

public class HelloServiceImpl implements HelloService{
	
	static private Logger logger = Logger.getLogger(HelloServiceImpl.class);
	
	@Override
	public void sayHello(String name,DubboVo vo){
		logger.info("Hello World ! "+name);
	}

}
