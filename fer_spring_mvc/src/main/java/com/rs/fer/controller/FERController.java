package com.rs.fer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;

@Controller
public class FERController {

	@Autowired
	private FERService ferService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("Login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String userName, String password, HttpServletRequest request) {
		// 2. Call the service for business logic execution
		int userId = ferService.login(userName, password);

		// 3. Display the status
		String path = null;
		HttpSession session = request.getSession();
		if (userId > 0) {

			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);

			// Body
			session.setAttribute("status", "Welcome to user: " + userName + "...!");
			path = "Dashboard";
		} else {
			session.setAttribute("status", "Incorrect username/Password please try again...!");
			path = "Login";

		}
		return new ModelAndView(path);
	}

	@RequestMapping(value = "/showAddExpense", method = RequestMethod.POST)
	public ModelAndView showAddExpense() {
		return new ModelAndView("AddExpense");
	}

	@RequestMapping(value = "/addExpense", method = RequestMethod.POST)
	public ModelAndView AddExpense(@ModelAttribute Expense expense, HttpServletRequest request) {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		expense.setUserId(userId);

		// 2. Call the service for business logic execution

		boolean isExpenseAdded = ferService.addExpense(expense);

		// 3. Display the status

		// Body
		if (isExpenseAdded) {

			session.setAttribute("status", "Expens added successful...!");
		} else {
			session.setAttribute("status", "Expens add is faild...!");
		}
		return new ModelAndView("Dashboard");
	}

	@RequestMapping(value = "/showRegistration", method = RequestMethod.GET)
	public ModelAndView showRegistration(HttpServletRequest request) {
		
		request.getSession().setAttribute("ferService", ferService);
		
		return new ModelAndView("Registration");
	}
	
	@RequestMapping(value = "/isUsernameAvailable", method = RequestMethod.GET)
	public ModelAndView isUsernameAvailable(@RequestParam String userName, HttpServletRequest request) {
		
		//2.
		boolean isUsernameAvailable = ferService.isUsernameAvailable(userName);
		
		// 3.
		HttpSession session = request.getSession();
		session.setAttribute("status", isUsernameAvailable ? "Y" : "N");
		
		return new ModelAndView("FERAJAX");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute User user, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setAttribute("userName", user.getUserName());
		String path = null;

		boolean isUsernameAvailable = ferService.isUsernameAvailable(user.getUserName());
		if (isUsernameAvailable) {

			boolean isRegister = ferService.registration(user);
			if (isRegister) {
				session.setAttribute("status", "User registration is successful...!");
				path = "Login";
			} else {
				session.setAttribute("status", "User registration is faild...!");
				path = "Registration";

			}

		} else {
			session.setAttribute("status", "UserName is not available. Please choose something else...");
			path = "Registration";

		}

		return new ModelAndView(path);
	}

	@RequestMapping(value = "/showEditExpenseOptions", method = RequestMethod.POST)
	public ModelAndView showEditExpenseOptions(HttpServletRequest request) {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		List<Expense> expenseOptions = ferService.expenseOptions(userId);
		session.setAttribute("expenseOptions", expenseOptions);

		return new ModelAndView("EditExpenseOptions");
	}

	@RequestMapping(value = "/showEditExpense", method = RequestMethod.POST)
	public ModelAndView showEditExpense(int expenseId, HttpServletRequest request) {
		// 1.
		HttpSession session = request.getSession();

		// 2.
		Expense expense = ferService.getExpense(expenseId);
		session.setAttribute("expense", expense);

		return new ModelAndView("EditExpense");
	}

	@RequestMapping(value = "/editExpense", method = RequestMethod.POST)
	public ModelAndView editExpense(@ModelAttribute Expense expense, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Expense expenseObj = (Expense) session.getAttribute("expense");

		expense.setUserId(expenseObj.getUserId());
		expense.setId(expenseObj.getId());

		// 2. Call the service for business logic execution

		boolean isExpenseEdited = ferService.addExpense(expense);

		// 3. Display the status

		// Body
		if (isExpenseEdited) {

			session.setAttribute("status", "Expens edited successful...!");
		} else {
			session.setAttribute("status", "Expens edit is failed...!");
		}
		return new ModelAndView("Dashboard");
	}

	@RequestMapping(value = "/showResetPassword", method = RequestMethod.POST)
	public ModelAndView showResetPassword() {
		return new ModelAndView("ResetPassword");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam String newPassword, String currentPassword,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		User user = ferService.getUser(userId);
		if (currentPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
		}
		boolean isReset = ferService.registration(user);

		if (isReset) {
			session.setAttribute("status", "ResetPassword successful...!");
		} else {
			session.setAttribute("status", "ResetPassword is faild...!");
		}
		return new ModelAndView("Dashboard");
	}

	@RequestMapping(value = "/showDeleteExpenseOptions", method = RequestMethod.POST)
	public ModelAndView showDeleteExpenseOptions(HttpServletRequest request) {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		List<Expense> expenseOptions = ferService.expenseOptions(userId);
		session.setAttribute("expenseOptions", expenseOptions);

		return new ModelAndView("DeleteExpenseOptions");
	}

	@RequestMapping(value = "/deleteExpense", method = RequestMethod.POST)
	public ModelAndView deleteExpense(@RequestParam int expenseId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		// 2. Call the service for business logic execution

		boolean isDeleted = ferService.deleteExpense(expenseId);

		// 3. Display the status

		// Body
		if (isDeleted) {

			session.setAttribute("status", "Expens deleted successfully...!");
		} else {
			session.setAttribute("status", "Expens delete is failed...!");
		}
		return new ModelAndView("Dashboard");
	}

	@RequestMapping(value = "/showExpenseReport", method = RequestMethod.POST)
	public ModelAndView showExpenseReport() {
		return new ModelAndView("ExpenseReport");
	}

	@RequestMapping(value = "/expenseReport", method = RequestMethod.POST)
	public ModelAndView expenseReport(@RequestParam String type, String fromDate, String toDate,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		List<Expense> expenses = ferService.expenseReport(userId, type, fromDate, toDate);
		session.setAttribute("expenses", expenses);

		return new ModelAndView("ExpenseReportPost");
	}

	@RequestMapping(value = "/showNameInfo", method = RequestMethod.POST)
	public ModelAndView showNameInfo(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		User user = ferService.getUser(userId); 
		session.setAttribute("user", user);
		return new ModelAndView("NameInfo");
	}

	@RequestMapping(value = "/showContactInfo", method = RequestMethod.POST)
	public ModelAndView showContactInfo(@RequestParam String firstName, String middleName, String lastName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		session.setAttribute("user", user);
		return new ModelAndView("ContactInfo");
	}

	@RequestMapping(value = "/showAddressInfo", method = RequestMethod.POST)
	public ModelAndView showAddressInfo(@RequestParam String email, String mobile,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setEmail(email);
		user.setMobile(mobile);
		session.setAttribute("user", user);
		return new ModelAndView("AddressInfo");
	}
	@RequestMapping(value = "/showReview", method = RequestMethod.POST)
	public ModelAndView showReview(@ModelAttribute Address address, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setAddress(address);
		session.setAttribute("user", user);
		return new ModelAndView("Review");
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user, HttpServletRequest request) {

		HttpSession session = request.getSession();
		// 2. Call the service for business logic execution

		boolean isDeleted = ferService.updateUser(user);

		// 3. Display the status

		// Body
		if (isDeleted) {

			session.setAttribute("status", "Update user is successfully...!");
		} else {
			session.setAttribute("status", "Update user is failed...!");
		}
		return new ModelAndView("Dashboard");
	}

}
