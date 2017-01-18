package com.kbremote.main.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.security.core.GrantedAuthority;

@XmlAccessorType(XmlAccessType.FIELD)
public class Role implements GrantedAuthority{

	private String name;
	
	@Override
	public String getAuthority() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
