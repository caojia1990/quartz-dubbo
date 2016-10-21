package com.eastng.practise.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eastng.practise.vo.ParamVo;

@Controller
public class DubboController {
    
    @RequestMapping(value="getParameter")
    @ResponseBody
    public List<ParamVo> getParamByInterfaceNameAndMethodName(String interfaceName,
                String methodName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException{
        
        Class<?> clazz = Class.forName(interfaceName);
        
        Method[] methods = clazz.getMethods();
        
        List<ParamVo> list = new ArrayList<ParamVo>();
        
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                Class<?>[] classes = method.getParameterTypes();
                
                for (Class<?> class1 : classes) {
                    ParamVo paramVo = new ParamVo();
                    paramVo.setParamType(class1.getName());
                    list.add(paramVo);
                }
                break;
            }
        }
        
        return list;
    }

}
