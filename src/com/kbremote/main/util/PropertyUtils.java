package com.kbremote.main.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;

import com.kbremote.main.model.Properties;
import com.kbremote.main.model.Property;

public class PropertyUtils {

	/*
	 * Raspbian : /home/pi
	 * Windows  : C:/Users/{user.name}
	 */
	public static String addressFile = System.getProperty("user.home") + "/ka-ma/address.xml";
	public static String propertiesFile = System.getProperty("user.home") + "/ka-ma/properties.xml";
	public static String userFile = System.getProperty("user.home") + "/ka-ma/users.xml";
	
	public static String userOutputFile = System.getProperty("user.home") + "/ka-ma/user-output.txt";
	public static String propertyOutputFile = System.getProperty("user.home") + "/ka-ma/property-output.txt";
	public static String addressOutputFile = System.getProperty("user.home") + "/ka-ma/address-output.txt";
	
	public PropertyUtils() {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Properties.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			File xmlFile = new File(propertiesFile);
			
			Properties properties = (Properties) unmarshaller.unmarshal(xmlFile);
			
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, new File(propertyOutputFile));
			
			List<Property> propList = properties.getProperties();
			
			if(CollectionUtils.isNotEmpty(propList) && 
					propList.get(0) != null && 
					Boolean.valueOf(propList.get(0).getValue()) != null){
				UPDATE_FLAG = Boolean.valueOf(propList.get(0).getValue()).booleanValue();
			} else {
				UPDATE_FLAG = false;
			}
			
			System.out.println("UPDATE FLAG : " + UPDATE_FLAG);
			
			if(UPDATE_FLAG){
				if(propList.get(1) != null && propList.get(1).getValue() != null) {
					CLIENT_IP = propList.get(1).getValue();
				}
				
				if(propList.get(2) != null && propList.get(2).getValue() != null) {
					LOCAL_IP = propList.get(2).getValue();
				}
			}
			
			System.out.println("CLIENT IP : " + CLIENT_IP);
			System.out.println("CLIENT IP : " + LOCAL_IP);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean UPDATE_FLAG;
	
	public static String CLIENT_IP;
	public static String LOCAL_IP;
	
}
