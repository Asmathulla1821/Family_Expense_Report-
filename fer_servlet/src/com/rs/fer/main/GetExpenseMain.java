package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetExpenseMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		int expenseId = 2;

		// 2. Call the service for business logic execution

		FERService ferService = new FERServiceImpl();
		Expense expense = ferService.getExpense(expenseId);
		

		// 3. Display the status
		if(expense == null) {
			System.out.println("Expense is not found...");
		}else {
			
				
				System.out.println("ID: " + expense.getId());
				System.out.println("Expense type: " + expense.getType());
				System.out.println("Date: " + expense.getDate());
				System.out.println("Price: " + expense.getPrice());
				System.out.println("Number Of items: " + expense.getNumberOfItems());
				System.out.println("Total: " + expense.getTotal());
				System.out.println("By whom: " + expense.getByWhom());
				System.out.println("User ID: " + expense.getUserId());

				System.out.println("===================================================");
			
		}
		
	}

}
