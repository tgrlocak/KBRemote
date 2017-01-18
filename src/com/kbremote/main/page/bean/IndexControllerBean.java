package com.kbremote.main.page.bean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ModbusClient.ModbusClient;

import com.kbremote.main.model.ReadObject;
import com.kbremote.main.service.AddressService;
import com.kbremote.main.util.AddressUtils;

@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexControllerBean extends ModbusBean{
	
	private Logger logger = Logger.getLogger(IndexControllerBean.class.getSimpleName());

	private List<ReadObject> readObjList;

	public void onLoad(){
		logger.entering(IndexControllerBean.class.getSimpleName(), "onLoad()");
		
		if(CollectionUtils.isEmpty(readObjList)){
			readObjList = new ArrayList<ReadObject>();
			
			if(CollectionUtils.isEmpty(AddressService.addressList)){
				AddressService.loadAddresses();
			}
			
			ReadObject makine = new ReadObject("MAKİNE");
			makine.setAddress(AddressService.getAddress(AddressUtils.MAKINE_ON));
			
			ReadObject recete = new ReadObject("REÇETE");
			recete.setAddress(AddressService.getAddress(AddressUtils.RECETE));
			
			ReadObject konveyorHizi = new ReadObject("KONVEYÖR HIZI");
			konveyorHizi.setAddress(AddressService.getAddress(AddressUtils.KONVEYOR_HIZI));
			konveyorHizi.setUnit("DECIMAL");
			
			ReadObject salinimKolHizi = new ReadObject("SALINIM KOL HIZI");
			salinimKolHizi.setAddress(AddressService.getAddress(AddressUtils.SALINIM_KOL_HIZI));
			salinimKolHizi.setUnit("DECIMAL");
			
			ReadObject pompaBasinci = new ReadObject("POMPA BASINCI");
			pompaBasinci.setAddress(AddressService.getAddress(AddressUtils.POMPA_BASINCI));
			
			ReadObject boyaAtimi = new ReadObject("BOYA ATIMI STANDART");
			boyaAtimi.setAddress(AddressService.getAddress(AddressUtils.BOYA_ATIMI_S_ON));
			
			ReadObject boyaAtimiY = new ReadObject("BOYA ATIMI YOĞUN");
			boyaAtimiY.setAddress(AddressService.getAddress(AddressUtils.BOYA_ATIMI_Y_ON));
			
			ReadObject kazimaUnitesi = new ReadObject("KAZIMA ÜNİTESİ");
			kazimaUnitesi.setAddress(AddressService.getAddress(AddressUtils.KAZIMA_UNITESI_ON));
			
			ReadObject havalandirmaDebisi = new ReadObject("HAVALANDIRMA DEBİSİ");
			havalandirmaDebisi.setAddress(AddressService.getAddress(AddressUtils.HAVALANDIRMA_DEBISI));
			havalandirmaDebisi.setUnit("DECIMAL");
			
			ReadObject sagGolge = new ReadObject("SAĞ GÖLGE");
			sagGolge.setAddress(AddressService.getAddress(AddressUtils.SAG_GOLGE));
			
			ReadObject solGolge = new ReadObject("SOL GÖLGE");
			solGolge.setAddress(AddressService.getAddress(AddressUtils.SOL_GOLGE));
			
			ReadObject onArkaGolge = new ReadObject("ÖN ARKA GÖLGE");
			onArkaGolge.setAddress(AddressService.getAddress(AddressUtils.ON_ARKA_GOLGE));
			
			ReadObject kolTabancaA= new ReadObject("KOL TABANCA SİSTEMİ A");
			kolTabancaA.setAddress(AddressService.getAddress(AddressUtils.KOL_TABANCA_A_ON));
			
			ReadObject kolTabancaB= new ReadObject("KOL TABANCA SİSTEMİ B");
			kolTabancaB.setAddress(AddressService.getAddress(AddressUtils.KOL_TABANCA_B_ON));
			
			ReadObject kolTabancaC= new ReadObject("KOL TABANCA SİSTEMİ C");
			kolTabancaC.setAddress(AddressService.getAddress(AddressUtils.KOL_TABANCA_C_ON));
			
			ReadObject tozAlmaUnitesi = new ReadObject("TOZ ALMA ÜNİTESİ");
			tozAlmaUnitesi.setAddress(AddressService.getAddress(AddressUtils.TOZ_ALMA_UNITESI_ON));
			
			readObjList.add(makine);
			readObjList.add(recete);
			readObjList.add(konveyorHizi);
			readObjList.add(salinimKolHizi);
			readObjList.add(pompaBasinci);
			readObjList.add(boyaAtimi);
			readObjList.add(boyaAtimiY);
			readObjList.add(kazimaUnitesi);
			readObjList.add(havalandirmaDebisi);
			readObjList.add(sagGolge);
			readObjList.add(solGolge);
			readObjList.add(onArkaGolge);
			readObjList.add(kolTabancaA);
			readObjList.add(kolTabancaB);
			readObjList.add(kolTabancaC);
			readObjList.add(tozAlmaUnitesi);
		}
		
		logger.exiting(IndexControllerBean.class.getSimpleName(), "onLoad()");
	}

	public List<ReadObject> getReadObjList() {
		logger.entering(IndexControllerBean.class.getSimpleName(), "getReadObjList()");
		
		if (CollectionUtils.isNotEmpty(readObjList)) {

			ModbusClient client = getClient();
			
			for(ReadObject ro : readObjList){
				if(client != null && client.isConnected()){
					ro.setValue(getModbusValue(client, ro));
				} else {
					if(ro.getAddress().getType().equals("M")){
						ro.setValue("F");
					} else if(ro.getAddress().getType().equals("D")){
						if(ro.getUnit() != null && ro.getUnit().equals("DECIMAL")){
							ro.setValue(new DecimalFormat("##.##").format((float) 0));
						} else {
							ro.setValue("0");
						}
					}
				}
			}
				
			disconnectClient();

		}
		logger.exiting(IndexControllerBean.class.getSimpleName(), "getReadObjList()");
		return readObjList;
	}

	public void setReadObjList(List<ReadObject> readObjList) {
		this.readObjList = readObjList;
	}

	public String getModbusValue(ModbusClient client, ReadObject ro) {
		logger.entering(IndexControllerBean.class.getSimpleName(), "getModbusValue()", new Object[] {client, ro});
		String value = "";

		try {

			if (client != null && client.isConnected()) {
				if (ro.getAddress().getType().equals("M")) {
					boolean[] coils = client.ReadCoils(ro.getAddress().getValue(), 1);
					if (coils.length > 0) {
						value = coils[0] ? "T" : "F";
					}
				} else if (ro.getAddress().getType().equals("D")) {
					int[] regs = client.ReadHoldingRegisters(ro.getAddress().getValue(), 1);
					if (regs.length > 0) {
						boolean set = false;
						float hiz = 0;
						if(ro.getAddress().getValue() == 6036){
							set = true;
							hiz = ((float) regs[0]) / 10;
						} else if(ro.getAddress().getValue() == 220){
							set = true;
							hiz = ((float) regs[0]) / 30;
						} else if(ro.getAddress().getValue() == 1302){
							set = true;
							hiz = ((float) regs[0]) / 100;
						}
						if(set){
							value = new DecimalFormat("##.##").format(hiz);
						} else {
							value = regs[0] + "";
						}
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.exiting(IndexControllerBean.class.getSimpleName(), "getModbusValue()", value);
		return value;
	}

}
