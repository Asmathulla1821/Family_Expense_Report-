package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetUserMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		int userId = 3;

		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);

		// 3. Display the status
		if (user == null) {
			System.out.println("User not found...!");
		} else {

			System.out.println("=======Name info=======");
			System.out.println("First name: " + user.getFirstName());
			System.out.println("Middle name: " + user.getMiddleName());
			System.out.println("Last name: " + user.getLastName());

			System.out.println("========Contact info=========");
			System.out.println("Email: " + user.getEmail());
			System.out.println("Mobile: " + user.getMobile());

			System.out.println("==========Address info========");
			
			Address address = user.getAddress();
			System.out.println("Line1: " + address.getLineOne());
			System.out.println("Line2: " + address.getLineTwo());
			System.out.println("City: " + address.getCity());
			System.out.println("State: " + address.getState());
			System.out.println("Pincode: " + address.getPincode());
			System.out.println("Country: " + address.getCountry());
		}

	}

}
