package com.kbremote.main.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;

import com.kbremote.main.model.Role;
import com.kbremote.main.model.User;
import com.kbremote.main.model.Users;
import com.kbremote.main.util.PropertyUtils;

public class UserService {

	private List<User> userList;

	public User findByUserName(String username) {
		if(CollectionUtils.isNotEmpty(getUserList())){
			for (User user : getUserList()) {
				if(username.equals(user.getUsername())){
					return user;
				}
			}
		}
		return null;
	}

	public List<User> getUserList() {
		if(CollectionUtils.isEmpty(userList)){
			userList = new ArrayList<User>();
			
			try {
				JAXBContext jc = JAXBContext.newInstance(Users.class);
				
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				
				File userXML = new File(PropertyUtils.userFile);
				
				Users users = (Users) unmarshaller.unmarshal(userXML);
				
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(users, new File(PropertyUtils.userOutputFile));
				
				userList = users.getUserList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
