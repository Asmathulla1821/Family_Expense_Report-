package com.rs.fer.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.HibernateUtil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		boolean isRegister = false;
		int reg = (int) session.save(user);
		if (reg > 0)
			isRegister = true;

		transaction.commit();
		session.close();

		return isRegister;
	}

	@Override
	public boolean login(String userName, String password) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));

		List<User> users = criteria.list();
		session.close();

		return (users == null || users.size() > 0);
	}

	@Override
	public boolean addExpense(Expense expense) {

		boolean isAddExpense = false;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int add = (int) session.save(expense);
		if (add > 0)
			isAddExpense = true;

		transaction.commit();
		session.close();

		return isAddExpense;
	}

	@Override
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

	@Override
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

	@Override
	public boolean resetPassword(String newPassword, int userId, String currentPassword) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("update User u set u.password=:newPass where id=:id and u.password=:currentPass");

		query.setParameter("newPass", newPassword);
		query.setParameter("id", userId);
		query.setParameter("currentPass", currentPassword);

		Transaction transaction = session.beginTransaction();
		boolean isResetPassword = query.executeUpdate() > 0;

		transaction.commit();
		session.close();

		return isResetPassword;
	}

	@Override
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

	@Override
	public List<Expense> expenseOptions(int userId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Expense.class);
		criteria.add(Restrictions.eq("userId", userId));

		List<Expense> expenses = criteria.list();
		session.close();

		return expenses;
	}

	@Override
	public Expense getExpenxe(int expenseId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Expense expense = (Expense) session.get(Expense.class, expenseId);
		session.close();

		return expense;
	}

	@Override
	public User getUser(int userId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User) session.get(User.class, userId);
		if (user.getAddress() == null)
			user.setAddress(new Address());

		session.close();

		return user;
	}

	@Override
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
