package com.eastng.practise.vo;

public class DubboVo {

	private String address;
    
    private Integer port;
    
    private String protocol;
    
    private String interfaceName;
    
    private String methodName;
    
    private ParamVo paramVo;
    
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

	public ParamVo getParamVo() {
		return paramVo;
	}

	public void setParamVo(ParamVo paramVo) {
		this.paramVo = paramVo;
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
