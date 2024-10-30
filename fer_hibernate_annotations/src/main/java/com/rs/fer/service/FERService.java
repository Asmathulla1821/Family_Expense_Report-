package com.rs.fer.service;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;

public interface FERService {

	public boolean registration(User user);

	public boolean login(String userName, String password);

	public boolean addExpense(Expense expense);

	public boolean editExpense(Expense expense);

	public boolean deleteExpense(int id);

	public boolean resetPassword(String newPassword, int userId, String currentPassword);

	public List<Expense> expenseOptions(int userId);
	
	public boolean updateUser( User user);

	public Expense getExpenxe(int expenseId);
	
	public User getUser(int userId);
	
	public List<Expense> expenseReport(int expenseId, String expenceType, String fromDate, String todate );
	
	
	
}
