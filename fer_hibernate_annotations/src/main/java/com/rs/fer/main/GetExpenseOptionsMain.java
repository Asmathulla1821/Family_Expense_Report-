package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetExpenseOptionsMain {

	public static void main(String[] args) {

		// handle the request

		// 1. get the input from UI
		int userId = 1;

		// 2. Call the service for business logic execution

		FERService ferService = new FERServiceImpl();
		List<Expense> expenses = ferService.expenseOptions(userId);
		

		// 3. Display the status
		if(expenses.isEmpty()) {
			System.out.println("Expense is not found...");
		}else {
			
			for(Expense expense: expenses) {
				
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

}
