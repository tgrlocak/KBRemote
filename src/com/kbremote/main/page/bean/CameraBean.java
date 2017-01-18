package com.kbremote.main.page.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.kbremote.main.util.AddressUtils;

@ManagedBean(name = "cameraBean")
@ViewScoped
public class CameraBean {

	private String clientIP;
	
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String updateVersion(){
		version++;
		return "";
	}

	public String getClientIP() {
		if(StringUtils.isEmpty(clientIP)){
			clientIP = AddressUtils.getLocalIP();
		}
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	
}
