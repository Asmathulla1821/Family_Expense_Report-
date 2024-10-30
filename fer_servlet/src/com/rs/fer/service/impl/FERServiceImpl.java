package com.rs.fer.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.DBUtil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {

		boolean isRegister = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "INSERT INTO USER (FIRSTNAME, MIDDLENAME, LASTNAME, EMAIL, USERNAME, PASSWORD, MOBILE) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUserName());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getMobile());

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {
				isRegister = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);

		}

		return isRegister;
	}

	@Override
	public int login(String userName, String password) {

		int userId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			// 4. Execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();

			// 4.1 Processing the results
			while (resultSet.next()) {
				userId = resultSet.getInt(1);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}
		return userId;
	}

	@Override
	public boolean addExpense(Expense expense) {

		boolean isRegister = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "INSERT INTO EXPENSE (TYPE, DATE, PRICE, NUMBEROFITEMS, TOTAL, BYWHOM, USERID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getUserId());

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {
				isRegister = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);
		}
		return isRegister;
	}

	@Override
	public boolean editExpense(Expense expense) {

		boolean isEditedExpense = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "UPDATE EXPENSE SET TYPE=?, DATE=?, PRICE=?, NUMBEROFITEMS=?, TOTAL=?, BYWHOM=? WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getId());

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {
				isEditedExpense = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return isEditedExpense;

	}

	@Override
	public boolean deleteExpense(int id) {

		boolean isExpenseDeleted = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "DELETE FROM EXPENSE WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setInt(1, id);

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {
				isExpenseDeleted = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return isExpenseDeleted;
	}

	@Override
	public boolean resetPassword(String newPassword, int userId, String currentPassword) {

		boolean isResetPassword = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "UPDATE USER SET PASSWORD=? WHERE ID=? AND PASSWORD=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, currentPassword);

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {
				isResetPassword = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return isResetPassword;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean updateUser(User user) {

		boolean isUpdated = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "UPDATE USER SET FIRSTNAME=?, MIDDLENAME=?, LASTNAME=?, EMAIL=?, MOBILE=? WHERE ID=? ";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getMobile());
			preparedStatement.setInt(6, user.getId());

			// 4. Execute the statement
			int numbeOfRecAffected = preparedStatement.executeUpdate();
			if (numbeOfRecAffected > 0) {

				Address address = user.getAddress();
				int addressId = address.getId();

				if (addressId == 0) {
					// insert
					// 3.
					query = "INSERT INTO ADDRESS (LINE1, LINE2, CITY, STATE, PINCODE, COUNTRY, USERID) VALUES (?, ?, ?, ?, ?, ?, ?)";
					preparedStatement = connection.prepareStatement(query);

					// 3.1
					preparedStatement.setString(1, address.getLineOne());
					preparedStatement.setString(2, address.getLineTwo());
					preparedStatement.setString(3, address.getCity());
					preparedStatement.setString(4, address.getState());
					preparedStatement.setString(5, address.getPincode());
					preparedStatement.setString(6, address.getCountry());
					preparedStatement.setInt(7, user.getId());

					numbeOfRecAffected = preparedStatement.executeUpdate();

				} else {
					// Update

					query = "UPDATE ADDRESS SET LINE1=?, LINE2=?, CITY=?, STATE=?, PINCODE=?, COUNTRY=? WHERE ID=?";
					preparedStatement = connection.prepareStatement(query);

					// 3.1
					preparedStatement.setString(1, address.getLineOne());
					preparedStatement.setString(2, address.getLineTwo());
					preparedStatement.setString(3, address.getCity());
					preparedStatement.setString(4, address.getState());
					preparedStatement.setString(5, address.getPincode());
					preparedStatement.setString(6, address.getCountry());
					preparedStatement.setInt(7, address.getId());
					numbeOfRecAffected = preparedStatement.executeUpdate();

				}

				// 4. Execute the statement
				if (numbeOfRecAffected > 0) {

					isUpdated = true;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return isUpdated;
	}

	@Override
	public List<Expense> expenseOptions(int userId) {

		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object
			preparedStatement.setInt(1, userId);

			// 4. Execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();

			// 4.1 Processing the results
			while (resultSet.next()) {

				// a. Get the column data
				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String bywhom = resultSet.getString(7);
				int uid = resultSet.getInt(8);

				// b. Set the column data into the expense object
				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(bywhom);
				expense.setUserId(uid);

				// c. Add the expense object into list object
				expenses.add(expense);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return expenses;
	}

	@Override
	public Expense getExpense(int expenseId) {

		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "SELECT * FROM EXPENSE WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object
			preparedStatement.setInt(1, expenseId);

			// 4. Execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();

			// 4.1 Processing the results
			while (resultSet.next()) {

				// a. Get the column data
				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String bywhom = resultSet.getString(7);
				int uid = resultSet.getInt(8);

				// b. Set the column data into the expense object
				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(bywhom);
				expense.setUserId(uid);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return expense;
	}

	@Override
	public User getUser(int userId) {

		User user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "SELECT U.*, A.* FROM USER U LEFT JOIN ADDRESS A ON U.ID=A.USERID WHERE U.ID=?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setInt(1, userId);

			// 4. Execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();

			// 4.1 Processing the results
			while (resultSet.next()) {

				// a. get user column data
				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String middleName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String username = resultSet.getString(6);
				String password = resultSet.getString(7);
				String mobile = resultSet.getString(8);

				// b. Get address column data
				int addrId = resultSet.getInt(9);
				String lineOne = resultSet.getString(10);
				String lineTwo = resultSet.getString(11);
				String city = resultSet.getString(12);
				String state = resultSet.getString(13);
				String pincode = resultSet.getString(14);
				String country = resultSet.getString(15);
				int uid = resultSet.getInt(16);

				// c. Adding the elements into the user object
				user = new User();
				user.setId(id);
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setUserName(username);
				user.setPassword(password);
				user.setMobile(mobile);

				// d. Adding address data into the address object
				Address address = new Address();

				address.setId(addrId);
				address.setLineOne(lineOne);
				address.setLineTwo(lineTwo);
				address.setCity(city);
				address.setState(state);
				address.setPincode(pincode);
				address.setCountry(country);
				address.setUserId(uid);

				user.setAddress(address);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return user;
	}

	@Override
	public List<Expense> expenseReport(int userId, String expenceType, String fromDate, String todate) {

		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// 1 & 2
			connection = DBUtil.getConnection();

			// 3. Create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID=? AND TYPE=? AND DATE BETWEEN ? AND ?";
			preparedStatement = connection.prepareStatement(query);

			// 3.1 Set the parameter value into preparedStatement object

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, expenceType);
			preparedStatement.setString(3, fromDate);
			preparedStatement.setString(4, todate);

			// 4. Execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();

			// 4.1 Processing the results
			while (resultSet.next()) {

				int Id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String byWhom = resultSet.getString(7);
				int userid = resultSet.getInt(8);

				expense = new Expense();
				expense.setId(Id);
				expense.setType(type);
				expense.setDate(date);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(userid);

				expenses.add(expense);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5.
			DBUtil.closeConnection(connection);

		}

		return expenses;
	}

}
