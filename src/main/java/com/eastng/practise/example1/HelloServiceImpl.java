package com.eastng.practise.example1;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.eastng.practise.bean.DubboBean;

public class HelloServiceImpl implements HelloService{
	
	static private Logger logger = Logger.getLogger(HelloServiceImpl.class);
	
	@Override
	public void sayHello(String name,DubboBean bean,String s){
		logger.info("Hello World ! "+name+JSON.toJSONString(bean));
	}

}
