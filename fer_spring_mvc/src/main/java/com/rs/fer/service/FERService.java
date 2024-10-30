package com.rs.fer.service;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;

public interface FERService {

	public boolean registration(User user);

	public int login(String userName, String password);

	public boolean addExpense(Expense expense);

	public boolean editExpense(Expense expense);

	public boolean deleteExpense(int id);

	public boolean resetPassword(String newPassword, int userId, String currentPassword);

	List<Expense> expenseOptions(int userId);

	public boolean updateUser(User user);

	Expense getExpense(int expenseId);

	User getUser(int userId);

	List<Expense> expenseReport(int expenseId, String expenceType, String fromDate, String todate);

	boolean isUsernameAvailable(String userName);
}