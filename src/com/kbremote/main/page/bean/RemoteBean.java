package com.kbremote.main.page.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kbremote.main.util.AddressUtils;

import ModbusClient.ModbusClient;

@ManagedBean(name = "remoteBean")
@ViewScoped
public class RemoteBean extends ModbusBean{

	Logger logger = Logger.getLogger(RemoteBean.class.getSimpleName());
	
	private float konveyorHizi;
	private boolean konveyorOpen;

	private boolean salinimOpen;
	private float salinimKoluHizi;

	private boolean kaziyiciOpen;

	private boolean havalandirmaOpen;
	private float havalandirmaDebi;

	private boolean boyamaOpen;
	private int boyamaCalisma;

	private boolean pompaOpen;
	private int pompaBasinci;

	private boolean tozAlmaOpen;

	private boolean kolTabancaAOpen;
	private boolean kolTabancaBOpen;
	private boolean kolTabancaCOpen;
	private String kolTabanca;

	@PostConstruct
	public void init() {
		if (!isRemoteSet()) {
			ModbusClient client = getClient();

			if(client != null && client.isConnected()){
				try {
					konveyorOpen = client.ReadCoils(
							AddressUtils.KONVEYOR_HIZI, 1)[0];
					konveyorHizi = ((float) client.ReadHoldingRegisters(
							AddressUtils.KONVEYOR_HIZI, 1)[0]) / 10;
	
					salinimOpen = client.ReadCoils(
							AddressUtils.SALINIM_KOLU_ON, 1)[0];
					salinimKoluHizi = client.ReadHoldingRegisters(
							AddressUtils.SALINIM_KOL_HIZI, 1)[0] / 30;
	
					kaziyiciOpen = client.ReadCoils(
							AddressUtils.KAZIMA_UNITESI_ON, 1)[0];
	
					havalandirmaOpen = client.ReadCoils(
							AddressUtils.HAVALANDIRMA_ON, 1)[0];
					havalandirmaDebi = ((float) client.ReadHoldingRegisters(
							AddressUtils.HAVALANDIRMA_DEBISI, 1)[0]) / 100;
	
					boolean standart = client.ReadCoils(
							AddressUtils.BOYA_ATIMI_S_ON, 1)[0];
					boolean yogun = client.ReadCoils(
							AddressUtils.BOYA_ATIMI_Y_ON, 1)[0];
					boyamaCalisma = (yogun && !standart) ? 111 : 104;
					boyamaOpen = !(client.ReadCoils(
							AddressUtils.BOYA_ATIMI_S_OFF, 1)[0]);
	
					pompaOpen = client.ReadCoils(
							AddressUtils.POMPA_ON, 1)[0];
					pompaBasinci = client.ReadHoldingRegisters(
							AddressUtils.POMPA_BASINCI, 1)[0];
	
					tozAlmaOpen = client.ReadCoils(
							AddressUtils.TOZ_ALMA_UNITESI_ON, 1)[0];
	
					kolTabancaAOpen = client.ReadCoils(
							AddressUtils.KOL_TABANCA_A_ON, 1)[0];
					kolTabancaBOpen = client.ReadCoils(
							AddressUtils.KOL_TABANCA_B_ON, 1)[0];
					kolTabancaCOpen = client.ReadCoils(
							AddressUtils.KOL_TABANCA_C_ON, 1)[0];
	
					setKolTabanca("A");
				} catch(Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
				
				disconnectClient();
				
				setRemoteSet(true);
			}
		}
	}

	public float getKonveyorHizi() {
		return konveyorHizi;
	}

	public void setKonveyorHizi(float konveyorHizi) {
		this.konveyorHizi = konveyorHizi;
	}

	public void switchKonveyor() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(AddressUtils.KONVEYOR_ON,
					!isKonveyorOpen());
			setKonveyorOpen(!isKonveyorOpen());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isKonveyorOpen() {
		return konveyorOpen;
	}

	public void setKonveyorOpen(boolean konveyorOpen) {
		this.konveyorOpen = konveyorOpen;
	}

	public boolean isSalinimOpen() {
		return salinimOpen;
	}

	public void setSalinimOpen(boolean salinimOpen) {
		this.salinimOpen = salinimOpen;
	}

	public float getSalinimKoluHizi() {
		return salinimKoluHizi;
	}

	public void setSalinimKoluHizi(float salinimKoluHizi) {
		this.salinimKoluHizi = salinimKoluHizi;
	}

	public void switchSalinimKolu() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(AddressUtils.SALINIM_KOLU_ON,
					!isSalinimOpen());
			setSalinimOpen(!isSalinimOpen());
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isKaziyiciOpen() {
		return kaziyiciOpen;
	}

	public void setKaziyiciOpen(boolean kaziyiciOpen) {
		this.kaziyiciOpen = kaziyiciOpen;
	}

	public void switchKaziyici() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(AddressUtils.KAZIMA_UNITESI_ON,
					!isKaziyiciOpen());
			
			setKaziyiciOpen(!isKaziyiciOpen());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isHavalandirmaOpen() {
		return havalandirmaOpen;
	}

	public void setHavalandirmaOpen(boolean havalandirmaOpen) {
		this.havalandirmaOpen = havalandirmaOpen;
	}

	public float getHavalandirmaDebi() {
		return havalandirmaDebi;
	}

	public void setHavalandirmaDebi(float havalandirmaDebi) {
		this.havalandirmaDebi = havalandirmaDebi;
	}

	public void switchHavalandirma() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(
					AddressUtils.HAVALANDIRMA_ON,
					!isHavalandirmaOpen());
			
			setHavalandirmaOpen(!isHavalandirmaOpen());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isBoyamaOpen() {
		return boyamaOpen;
	}

	public void setBoyamaOpen(boolean boyamaOpen) {
		this.boyamaOpen = boyamaOpen;
	}

	public int getBoyamaCalisma() {
		return boyamaCalisma;
	}

	public void setBoyamaCalisma(int boyamaCalisma) {
		this.boyamaCalisma = boyamaCalisma;
	}

	public void switchBoyama() {
		ModbusClient client = getClient();

		try {
			if (boyamaCalisma == 111) {
				client.WriteSingleCoil(
						AddressUtils.BOYA_ATIMI_Y_ON,
						!isBoyamaOpen());
			} else {
				client.WriteSingleCoil(
						AddressUtils.BOYA_ATIMI_S_ON,
						!isBoyamaOpen());
			}
			
			setBoyamaOpen(!isBoyamaOpen());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isPompaOpen() {
		return pompaOpen;
	}

	public void setPompaOpen(boolean pompaOpen) {
		this.pompaOpen = pompaOpen;
	}

	public int getPompaBasinci() {
		return pompaBasinci;
	}

	public void setPompaBasinci(int pompaBasinci) {
		this.pompaBasinci = pompaBasinci;
	}

	public void switchPompa() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(AddressUtils.POMPA_ON,
					!isPompaOpen());
			
			setPompaOpen(!isPompaOpen());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isTozAlmaOpen() {
		return tozAlmaOpen;
	}

	public void setTozAlmaOpen(boolean tozAlmaOpen) {
		this.tozAlmaOpen = tozAlmaOpen;
	}

	public void switchTozAlma() {
		ModbusClient client = getClient();

		try {
			client.WriteSingleCoil(AddressUtils.TOZ_ALMA_UNITESI_ON,
					!isTozAlmaOpen());
			
			setTozAlmaOpen(!isTozAlmaOpen());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public boolean isKolTabancaAOpen() {
		return kolTabancaAOpen;
	}

	public void setKolTabancaAOpen(boolean kolTabancaAOpen) {
		this.kolTabancaAOpen = kolTabancaAOpen;
	}

	public boolean isKolTabancaBOpen() {
		return kolTabancaBOpen;
	}

	public void setKolTabancaBOpen(boolean kolTabancaBOpen) {
		this.kolTabancaBOpen = kolTabancaBOpen;
	}

	public boolean isKolTabancaCOpen() {
		return kolTabancaCOpen;
	}

	public void setKolTabancaCOpen(boolean kolTabancaCOpen) {
		this.kolTabancaCOpen = kolTabancaCOpen;
	}

	public String getKolTabanca() {
		return kolTabanca;
	}

	public void setKolTabanca(String kolTabanca) {
		this.kolTabanca = kolTabanca;
	}

	public void switchKolTabanca() {
		ModbusClient client = getClient();

		try {
			if (getKolTabanca().equals("A")) {
				client.WriteSingleCoil(
						AddressUtils.KOL_TABANCA_A_ON,
						!isKolTabancaAOpen());
				
				setKolTabancaAOpen(!isKolTabancaAOpen());
			} else if (getKolTabanca().equals("B")) {
				client.WriteSingleCoil(
						AddressUtils.KOL_TABANCA_B_ON,
						!isKolTabancaBOpen());
				setKolTabancaBOpen(!isKolTabancaBOpen());
			} else if (getKolTabanca().equals("C")) {
				client.WriteSingleCoil(
						AddressUtils.KOL_TABANCA_C_ON,
						!isKolTabancaCOpen());
				setKolTabancaCOpen(!isKolTabancaCOpen());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public String writeValues() {

		try {
			ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);
			client.Connect();
			
			client.WriteSingleRegister(AddressUtils.KONVEYOR_HIZI,
					(int) (getKonveyorHizi() * 10));
			client.WriteSingleRegister(
					AddressUtils.SALINIM_KOL_HIZI,
					(int) (getSalinimKoluHizi() * 30));
			client.WriteSingleRegister(
					AddressUtils.HAVALANDIRMA_DEBISI,
					(int) (getHavalandirmaDebi() * 100));
			client.WriteSingleRegister(AddressUtils.POMPA_BASINCI,
					getPompaBasinci());
			
			client.Disconnect();
		} catch (Exception e) {
			System.out.println("writeValues.368 : " + e.getMessage());
		}
		return "";
	}

}
