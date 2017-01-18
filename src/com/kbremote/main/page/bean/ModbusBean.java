package com.kbremote.main.page.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kbremote.main.util.AddressUtils;

import ModbusClient.ModbusClient;

@ManagedBean(name="modbusBean")
@SessionScoped
public class ModbusBean {

	Logger logger = Logger.getLogger(ModbusBean.class.getSimpleName());
	
	private ModbusClient client;
	
	private boolean remoteSet = false;

	public ModbusClient getClient() {
		logger.entering(ModbusBean.class.getSimpleName(), "getClient()");
		if(client == null || !client.isConnected()){
			client = new ModbusClient(AddressUtils.getClientIP(), 502);
			try {
				client.Connect();
				client.setConnectionTimeout(10000);
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		logger.exiting(ModbusBean.class.getSimpleName(), "getClient()", client);
		return client;
	}

	public void disconnectClient() {
		if(client != null && client.isConnected()){
			try {
				client.Disconnect();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	public boolean isRemoteSet() {
		return remoteSet;
	}

	public void setRemoteSet(boolean remoteSet) {
		this.remoteSet = remoteSet;
	}

}
