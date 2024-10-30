package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class AddExpenseMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		Expense expense = new Expense();

		expense.setType("Outing");
		expense.setDate("26/08/2023");
		expense.setPrice(200);
		expense.setNumberOfItems(2);
		expense.setTotal(400);
		expense.setByWhom("Me");
		expense.setUserId(2);

		// 2. Call the service for business logic execution

		FERService ferService = new FERServiceImpl();
		boolean isValidUser = ferService.addExpense(expense);

		// 3. Display the status

		if (isValidUser) {
			System.out.println("Expens added successful...!");
		} else {
			System.out.println("Expens add is faild...!");
		}
	}

}
