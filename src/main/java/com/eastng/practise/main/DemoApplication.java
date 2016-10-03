package com.eastng.practise.main;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.eastng.practise.example1.HelloService;
import com.eastng.practise.example1.HelloServiceImpl;

public class DemoApplication {

	public static void main(String[] args) {
		
		ApplicationConfig application = new ApplicationConfig();
		
		application.setName("demo");
		
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("localhost");
		registry.setPort(2181);
		registry.setProtocol("zookeeper");
		
		ServiceConfig<HelloService> service = new ServiceConfig
				<HelloService>();
		service.setApplication(application);
		service.setRegistry(registry);
		service.setInterface(HelloService.class);
		
		HelloService helloService = new HelloServiceImpl();
		service.setRef(helloService);
		
		service.export();
		
		synchronized (DemoApplication.class) {
			try {
				DemoApplication.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
