package com.kbremote.main.page.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ModbusClient.ModbusClient;

import com.kbremote.main.model.Angle;
import com.kbremote.main.util.AddressUtils;

@ManagedBean
@ViewScoped
public class AngleSetupBean extends ModbusBean{

	private List<Angle> angles;

	public List<Angle> getAngles() {
		if(CollectionUtils.isEmpty(angles)){
			angles = new ArrayList<Angle>();
			
			angles.add(getAngle("1"));
			angles.add(getAngle("2"));
			angles.add(getAngle("3"));
			angles.add(getAngle("4"));
		}
		return angles;
	}

	public void setAngles(List<Angle> angles) {
		this.angles = angles;
	}
	
	public Angle getAngle(String tabancaNo){
		Angle angle = new Angle();
		ModbusClient client = getClient();
		
		switch (tabancaNo) {
		case "1":
			angle.setTabanca("1. Tabanca");
			
			angle.setSistemAalfa(getModbusValue(client, AddressUtils.TABANCA_1_A_ALFA));
			angle.setSistemAbeta(getModbusValue(client, AddressUtils.TABANCA_1_A_BETA));
			angle.setSistemAh(getModbusValue(client, AddressUtils.TABANCA_1_A_YUKSEKLIK));
			
			angle.setSistemBalfa(getModbusValue(client, AddressUtils.TABANCA_1_B_ALFA));
			angle.setSistemBbeta(getModbusValue(client, AddressUtils.TABANCA_1_B_BETA));
			angle.setSistemBh(getModbusValue(client, AddressUtils.TABANCA_1_B_YUKSEKLIK));
			
			angle.setSistemCalfa(getModbusValue(client, AddressUtils.TABANCA_1_C_ALFA));
			angle.setSistemCbeta(getModbusValue(client, AddressUtils.TABANCA_1_C_BETA));
			angle.setSistemCh(getModbusValue(client, AddressUtils.TABANCA_1_C_YUKSEKLIK));
			
			break;
		case "2":
			angle.setTabanca("2. Tabanca");
			
			angle.setSistemAalfa(getModbusValue(client, AddressUtils.TABANCA_2_A_ALFA));
			angle.setSistemAbeta(getModbusValue(client, AddressUtils.TABANCA_2_A_BETA));
			angle.setSistemAh(getModbusValue(client, AddressUtils.TABANCA_2_A_YUKSEKLIK));
			
			angle.setSistemBalfa(getModbusValue(client, AddressUtils.TABANCA_2_B_ALFA));
			angle.setSistemBbeta(getModbusValue(client, AddressUtils.TABANCA_2_B_BETA));
			angle.setSistemBh(getModbusValue(client, AddressUtils.TABANCA_2_B_YUKSEKLIK));
			
			angle.setSistemCalfa(getModbusValue(client, AddressUtils.TABANCA_2_C_ALFA));
			angle.setSistemCbeta(getModbusValue(client, AddressUtils.TABANCA_2_C_BETA));
			angle.setSistemCh(getModbusValue(client, AddressUtils.TABANCA_2_C_YUKSEKLIK));
			break;
		case "3":
			angle.setTabanca("3. Tabanca");
			
			angle.setSistemAalfa(getModbusValue(client, AddressUtils.TABANCA_3_A_ALFA));
			angle.setSistemAbeta(getModbusValue(client, AddressUtils.TABANCA_3_A_BETA));
			angle.setSistemAh(getModbusValue(client, AddressUtils.TABANCA_3_A_YUKSEKLIK));
			
			angle.setSistemBalfa(getModbusValue(client, AddressUtils.TABANCA_3_B_ALFA));
			angle.setSistemBbeta(getModbusValue(client, AddressUtils.TABANCA_3_B_BETA));
			angle.setSistemBh(getModbusValue(client, AddressUtils.TABANCA_3_B_YUKSEKLIK));
			
			angle.setSistemCalfa(getModbusValue(client, AddressUtils.TABANCA_3_C_ALFA));
			angle.setSistemCbeta(getModbusValue(client, AddressUtils.TABANCA_3_C_BETA));
			angle.setSistemCh(getModbusValue(client, AddressUtils.TABANCA_3_C_YUKSEKLIK));
			break;
		case "4":
			angle.setTabanca("4. Tabanca");
			
			angle.setSistemAalfa(getModbusValue(client, AddressUtils.TABANCA_4_A_ALFA));
			angle.setSistemAbeta(getModbusValue(client, AddressUtils.TABANCA_4_A_BETA));
			angle.setSistemAh(getModbusValue(client, AddressUtils.TABANCA_4_A_YUKSEKLIK));
			
			angle.setSistemBalfa(getModbusValue(client, AddressUtils.TABANCA_4_B_ALFA));
			angle.setSistemBbeta(getModbusValue(client, AddressUtils.TABANCA_4_B_BETA));
			angle.setSistemBh(getModbusValue(client, AddressUtils.TABANCA_4_B_YUKSEKLIK));
			
			angle.setSistemCalfa(getModbusValue(client, AddressUtils.TABANCA_4_C_ALFA));
			angle.setSistemCbeta(getModbusValue(client, AddressUtils.TABANCA_4_C_BETA));
			angle.setSistemCh(getModbusValue(client, AddressUtils.TABANCA_4_C_YUKSEKLIK));
			break;
		default:
			break;
		}
		disconnectClient();
		return angle;
	}
	
	public int getModbusValue(ModbusClient client, int address){
		int value = 0;
		
		try {
			if(client != null){
				value = client.ReadHoldingRegisters(address, 1) != null ? client.ReadHoldingRegisters(address, 1)[0] : 0;
			} else {
				value = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			value = 0;
		}
		return value;
	}
	
}
