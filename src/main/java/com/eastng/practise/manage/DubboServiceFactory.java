package com.eastng.practise.manage;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.eastng.practise.bean.DubboBean;

@Service("dubboService")
public class DubboServiceFactory {

    public void invoke(DubboBean bean){
        
        ApplicationConfig application = new ApplicationConfig();
        application.setName("test");
        
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(bean.getAddress());
        registry.setPort(bean.getPort());
        registry.setProtocol(bean.getProtocol());
        
        ReferenceConfig<GenericService> reference = new ReferenceConfig
                <GenericService>();
        
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setGeneric(true);
        reference.setRetries(0);
        reference.setInterface(bean.getInterfaceName());
        reference.setTimeout(bean.getTimeout());
        reference.setVersion(bean.getVersion());
        
        GenericService genericService = reference.get();
        
        genericService.$invoke(bean.getMethodName(), bean.getParameterType(), bean.getParameterValue());
        
        reference.destroy();
    }
    
}
