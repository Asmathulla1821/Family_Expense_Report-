package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class LoginMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		String userName = "jaya";
		String password = "jaya123";

		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		boolean isValidUser = ferService.login(userName, password);

		// 3. Display the status
		if (isValidUser) {
			System.out.println("Welcome to user: " + userName + "...!");
		} else {
			System.out.println("Incorrect username/Password please try again...!");
		}
	}

}
