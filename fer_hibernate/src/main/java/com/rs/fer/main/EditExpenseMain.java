package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class EditExpenseMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		Expense expense = new Expense();

		expense.setType("Food");
		expense.setDate("25/08/2023");
		expense.setPrice(100);
		expense.setNumberOfItems(2);
		expense.setTotal(200);
		expense.setByWhom("Myself");
		expense.setId(8);
		expense.setUserId(2);
		// 2. Call the service for business logic execution
		FERService ferService = new FERServiceImpl();
		boolean isEditedExpense = ferService.editExpense(expense);

		// 3. Display the status
		if (isEditedExpense) {
			System.out.println("Expens edited successful...!");
		} else {
			System.out.println("Expens edit is feild...!");
		}
	}

}
