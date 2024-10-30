package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class UpdateUserMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		int userId = 1;
		boolean isUpdated = false;
		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);
		
		if(user != null) {
			// Update the user fields
			user.setFirstName("Suri");
			user.setMiddleName("cdjg");
			user.setLastName("Kumar");
			user.setEmail("hari@rs.mail");
			user.setMobile("2009995");

			// Update the address fields
			Address address = user.getAddress();

			address.setLineOne("654654654");
			address.setLineTwo("hyfg");
			address.setCity("hgjy");
			address.setState("ytuft");
			address.setPincode("hgf");
			address.setCountry("hjbhgufg");
			
			
			// 2. Call the service for business logic execution
			isUpdated = ferService.updateUser(user);
		}
       

		// 3. Display the status
		if (isUpdated) {
			System.out.println("User Updated successful...!");
		} else {
			System.out.println("User upgradation is faild...!");
		}
		

	}

}
