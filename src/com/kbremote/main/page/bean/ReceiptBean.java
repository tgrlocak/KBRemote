package com.kbremote.main.page.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ModbusClient.ModbusClient;

import com.kbremote.main.util.AddressUtils;

@ManagedBean(name="receiptBean")
@ViewScoped
public class ReceiptBean extends ModbusBean{

	Logger logger = Logger.getLogger(ReceiptBean.class.getSimpleName());
	
	private int receteSec;
	
	public void writeReceiptValues(){
		logger.entering(this.getClass().getSimpleName(), "writeReceiptValues");

		ModbusClient client = getClient();
		
		if(client != null && client.isConnected()){
			try {
				client.WriteSingleRegister(AddressUtils.RECETE, Integer.valueOf(getReceteSec()));
				setReceteSec(0);
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		
		disconnectClient();
		
		logger.exiting(this.getClass().getSimpleName(), "writeReceiptValues");
	}
	
	public int getReceteSec() {
		if(receteSec <= 0){
			ModbusClient client = getClient();
			
			if(client != null && client.isConnected()){
				try {
					receteSec = client.ReadInputRegisters(AddressUtils.RECETE, 1)[0];
				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		return receteSec;
	}

	public void setReceteSec(int receteSec) {
		this.receteSec = receteSec;
	}
}
