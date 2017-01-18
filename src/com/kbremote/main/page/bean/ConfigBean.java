package com.kbremote.main.page.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ModbusClient.ModbusClient;

import com.kbremote.main.model.ReadObject;
import com.kbremote.main.util.AddressUtils;

@ManagedBean(name = "configBean")
@ViewScoped
public class ConfigBean {

	private int activeTab = 0;

	private List<ReadObject> writeObjectList;

	public List<ReadObject> getWriteObjectList() {
		if (CollectionUtils.isEmpty(writeObjectList)) {
			writeObjectList = new ArrayList<ReadObject>();

			try {
				ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);
				client.Connect();

				/*
				writeObjectList
						.add(new ReadObject(
								"TABANCA SAÐ DÜZELTMESÝ",
								"word",
								"mm",
								getModbusValue(
										client,
										AddressUtils.TABANCA_SAG_DUZELTME,
										"word"),
								AddressUtils.TABANCA_SAG_DUZELTME));
				writeObjectList
						.add(new ReadObject(
								"TABANCA SOL DÜZELTMESÝ",
								"word",
								"mm",
								getModbusValue(
										client,
										AddressUtils.TABANCA_SOL_DUZELTME,
										"word"),
								AddressUtils.TABANCA_SOL_DUZELTME));
				writeObjectList.add(new ReadObject("MAX BOYAMA ALANI", "word",
						"mm", "", 0));
				writeObjectList.add(new ReadObject("MIN BOYAMA ALANI", "word",
						"mm", "", 0));
				writeObjectList.add(new ReadObject("MAX FOTOSEL ÇALIÞMA ALANI",
						"word", "mm", "", 0));
				writeObjectList.add(new ReadObject("MÝN FOTOSEL ÇALIÞMA ALANI",
						"word", "mm", "", 0));
				writeObjectList.add(new ReadObject(
						"SENSÖR ÝLE 1. TABANCA ARASI", "word", "mm", "", 0));
				writeObjectList.add(new ReadObject(
						"SENSÖR ÝLE 2. TABANCA ARASI", "word", "mm", "", 0));
				writeObjectList.add(new ReadObject("SERVO HIZLANMA RAMPASI",
						"word", "mm", "", 0));
				writeObjectList.add(new ReadObject("SERVO YAVAÞLAMA RAMPASI",
						"word", "mm", "", 0));
				writeObjectList.add(new ReadObject("SENSÖR DÜZELTME", "word",
						"mm", "", 0));
				writeObjectList.add(new ReadObject("POMPA BEKLEME SÜRESÝ",
						"word", "dk", "", 0));
				writeObjectList.add(new ReadObject("POMPA ÇALIÞMA SÜRESÝ",
						"word", "sn", "", 0));
				writeObjectList.add(new ReadObject("SENSÖR ZAMANLAYICI TON",
						"bool", "", "", 0));
				writeObjectList.add(new ReadObject("SENSÖR ZAMANLAYICI TOFF",
						"bool", "", "", 0));
				writeObjectList.add(new ReadObject("POMPA HAVA BASINCI LÝMÝTÝ",
						"word", "", "", 0));
				*/

				client.Disconnect();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return writeObjectList != null ? writeObjectList
				: new ArrayList<ReadObject>();
	}

	public void setWriteObjectList(List<ReadObject> writeObjectList) {
		this.writeObjectList = writeObjectList;
	}

	public String getModbusValue(ModbusClient client, int address, String type) {
		String value = "";

		try {

			if (client.isConnected()) {
				if (type.equals("bool")) {
					boolean[] coils = client.ReadCoils(address, 1);
					if (coils.length > 0) {
						value = coils[0] ? "true" : "false";
					}
				} else if (type.equals("word")) {
					int[] regs = client.ReadHoldingRegisters(address, 1);
					if (regs.length > 0) {
						value = regs[0] + "";
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return value;

	}

	public String writeValues() {
		try {
			ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);

			client.Connect();

			if (CollectionUtils.isNotEmpty(getWriteObjectList())) {
				for (ReadObject obj : getWriteObjectList()) {
					if (obj.getAddress().getType().equals("M")) {
						client.WriteSingleCoil(obj.getAddress().getValue(), obj.getValue()
								.equals("true"));
					} else if (obj.getAddress().getType().equals("D")) {
						if(obj.getValue() == null || obj.getValue().equals("")){
							obj.setValue("0");
						}
						
						client.WriteSingleRegister(obj.getAddress().getValue(), Integer.parseInt(obj.getValue()));
					}
				}
			}

			client.Disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setWriteObjectList(null);
		
		return "";
	}

	private String tabancaSagDuzeltmesi;
	private String tabancaSolDuzeltmesi;
	private String maxBoyamaAlani;
	private String minBoyamaAlani;
	private String maxFotoselCalismaAlani;
	private String minFotoselCalismaAlani;
	private String sensor1TabancaArasi;
	private String sensor2TabancaArasi;
	private String servoHizlanmaRampasi;
	private String servoYavaslamaRampasi;
	private String sensorDuzeltme;
	private String pompaBeklemeSuresi;
	private String pompaCalismaSuresi;
	private boolean sensorZamanlayiciTOn;
	private boolean sensorZamanlayiciTOff;
	private String pompaHavaBasinciLimiti;

	public String getTabancaSagDuzeltmesi() {
		return tabancaSagDuzeltmesi;
	}

	public void setTabancaSagDuzeltmesi(String tabancaSagDuzeltmesi) {
		this.tabancaSagDuzeltmesi = tabancaSagDuzeltmesi;
	}

	public String getTabancaSolDuzeltmesi() {
		return tabancaSolDuzeltmesi;
	}

	public void setTabancaSolDuzeltmesi(String tabancaSolDuzeltmesi) {
		this.tabancaSolDuzeltmesi = tabancaSolDuzeltmesi;
	}

	public String getMaxBoyamaAlani() {
		return maxBoyamaAlani;
	}

	public void setMaxBoyamaAlani(String maxBoyamaAlani) {
		this.maxBoyamaAlani = maxBoyamaAlani;
	}

	public String getMinBoyamaAlani() {
		return minBoyamaAlani;
	}

	public void setMinBoyamaAlani(String minBoyamaAlani) {
		this.minBoyamaAlani = minBoyamaAlani;
	}

	public String getMaxFotoselCalismaAlani() {
		return maxFotoselCalismaAlani;
	}

	public void setMaxFotoselCalismaAlani(String maxFotoselCalismaAlani) {
		this.maxFotoselCalismaAlani = maxFotoselCalismaAlani;
	}

	public String getMinFotoselCalismaAlani() {
		return minFotoselCalismaAlani;
	}

	public void setMinFotoselCalismaAlani(String minFotoselCalismaAlani) {
		this.minFotoselCalismaAlani = minFotoselCalismaAlani;
	}

	public String getSensor1TabancaArasi() {
		return sensor1TabancaArasi;
	}

	public void setSensor1TabancaArasi(String sensor1TabancaArasi) {
		this.sensor1TabancaArasi = sensor1TabancaArasi;
	}

	public String getSensor2TabancaArasi() {
		return sensor2TabancaArasi;
	}

	public void setSensor2TabancaArasi(String sensor2TabancaArasi) {
		this.sensor2TabancaArasi = sensor2TabancaArasi;
	}

	public String getServoHizlanmaRampasi() {
		return servoHizlanmaRampasi;
	}

	public void setServoHizlanmaRampasi(String servoHizlanmaRampasi) {
		this.servoHizlanmaRampasi = servoHizlanmaRampasi;
	}

	public String getServoYavaslamaRampasi() {
		return servoYavaslamaRampasi;
	}

	public void setServoYavaslamaRampasi(String servoYavaslamaRampasi) {
		this.servoYavaslamaRampasi = servoYavaslamaRampasi;
	}

	public String getSensorDuzeltme() {
		return sensorDuzeltme;
	}

	public void setSensorDuzeltme(String sensorDuzeltme) {
		this.sensorDuzeltme = sensorDuzeltme;
	}

	public String getPompaBeklemeSuresi() {
		return pompaBeklemeSuresi;
	}

	public void setPompaBeklemeSuresi(String pompaBeklemeSuresi) {
		this.pompaBeklemeSuresi = pompaBeklemeSuresi;
	}

	public String getPompaCalismaSuresi() {
		return pompaCalismaSuresi;
	}

	public void setPompaCalismaSuresi(String pompaCalismaSuresi) {
		this.pompaCalismaSuresi = pompaCalismaSuresi;
	}

	public boolean isSensorZamanlayiciTOn() {
		return sensorZamanlayiciTOn;
	}

	public void setSensorZamanlayiciTOn(boolean sensorZamanlayiciTOn) {
		this.sensorZamanlayiciTOn = sensorZamanlayiciTOn;
	}

	public boolean isSensorZamanlayiciTOff() {
		return sensorZamanlayiciTOff;
	}

	public void setSensorZamanlayiciTOff(boolean sensorZamanlayiciTOff) {
		this.sensorZamanlayiciTOff = sensorZamanlayiciTOff;
	}

	public String getPompaHavaBasinciLimiti() {
		return pompaHavaBasinciLimiti;
	}

	public void setPompaHavaBasinciLimiti(String pompaHavaBasinciLimiti) {
		this.pompaHavaBasinciLimiti = pompaHavaBasinciLimiti;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

}
