package com.kbremote.main.model;

public class ReadObject {

	public ReadObject() {
		
	}
	
	public ReadObject(String componentName){
		this.componentName = componentName;
	}
	
	public ReadObject(String componentName, Address address){
		this.componentName = componentName;
		this.address = address;
	}
	
	public ReadObject(String componentName, Address address, String unit){
		this.componentName = componentName;
		this.setUnit(unit);
		this.address = address;
	}
	
	private String componentName;
	private Address address;
	private String value;
	private String unit;
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
