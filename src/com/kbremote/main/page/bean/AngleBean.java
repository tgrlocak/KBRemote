package com.kbremote.main.page.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.kbremote.main.model.Angle;

@ManagedBean
@ViewScoped
public class AngleBean {

	private List<Angle> angles;

	public List<Angle> getAngles() {
		if(CollectionUtils.isEmpty(angles)){
			angles = new ArrayList<Angle>();
			
			angles.add(new Angle("1. Tabanca", 1, 2, 3, 1, 2, 3, 1, 2, 3));
		}
		return angles;
	}

	public void setAngles(List<Angle> angles) {
		this.angles = angles;
	}
	
	
}
