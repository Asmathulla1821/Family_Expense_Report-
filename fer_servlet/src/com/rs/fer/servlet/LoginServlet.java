package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// 2. Call the service for business logic execution
		int userId = ferService.login(userName, password);

		// 3. Display the status
		PrintWriter out = response.getWriter();

		if (userId > 0) {

			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);
			
			// Header and left frame
			LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
			
			// Body
			out.println("Welcome to user: " + userName + "...!");
			
			// Footer
			LayoutUtil.displayFooter(request, response);
		} else {
			out.println("Incorrect username/Password please try again...!");
			
			request.getRequestDispatcher("Login.html").include(request, response);
		}

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
