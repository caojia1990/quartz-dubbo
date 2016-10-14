package com.eastng.practise;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.eastng.practise.controller.DubboController;
import com.eastng.practise.vo.ParamVo;

public class ParamTest {

	@Test
	public void getParamByInterfaceNameAndMethodName(){
		DubboController controller = new DubboController();
		try {
			List<ParamVo> list = controller.getParamByInterfaceNameAndMethodName(
					"com.eastng.practise.example1.HelloService", "sayHello");
			System.out.println(JSON.toJSONString(list));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
