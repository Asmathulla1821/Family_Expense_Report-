package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class ResetPasswordMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		String newPassword = "123";
		int userId = 2;
		String currentPassword = "1234";

		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		boolean isResetPassword = ferService.resetPassword(newPassword, userId, currentPassword);

		// 3. Display the status
		if (isResetPassword) {
			System.out.println("Password Updation is Successful...!");
		} else {
			System.out.println("Password Updation is faild ...!");
		}
	}

}