package com.kbremote.main.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;

import com.kbremote.main.model.Address;
import com.kbremote.main.model.Addresses;
import com.kbremote.main.util.AddressUtils;
import com.kbremote.main.util.PropertyUtils;

public class AddressService {

	public static List<Address> addressList;
	
	public static Address getAddress(int addressValue){
		if(CollectionUtils.isEmpty(addressList)){
			loadAddresses();
		}
		
		for(Address a : addressList){
			if(a.getValue() == addressValue){
				return a;
			}
		}
		return null;
	}
	
	public List<Address> getAddressList(){
		
		PropertyUtils propUtil = new PropertyUtils();
		
		if(!PropertyUtils.UPDATE_FLAG && CollectionUtils.isNotEmpty(addressList)){
			return addressList;
		}
		
		addressList = new ArrayList<Address>();
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Addresses.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			File xmlFile = new File(PropertyUtils.addressFile);
			
			Addresses addresses = (Addresses) unmarshaller.unmarshal(xmlFile);
			
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(addresses, new File(PropertyUtils.addressOutputFile));
			
			addressList = addresses.getAddressList();
			
			if(CollectionUtils.isNotEmpty(addressList)){
				for(Address a : addressList){
					switch (a.getName()) {
					case "MAKINE_ON":
						AddressUtils.MAKINE_ON = a.getValue();
						break;
					case "RECETE":
						AddressUtils.RECETE = a.getValue();
						break;
					case "KONVEYOR_HIZI":
						AddressUtils.KONVEYOR_HIZI = a.getValue();
						break;
					case "KONVEYOR_ON":
						AddressUtils.KONVEYOR_ON = a.getValue();
						break;
					case "SALINIM_KOL_HIZI":
						AddressUtils.SALINIM_KOL_HIZI = a.getValue();
						break;
					case "SALINIM_KOLU_ON":
						AddressUtils.SALINIM_KOLU_ON = a.getValue();
						break;
					case "POMPA_BASINCI":
						AddressUtils.POMPA_BASINCI = a.getValue();
						break;
					case "BOYA_ATIMI_S_ON":
						AddressUtils.BOYA_ATIMI_S_ON = a.getValue();
						break;
					case "BOYA_ATIMI_S_OFF":
						AddressUtils.BOYA_ATIMI_S_OFF = a.getValue();
						break;
					case "BOYA_ATIMI_Y_ON":
						AddressUtils.BOYA_ATIMI_Y_ON = a.getValue();
						break;
					case "BOYA_ATIMI_Y_OFF":
						AddressUtils.BOYA_ATIMI_Y_OFF = a.getValue();
						break;
					case "KAZIMA_UNITESI_ON":
						AddressUtils.KAZIMA_UNITESI_ON = a.getValue();
						break;
					case "HAVALANDIRMA_DEBISI":
						AddressUtils.HAVALANDIRMA_DEBISI = a.getValue();
						break;
					case "HAVALANDIRMA_ON":
						AddressUtils.HAVALANDIRMA_ON = a.getValue();
						break;
					case "SAG_GOLGE":
						AddressUtils.SAG_GOLGE = a.getValue();
						break;
					case "SOL_GOLGE":
						AddressUtils.SOL_GOLGE = a.getValue();
						break;
					case "ON_ARKA_GOLGE":
						AddressUtils.ON_ARKA_GOLGE = a.getValue();
						break;
					case "KOL_TABANCA_A_ON":
						AddressUtils.KOL_TABANCA_A_ON = a.getValue();
						break;
					case "KOL_TABANCA_B_ON":
						AddressUtils.KOL_TABANCA_B_ON = a.getValue();
						break;
					case "KOL_TABANCA_C_ON":
						AddressUtils.KOL_TABANCA_C_ON = a.getValue();
						break;
					case "TOZ_ALMA_UNITESI_ON":
						AddressUtils.TOZ_ALMA_UNITESI_ON = a.getValue();
						break;
					case "POMPA_ON":
						AddressUtils.POMPA_ON = a.getValue();
						break;
					case "KOL_TABANCA_SISTEM_SECIMI_ON":
						AddressUtils.KOL_TABANCA_SISTEM_SECIMI_ON = a.getValue();
						break;
					case "TABANCA_1_A_YUKSEKLIK":
						AddressUtils.TABANCA_1_A_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_1_A_BETA":
						AddressUtils.TABANCA_1_A_BETA = a.getValue();
						break;
					case "TABANCA_1_A_ALFA":
						AddressUtils.TABANCA_1_A_ALFA = a.getValue();
						break;
					case "TABANCA_1_B_YUKSEKLIK":
						AddressUtils.TABANCA_1_B_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_1_B_BETA":
						AddressUtils.TABANCA_1_B_BETA = a.getValue();
						break;
					case "TABANCA_1_B_ALFA":
						AddressUtils.TABANCA_1_B_ALFA = a.getValue();
						break;
					case "TABANCA_1_C_YUKSEKLIK":
						AddressUtils.TABANCA_1_C_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_1_C_BETA":
						AddressUtils.TABANCA_1_C_BETA = a.getValue();
						break;
					case "TABANCA_1_C_ALFA":
						AddressUtils.TABANCA_1_C_ALFA = a.getValue();
						break;
					case "TABANCA_2_A_YUKSEKLIK":
						AddressUtils.TABANCA_2_A_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_2_A_BETA":
						AddressUtils.TABANCA_2_A_BETA = a.getValue();
						break;
					case "TABANCA_2_A_ALFA":
						AddressUtils.TABANCA_2_A_ALFA = a.getValue();
						break;
					case "TABANCA_2_B_YUKSEKLIK":
						AddressUtils.TABANCA_2_B_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_2_B_BETA":
						AddressUtils.TABANCA_2_B_BETA = a.getValue();
						break;
					case "TABANCA_2_B_ALFA":
						AddressUtils.TABANCA_2_B_ALFA = a.getValue();
						break;
					case "TABANCA_2_C_YUKSEKLIK":
						AddressUtils.TABANCA_2_C_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_2_C_BETA":
						AddressUtils.TABANCA_2_C_BETA = a.getValue();
						break;
					case "TABANCA_2_C_ALFA":
						AddressUtils.TABANCA_2_C_ALFA = a.getValue();
						break;
					case "TABANCA_3_A_YUKSEKLIK":
						AddressUtils.TABANCA_3_A_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_3_A_BETA":
						AddressUtils.TABANCA_3_A_BETA = a.getValue();
						break;
					case "TABANCA_3_A_ALFA":
						AddressUtils.TABANCA_3_A_ALFA = a.getValue();
						break;
					case "TABANCA_3_B_YUKSEKLIK":
						AddressUtils.TABANCA_3_B_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_3_B_BETA":
						AddressUtils.TABANCA_3_B_BETA = a.getValue();
						break;
					case "TABANCA_3_B_ALFA":
						AddressUtils.TABANCA_3_B_ALFA = a.getValue();
						break;
					case "TABANCA_3_C_YUKSEKLIK":
						AddressUtils.TABANCA_3_C_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_3_C_BETA":
						AddressUtils.TABANCA_3_C_BETA = a.getValue();
						break;
					case "TABANCA_3_C_ALFA":
						AddressUtils.TABANCA_3_C_ALFA = a.getValue();
						break;
					case "TABANCA_4_A_YUKSEKLIK":
						AddressUtils.TABANCA_4_A_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_4_A_BETA":
						AddressUtils.TABANCA_4_A_BETA = a.getValue();
						break;
					case "TABANCA_4_A_ALFA":
						AddressUtils.TABANCA_4_A_ALFA = a.getValue();
						break;
					case "TABANCA_4_B_YUKSEKLIK":
						AddressUtils.TABANCA_4_B_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_4_B_BETA":
						AddressUtils.TABANCA_4_B_BETA = a.getValue();
						break;
					case "TABANCA_4_B_ALFA":
						AddressUtils.TABANCA_4_B_ALFA = a.getValue();
						break;
					case "TABANCA_4_C_YUKSEKLIK":
						AddressUtils.TABANCA_4_C_YUKSEKLIK = a.getValue();
						break;
					case "TABANCA_4_C_BETA":
						AddressUtils.TABANCA_4_C_BETA = a.getValue();
						break;
					case "TABANCA_4_C_ALFA":
						AddressUtils.TABANCA_4_C_ALFA = a.getValue();
						break;
					case "TABANCA_SAG_DUZELTME":
						AddressUtils.TABANCA_SAG_DUZELTME = a.getValue();
						break;
					case "TABANCA_SOL_DUZELTME":
						AddressUtils.TABANCA_SOL_DUZELTME = a.getValue();
						break;
					case "MAX_BOYAMA_ALANI":
						AddressUtils.MAX_BOYAMA_ALANI = a.getValue();
						break;
					case "MIN_BOYAMA_ALANI":
						AddressUtils.MIN_BOYAMA_ALANI = a.getValue();
						break;
					case "MAX_FOTOSEL_CALISMA_ALANI":
						AddressUtils.MAX_FOTOSEL_CALISMA_ALANI = a.getValue();
						break;
					case "MIN_FOTOSEL_CALISMA_ALANI":
						AddressUtils.MIN_FOTOSEL_CALISMA_ALANI = a.getValue();
						break;
					case "SENSOR_ILE_1_TABANCA_ARASI":
						AddressUtils.SENSOR_ILE_1_TABANCA_ARASI = a.getValue();
						break;
					case "SENSOR_ILE_2_TABANCA_ARASI":
						AddressUtils.SENSOR_ILE_2_TABANCA_ARASI = a.getValue();
						break;
					case "SERVO_HIZLANMA_RAMPASI":
						AddressUtils.SERVO_HIZLANMA_RAMPASI = a.getValue();
						break;
					case "SERVO_YAVASLAMA_RAMPASI":
						AddressUtils.SERVO_YAVASLAMA_RAMPASI = a.getValue();
						break;
					case "SENSOR_DUZELTME":
						AddressUtils.SENSOR_DUZELTME = a.getValue();
						break;
					case "POMPA_BEKLEME_SURESI":
						AddressUtils.POMPA_BEKLEME_SURESI = a.getValue();
						break;
					case "POMPA_CALISMA_SURESI":
						AddressUtils.POMPA_CALISMA_SURESI = a.getValue();
						break;
					case "SENSOR_ZAMANLAYICI_T_ON":
						AddressUtils.SENSOR_ZAMANLAYICI_T_ON = a.getValue();
						break;
					case "SENSOR_ZAMANLAYICI_T_OFF":
						AddressUtils.SENSOR_ZAMANLAYICI_T_OFF = a.getValue();
						break;
					case "POMPA_HAVA_BASINCI_LIMITI":
						AddressUtils.POMPA_HAVA_BASINCI_LIMITI = a.getValue();
						break;
					default:
						break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addressList;
	}

	public static void loadAddresses() {
		AddressService as = new AddressService();
		as.getAddressList();
	}
}
