package com.eastng.practise;

import javax.annotation.Resource;

import org.junit.Test;

import com.eastng.practise.base.BaseJunitNoQuartzTest;
import com.eastng.practise.bean.DubboBean;
import com.eastng.practise.manage.DubboServiceFactory;

public class DubboServiceTest extends BaseJunitNoQuartzTest {
	
	@Resource
	private DubboServiceFactory dubboService;
	
	
	@Test
	public void invokeTest(){
		
		DubboBean bean = new DubboBean();
		
		bean.setAddress("localhost");
		bean.setProtocol("zookeeper");
		bean.setPort(2181);
		
		bean.setInterfaceName("com.eastng.practise.example1.HelloService");
		bean.setMethodName("sayHello");
		
		this.dubboService.invoke(bean);
	}

}
