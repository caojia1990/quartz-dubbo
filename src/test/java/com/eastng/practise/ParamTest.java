package com.eastng.practise;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eastng.practise.controller.DubboController;
import com.eastng.practise.vo.ParamVo;

public class ParamTest {

	@Test
	public void getParamByInterfaceNameAndMethodNameTest(){
		DubboController controller = new DubboController();
		try {
			List<ParamVo> params = controller.getParamByInterfaceNameAndMethodName(
					"com.eastng.practise.example1.HelloService", "sayHello");
			System.out.println(params);
			//param = "[{\"paramType\":\"java.lang.String\",\"paramValue\":\"\\\"\\\"\"},{\"paramType\":\"com.eastng.practise.vo.DubboVo\",\"paramValue\":\"{\\\"address\\\":\\\"localhost\\\",\\\"interfaceName\\\":\\\"123\\\",\\\"methodName\\\":null,\\\"port\\\":null,\\\"protocol\\\":null,\\\"timeout\\\":null,\\\"version\\\":null}\"}]";
			
			//System.out.println(param);
			
			/*JSONArray jsonArray = JSON.parseArray(param);
			
			for (Object jsonObject : jsonArray) {
				ParamVo paramVo = (ParamVo) JSON.parseObject(jsonObject.toString(),ParamVo.class);
				Class<?> clazz = Class.forName(paramVo.getParamType());
				Object o = JSON.parseObject(paramVo.getParamValue(), clazz);
				System.out.println(JSON.toJSONString(o));
			}*/
			
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
	
	@Test
	public void test(){
		
	}
}
