package com.rs.fer.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.repository.ExpenseRepository;
import com.rs.fer.repository.UserRepository;
import com.rs.fer.service.FERService;

@Service
public class FERServiceImpl implements FERService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Transactional
	@Override
	public boolean registration(User user) {
		return userRepository.save(user).getId() > 0;
	}

	@Override
	public int login(String userName, String password) {
		List<User> users = userRepository.findByUserNameAndPassword(userName, password);
		if (users != null && !users.isEmpty()) {
			return users.get(0).getId();
		}
		return 0;
	}

	@Transactional
	@Override
	public boolean addExpense(Expense expense) {
		return expenseRepository.save(expense).getId() > 0;
	}

	@Override
	public boolean editExpense(Expense expense) {
		return addExpense(expense);
	}

	@Transactional
	@Override
	public boolean deleteExpense(int expenseId) {
		try {
			expenseRepository.deleteById(expenseId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean resetPassword(String newPassword, int userId, String currentPassword) {
		User user = getUser(userId);
		if (currentPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		return registration(user);
	}

	@Override
	public List<Expense> expenseOptions(int userId) {
		return expenseRepository.findByUserId(userId);
	}

	@Override
	public Expense getExpense(int expenseId) {
		return expenseRepository.findById(expenseId).get();
	}

	@Override
	public User getUser(int userId) {
		User user = null;
		Optional<User> optionalObj = userRepository.findById(userId);
		if (optionalObj.isPresent()) {
			user = optionalObj.get();
			if (user.getAddress() == null) {
				user.setAddress(new Address());
			}
		}
		return user;
	}

	@Override
	public List<Expense> expenseReport(int userId, String expenceType, String fromDate, String toDate) {
		return expenseRepository.findByUserIdAndTypeAndDateBetween(userId, expenceType, fromDate, toDate);
	}

	@Override
	public boolean isUsernameAvailable(String userName) {
		
		List<User> users = userRepository.findByUserName(userName);
		return (users == null || users.isEmpty());
	}

}
