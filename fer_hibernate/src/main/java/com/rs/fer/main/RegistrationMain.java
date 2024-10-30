package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		User user = new User();

		user.setFirstName("Jaya");
		user.setMiddleName("Surya");
		user.setLastName("M");
		user.setEmail("jaya@m.mail");
		user.setMobile("23563255");
		user.setUserName("Surya");
		user.setPassword("jaya123");

		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		boolean isRegister = ferService.registration(user);

		// 3. Display the status
		if (isRegister) {
			System.out.println("User registration is successful...!");
		} else {
			System.out.println("User registration is faild...!");
		}

	}

}
