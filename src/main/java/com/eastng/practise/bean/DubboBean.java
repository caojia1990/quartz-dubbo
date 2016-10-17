package com.eastng.practise.bean;

import java.io.Serializable;

public class DubboBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 4891881577454086894L;
    
    private String address;
    
    private Integer port;
    
    private String protocol;
    
    private String interfaceName;
    
    private String methodName;
    
    private String[] parameterType;
    
    private Object[] parameterValue;
    
    private Integer timeout;
    
    private String version;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getParameterType() {
        return parameterType;
    }

    public void setParameterType(String[] parameterType) {
        this.parameterType = parameterType;
    }

    public Object[] getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Object[] parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
    

}
