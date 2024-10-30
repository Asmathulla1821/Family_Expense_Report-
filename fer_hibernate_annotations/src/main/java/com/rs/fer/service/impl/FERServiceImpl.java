package com.rs.fer.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.HibernateUtil;

public class FERServiceImpl implements FERService {

	public boolean registration(User user) {

		boolean isRegister = false;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		System.out.println(factory);
		factory = HibernateUtil.getSessionFactory();
		System.out.println(factory);
		factory = HibernateUtil.getSessionFactory();
		System.out.println(factory);
		factory = HibernateUtil.getSessionFactory();
		System.out.println(factory);
		factory = HibernateUtil.getSessionFactory();
		System.out.println(factory);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int reg = (Integer) session.save(user);
		if (reg > 0)
			isRegister = true;
		transaction.commit();
		session.close();

		return isRegister;
	}

	public boolean login(String userName, String password) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));

		List<User> users = criteria.list();
		session.close();

		return (users == null || users.size() > 0);
	}

	public boolean addExpense(Expense expense) {

		boolean isAddExpense = false;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int add = (Integer) session.save(expense);
		if (add > 0)
			isAddExpense = true;

		transaction.commit();
		session.close();

		return isAddExpense;
	}

	public boolean editExpense(Expense expense) {

		boolean isExpenseEdited = true;

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.update(expense);
			transaction.commit();
		} catch (Exception ex) {
			isExpenseEdited = false;
		}
		session.close();

		return isExpenseEdited;
	}

	public boolean deleteExpense(int expenseId) {

		boolean isExpenseDeleted = true;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Expense expense = new Expense();
			expense.setId(expenseId);
			session.delete(expense);
			transaction.commit();
		} catch (Exception e) {
			isExpenseDeleted = false;
		}
		session.close();

		return isExpenseDeleted;
	}

	public boolean resetPassword(String newPassword, int userId, String currentPassword) {

		boolean isResetPassword = false;

		Session session = HibernateUtil.getSessionFactory().openSession();

		SQLQuery sqlQuery = session
				.createSQLQuery("UPDATE USER_ANNOTATIONS SET PASSWORD=? WHERE USER_ID=? AND PASSWORD=?");

		sqlQuery.setParameter(0, newPassword);
		sqlQuery.setParameter(1, userId);
		sqlQuery.setParameter(2, currentPassword);

		Transaction transaction = session.beginTransaction();
		isResetPassword = sqlQuery.executeUpdate() > 0;
		transaction.commit();

		session.close();

		return isResetPassword;
	}

	public boolean updateUser(User user) {

		boolean isUpdated = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			isUpdated = false;
		}
		session.close();

		return isUpdated;
	}

	public List<Expense> expenseOptions(int userId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Expense.class);
		criteria.add(Restrictions.eq("userId", userId));

		List<Expense> expenses = criteria.list();
		session.close();

		return expenses;
	}

	public Expense getExpenxe(int expenseId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Expense expense = (Expense) session.get(Expense.class, expenseId);
		session.close();

		return expense;
	}

	public User getUser(int userId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User) session.get(User.class, userId);
		if (user.getAddress() == null)
			user.setAddress(new Address());

		session.close();

		return user;
	}

	public List<Expense> expenseReport(int userId, String expenceType, String fromDate, String todate) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Expense.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("type", expenceType));
		criteria.add(Restrictions.between("date", fromDate, todate));

		List<Expense> expenses = criteria.list();
		session.close();

		return expenses;
	}
}
