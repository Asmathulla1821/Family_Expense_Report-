package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class DeleteExpenseMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		int id = 7;

		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		boolean isExpenseDeleted = ferService.deleteExpense(id);

		// 3. Display the status
		if (isExpenseDeleted) {
			System.out.println("Expens deleted successful...!");
		} else {
			System.out.println("Expens delettion is faild...!");
		}
	}

}
