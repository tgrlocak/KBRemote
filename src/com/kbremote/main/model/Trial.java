package com.kbremote.main.model;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import ModbusClient.ModbusClient;

import com.kbremote.main.service.AddressService;
import com.kbremote.main.service.UserService;
import com.kbremote.main.util.AddressUtils;
import com.kbremote.main.util.PropertyUtils;

public class Trial {

	public static int MAKINE_ON = 0;
	public static int RECETE = 6057;
	public static int KONVEYOR_HIZI = 6036;
	public static int KONVEYOR_ON = 160;
	public static int SALINIM_KOL_HIZI = 220;
	public static int SALINIM_KOLU_ON = 162;
	public static int POMPA_BASINCI = 6065;
	public static int BOYA_ATIMI_S_ON = 164;
	public static int BOYA_ATIMI_S_OFF = 170;
	public static int BOYA_ATIMI_Y_ON = 171;
	public static int BOYA_ATIMI_Y_OFF = 170;
	public static int KAZIMA_UNITESI_ON = 163;
	public static int HAVALANDIRMA_DEBISI = 1302;
	public static int HAVALANDIRMA_ON = 161;
	public static int SAG_GOLGE = 6041;
	public static int SOL_GOLGE = 6041;
	public static int ON_ARKA_GOLGE = 6042;
	public static int KOL_TABANCA_A_ON = 560;
	public static int KOL_TABANCA_B_ON = 561;
	public static int KOL_TABANCA_C_ON = 562;
	public static int TOZ_ALMA_UNITESI_ON = 172;
	public static int POMPA_ON = 296;
	public static int KOL_TABANCA_SISTEM_SECIMI_ON = 565;
	public static int TABANCA_1_A_YUKSEKLIK = 6000;
	public static int TABANCA_1_A_BETA = 6001;
	public static int TABANCA_1_A_ALFA = 6002;
	public static int TABANCA_1_B_YUKSEKLIK = 6012;
	public static int TABANCA_1_B_BETA = 6013;
	public static int TABANCA_1_B_ALFA = 6014;
	public static int TABANCA_1_C_YUKSEKLIK = 6024;
	public static int TABANCA_1_C_BETA = 6025;
	public static int TABANCA_1_C_ALFA = 6026;
	public static int TABANCA_2_A_YUKSEKLIK = 6003;
	public static int TABANCA_2_A_BETA = 6004;
	public static int TABANCA_2_A_ALFA = 6005;
	public static int TABANCA_2_B_YUKSEKLIK = 6015;
	public static int TABANCA_2_B_BETA = 6016;
	public static int TABANCA_2_B_ALFA = 6017;
	public static int TABANCA_2_C_YUKSEKLIK = 6027;
	public static int TABANCA_2_C_BETA = 6028;
	public static int TABANCA_2_C_ALFA = 6029;
	public static int TABANCA_3_A_YUKSEKLIK = 6006;
	public static int TABANCA_3_A_BETA = 6007;
	public static int TABANCA_3_A_ALFA = 6008;
	public static int TABANCA_3_B_YUKSEKLIK = 6018;
	public static int TABANCA_3_B_BETA = 6019;
	public static int TABANCA_3_B_ALFA = 6020;
	public static int TABANCA_3_C_YUKSEKLIK = 6030;
	public static int TABANCA_3_C_BETA = 6031;
	public static int TABANCA_3_C_ALFA = 6032;
	public static int TABANCA_4_A_YUKSEKLIK = 6009;
	public static int TABANCA_4_A_BETA = 6010;
	public static int TABANCA_4_A_ALFA = 6011;
	public static int TABANCA_4_B_YUKSEKLIK = 6021;
	public static int TABANCA_4_B_BETA = 6022;
	public static int TABANCA_4_B_ALFA = 6023;
	public static int TABANCA_4_C_YUKSEKLIK = 6033;
	public static int TABANCA_4_C_BETA = 6034;
	public static int TABANCA_4_C_ALFA = 6035;
	public static int TABANCA_SAG_DUZELTME = 6044;
	public static int TABANCA_SOL_DUZELTME = 6045;
	public static int MAX_BOYAMA_ALANI = 6046;
	public static int MIN_BOYAMA_ALANI = 6047;
	public static int MAX_FOTOSEL_CALISMA_ALANI = 6048;
	public static int MIN_FOTOSEL_CALISMA_ALANI = 6049;
	public static int SENSOR_ILE_1_TABANCA_ARASI = 6050;
	public static int SENSOR_ILE_2_TABANCA_ARASI = 6052;
	public static int SERVO_HIZLANMA_RAMPASI = 6054;
	public static int SERVO_YAVASLAMA_RAMPASI = 6055;
	public static int SENSOR_DUZELTME = 6056;
	public static int POMPA_BEKLEME_SURESI = 6056;
	public static int POMPA_CALISMA_SURESI = 6056;
	public static int SENSOR_ZAMANLAYICI_T_ON = 6056;
	public static int SENSOR_ZAMANLAYICI_T_OFF = 6056;
	public static int POMPA_HAVA_BASINCI_LIMITI = 6056;
	
	public static void main(String[] args) {
		
		//testModbusClient();

		testReadAddresses();
		
		//testWriteValues();
		
		//testAddressService();
		
		//testAddressUtils();
		
		//testProperties();

		//testUserService();
	}
	
	public static void testReadAddresses(){
		
		AddressService service = new AddressService();
		List<Address> addresList = service.getAddressList();
		
		try {
			System.out.println("READ START");
			ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);
			client.Connect();
			
			for(Address add : addresList) {
				if(AddressUtils.M_TYPE.equals(add.getType())){
					System.out.println(add.getName() + " - " + add.getBit() + " - " + 
							client.ReadCoils(add.getValue(), 1)[0]);
				} else if(AddressUtils.D_TYPE.equals(add.getType())){
					System.out.println(add.getName() + " - " + add.getBit() + " - " + 
							client.ReadHoldingRegisters(add.getValue(), 1)[0]);
				}
			}
			
			client.Disconnect();
			System.out.println("READ END");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testWriteValues(){
		AddressService service = new AddressService();
		List<Address> addresList = service.getAddressList();
		
		try {
			System.out.println("WRITE START");
			ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);
			client.Connect();
			
			for(Address add : addresList){
				if(AddressUtils.M_TYPE.equals(add.getType())){
					client.WriteSingleCoil(add.getValue(), true);
				} else if(AddressUtils.D_TYPE.equals(add.getType())){
					client.WriteSingleRegister(add.getValue(), 50);
				}
			}
			
			client.Disconnect();
			System.out.println("WRITE END");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testAddressService(){
		AddressService addressService = new AddressService();
		List<Address> addList = addressService.getAddressList();
		
		/*
		for(Address a : addList){
			System.out.println("System.out.println(AddressUtils." + a.getName() + ");");
			//System.out.println("public static int " + a.getName() + " = " + a.getValue() + ";");
		}
		*/
	}
	
	public static void testAddressUtils(){
		System.out.println(AddressUtils.MAKINE_ON);
		System.out.println(AddressUtils.RECETE);
		System.out.println(AddressUtils.KONVEYOR_HIZI);
		System.out.println(AddressUtils.KONVEYOR_ON);
		System.out.println(AddressUtils.SALINIM_KOL_HIZI);
		System.out.println(AddressUtils.SALINIM_KOLU_ON);
		System.out.println(AddressUtils.POMPA_BASINCI);
		System.out.println(AddressUtils.BOYA_ATIMI_S_ON);
		System.out.println(AddressUtils.BOYA_ATIMI_S_OFF);
		System.out.println(AddressUtils.BOYA_ATIMI_Y_ON);
		System.out.println(AddressUtils.BOYA_ATIMI_Y_OFF);
		System.out.println(AddressUtils.KAZIMA_UNITESI_ON);
		System.out.println(AddressUtils.HAVALANDIRMA_DEBISI);
		System.out.println(AddressUtils.HAVALANDIRMA_ON);
		System.out.println(AddressUtils.SAG_GOLGE);
		System.out.println(AddressUtils.SOL_GOLGE);
		System.out.println(AddressUtils.ON_ARKA_GOLGE);
		System.out.println(AddressUtils.KOL_TABANCA_A_ON);
		System.out.println(AddressUtils.KOL_TABANCA_B_ON);
		System.out.println(AddressUtils.KOL_TABANCA_C_ON);
		System.out.println(AddressUtils.TOZ_ALMA_UNITESI_ON);
		System.out.println(AddressUtils.POMPA_ON);
		System.out.println(AddressUtils.KOL_TABANCA_SISTEM_SECIMI_ON);
		System.out.println(AddressUtils.TABANCA_1_A_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_1_A_BETA);
		System.out.println(AddressUtils.TABANCA_1_A_ALFA);
		System.out.println(AddressUtils.TABANCA_1_B_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_1_B_BETA);
		System.out.println(AddressUtils.TABANCA_1_B_ALFA);
		System.out.println(AddressUtils.TABANCA_1_C_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_1_C_BETA);
		System.out.println(AddressUtils.TABANCA_1_C_ALFA);
		System.out.println(AddressUtils.TABANCA_2_A_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_2_A_BETA);
		System.out.println(AddressUtils.TABANCA_2_A_ALFA);
		System.out.println(AddressUtils.TABANCA_2_B_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_2_B_BETA);
		System.out.println(AddressUtils.TABANCA_2_B_ALFA);
		System.out.println(AddressUtils.TABANCA_2_C_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_2_C_BETA);
		System.out.println(AddressUtils.TABANCA_2_C_ALFA);
		System.out.println(AddressUtils.TABANCA_3_A_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_3_A_BETA);
		System.out.println(AddressUtils.TABANCA_3_A_ALFA);
		System.out.println(AddressUtils.TABANCA_3_B_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_3_B_BETA);
		System.out.println(AddressUtils.TABANCA_3_B_ALFA);
		System.out.println(AddressUtils.TABANCA_3_C_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_3_C_BETA);
		System.out.println(AddressUtils.TABANCA_3_C_ALFA);
		System.out.println(AddressUtils.TABANCA_4_A_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_4_A_BETA);
		System.out.println(AddressUtils.TABANCA_4_A_ALFA);
		System.out.println(AddressUtils.TABANCA_4_B_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_4_B_BETA);
		System.out.println(AddressUtils.TABANCA_4_B_ALFA);
		System.out.println(AddressUtils.TABANCA_4_C_YUKSEKLIK);
		System.out.println(AddressUtils.TABANCA_4_C_BETA);
		System.out.println(AddressUtils.TABANCA_4_C_ALFA);
		System.out.println(AddressUtils.TABANCA_SAG_DUZELTME);
		System.out.println(AddressUtils.TABANCA_SOL_DUZELTME);
		System.out.println(AddressUtils.MAX_BOYAMA_ALANI);
		System.out.println(AddressUtils.MIN_BOYAMA_ALANI);
		System.out.println(AddressUtils.MAX_FOTOSEL_CALISMA_ALANI);
		System.out.println(AddressUtils.MIN_FOTOSEL_CALISMA_ALANI);
		System.out.println(AddressUtils.SENSOR_ILE_1_TABANCA_ARASI);
		System.out.println(AddressUtils.SENSOR_ILE_2_TABANCA_ARASI);
		System.out.println(AddressUtils.SERVO_HIZLANMA_RAMPASI);
		System.out.println(AddressUtils.SERVO_YAVASLAMA_RAMPASI);
		System.out.println(AddressUtils.SENSOR_DUZELTME);
		System.out.println(AddressUtils.POMPA_BEKLEME_SURESI);
		System.out.println(AddressUtils.POMPA_CALISMA_SURESI);
		System.out.println(AddressUtils.SENSOR_ZAMANLAYICI_T_ON);
		System.out.println(AddressUtils.SENSOR_ZAMANLAYICI_T_OFF);
		System.out.println(AddressUtils.POMPA_HAVA_BASINCI_LIMITI);

	}
	
	public static void testProperties(){
		PropertyUtils propUtil = new PropertyUtils();
		
		System.out.println(PropertyUtils.UPDATE_FLAG);
		System.out.println(PropertyUtils.CLIENT_IP);
	}
	
	public static void testUserService(){
		UserService userService = new UserService();
		
		List<User> userList = userService.getUserList();
		
		for(User u : userList){
			System.out.print("USER : " + u.getUsername() + " - PASSWORD : " + u.getPassword() + " - ROLES : ");
			for(Role r : u.getAuthorities()){
				System.out.print(r.getName() + " | ");
			}
			System.out.println();
		}
	}
	
	public static void testModbusClient() {
		try {
			ModbusClient client = new ModbusClient(AddressUtils.getClientIP(), 502);
			
			client.Connect();
			/*
			 * coils numbers start with a zero and then span from 00001 to 09999
			 * discrete input numbers start with a one and then span from 10001 to 19999
			 * input register numbers start with a three and then span from 30001 to 39999
			 * holding register numbers start with a four and then span from 40001 to 49999
			 */


			System.out.println("############   START READING   ############");
			System.out.println("############   COILS   ############");
			
			String content = "";
			
			for(int i=0; i < 10000; i++){
				boolean[] bArr = client.ReadCoils(i, 1);
				String tmp = i + " : ";
				
				if(bArr.length > 1){
					for(int j=0; j < bArr.length; j++){
						tmp = tmp + bArr[j] + " - ";
					}
				} else {
					tmp = tmp + bArr[0];
				}
				
				content += tmp + "\n";
			}
			
			File coilsFile = new File("C:\\MODBUS\\1-Coils2.txt");
			FileWriter writer = new FileWriter(coilsFile);
			
			writer.write(content);
			writer.close();
			
			System.out.println("ENDED");
			/*
			content = "";
			
			System.out.println("############   DISCRETE INPUTS   ############");
			for(int i=0; i < 65535; i++){
				boolean[] bArr = client.ReadDiscreteInputs(i, 1);
				String tmp = i + " : ";
				
				if(bArr.length > 1){
					for(int j=0; j < bArr.length; j++){
						tmp = tmp + bArr[j] + " - ";
					}
				} else {
					tmp = tmp + bArr[0];
				}
				
				content += tmp + "\n";
			}
			
			File discreteFile = new File("C:\\MODBUS\\2-DiscreteInputs.txt");
			writer = new FileWriter(discreteFile);
			
			writer.write(content);
			writer.close();

			content = "";
			
			System.out.println("############   INPUT REGISTERS   ############");
			for(int i=0; i < 65535; i++){
				int[] bArr = client.ReadInputRegisters(i, 1);
				String tmp = i + " : ";
				
				if(bArr.length > 1){
					for(int j=0; j < bArr.length; j++){
						tmp = tmp + bArr[j] + " - ";
					}
				} else {
					tmp = tmp + bArr[0];
				}
				
				content += tmp + "\n";
			}
			
			File inputRegFile = new File("C:\\MODBUS\\3-InputRegisters2.txt");
			writer = new FileWriter(inputRegFile);
			
			writer.write(content);
			writer.close();
			
			content = "";
			
			System.out.println("############   HOLDING REGISTERS   ############");
			for(int i=0; i < 65535; i++){
				int[] bArr = client.ReadHoldingRegisters(i, 1);
				String tmp = i + " : ";
				
				if(bArr.length > 1){
					for(int j=0; j < bArr.length; j++){
						tmp = tmp + bArr[j] + " - ";
					}
				} else {
					tmp = tmp + bArr[0];
				}
				
				content += tmp + "\n";
			}
			
			File holdingFile = new File("C:\\MODBUS\\4-HoldingRegisters2.txt");
			writer = new FileWriter(holdingFile);
			
			writer.write(content);
			writer.close();
			
			System.out.println("############   END READING   ############");
			
			*/
			
			client.Disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
