package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// handle the request

		// 1. get the input from UI
		User user = new User();

		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobileNumber"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		// 2. Call the service for business logic execution
		boolean isRegister = ferService.registration(user);

		// 3. Display the status
		PrintWriter out = response.getWriter();
		String path = null;

		if (isRegister) {
			out.println("User registration is successful...!");
			path = "Login.html";
		} else {
			out.println("User registration is faild...!");
			path = "Registration.html";

		}
		request.getRequestDispatcher(path).include(request, response);

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
