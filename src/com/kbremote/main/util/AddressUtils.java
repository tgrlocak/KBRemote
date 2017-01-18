package com.kbremote.main.util;

import org.apache.commons.lang3.StringUtils;

public class AddressUtils {

	/*
	 * M bitler icin 3-4 basamaklarda son basamak Hex ilk basamaklar decimal olarak hesaplanýr.
	 * M352 icin 35*16 + 2 = 562
	 * M5F icin 5*16 + 15 = 105
	 * M102A icin 102 * 16 + 10 
	 * gibi
	 */
	
	public static String M_TYPE = "M";
	public static String D_TYPE = "D";
	
	/*
	 * ANASAYFA degerleri - index.html
	 */
	public static int MAKINE_ON;
	// RECETE SECIMI - receipts.xhtml
	public static int RECETE;
	public static int KONVEYOR_HIZI;
	public static int KONVEYOR_ON;
	public static int SALINIM_KOL_HIZI;
	public static int SALINIM_KOLU_ON;
	public static int POMPA_BASINCI;
	public static int BOYA_ATIMI_S_ON;
	public static int BOYA_ATIMI_S_OFF;
	public static int BOYA_ATIMI_Y_ON;
	public static int BOYA_ATIMI_Y_OFF;
	public static int KAZIMA_UNITESI_ON;
	public static int HAVALANDIRMA_DEBISI;
	public static int HAVALANDIRMA_ON;
	public static int KOL_TABANCA_A_ON;
	public static int KOL_TABANCA_B_ON;
	public static int KOL_TABANCA_C_ON;
	public static int TOZ_ALMA_UNITESI_ON;
	// CALISMA GOLGE AYARLARI - shadows.xhtml
	public static int SAG_GOLGE;
	public static int SOL_GOLGE;
	public static int ON_ARKA_GOLGE;
	
	/*
	 * SERVIS AYARLARI - config.xhtml
	 */
	public static int POMPA_ON;
	public static int KOL_TABANCA_SISTEM_SECIMI_ON;
	
	/*
	 * TABANCA ACILARI - angles.xhtml
	 */
	public static int TABANCA_1_A_YUKSEKLIK;
	public static int TABANCA_1_A_BETA;
	public static int TABANCA_1_A_ALFA;
	public static int TABANCA_1_B_YUKSEKLIK;
	public static int TABANCA_1_B_BETA;
	public static int TABANCA_1_B_ALFA;
	public static int TABANCA_1_C_YUKSEKLIK;
	public static int TABANCA_1_C_BETA;
	public static int TABANCA_1_C_ALFA;
	public static int TABANCA_2_A_YUKSEKLIK;
	public static int TABANCA_2_A_BETA;
	public static int TABANCA_2_A_ALFA;
	public static int TABANCA_2_B_YUKSEKLIK;
	public static int TABANCA_2_B_BETA;
	public static int TABANCA_2_B_ALFA;
	public static int TABANCA_2_C_YUKSEKLIK;
	public static int TABANCA_2_C_BETA;
	public static int TABANCA_2_C_ALFA;
	public static int TABANCA_3_A_YUKSEKLIK;
	public static int TABANCA_3_A_BETA;
	public static int TABANCA_3_A_ALFA;
	public static int TABANCA_3_B_YUKSEKLIK;
	public static int TABANCA_3_B_BETA;
	public static int TABANCA_3_B_ALFA;
	public static int TABANCA_3_C_YUKSEKLIK;
	public static int TABANCA_3_C_BETA;
	public static int TABANCA_3_C_ALFA;
	public static int TABANCA_4_A_YUKSEKLIK;
	public static int TABANCA_4_A_BETA;
	public static int TABANCA_4_A_ALFA;
	public static int TABANCA_4_B_YUKSEKLIK;
	public static int TABANCA_4_B_BETA;
	public static int TABANCA_4_B_ALFA;
	public static int TABANCA_4_C_YUKSEKLIK;
	public static int TABANCA_4_C_BETA;
	public static int TABANCA_4_C_ALFA;

	/*
	 * SERVIS AYARLARI - service.xhtml
	 */
	public static int TABANCA_SAG_DUZELTME;
	public static int TABANCA_SOL_DUZELTME;
	public static int MAX_BOYAMA_ALANI;
	public static int MIN_BOYAMA_ALANI;
	public static int MAX_FOTOSEL_CALISMA_ALANI;
	public static int MIN_FOTOSEL_CALISMA_ALANI;
	public static int SENSOR_ILE_1_TABANCA_ARASI;
	public static int SENSOR_ILE_2_TABANCA_ARASI;
	public static int SERVO_HIZLANMA_RAMPASI;
	public static int SERVO_YAVASLAMA_RAMPASI;
	public static int SENSOR_DUZELTME;
	public static int POMPA_BEKLEME_SURESI;
	public static int POMPA_CALISMA_SURESI;
	public static int SENSOR_ZAMANLAYICI_T_ON;
	public static int SENSOR_ZAMANLAYICI_T_OFF;
	public static int POMPA_HAVA_BASINCI_LIMITI;
	
	public static String getClientIP(){
		if(StringUtils.isEmpty(PropertyUtils.CLIENT_IP)){
			PropertyUtils propUtil = new PropertyUtils();
		}
		
		return PropertyUtils.CLIENT_IP;
	}
	
	public static String getLocalIP(){
		if(StringUtils.isEmpty(PropertyUtils.LOCAL_IP)){
			PropertyUtils propUtil = new PropertyUtils();
		}
		
		return PropertyUtils.LOCAL_IP;
	}
}
