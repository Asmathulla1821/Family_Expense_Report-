package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// handle the request
		// Header and left frame

		// 1. get the input from UI
		HttpSession session = request.getSession();

		String newPassword = request.getParameter("newPassword");
		String currentPassword =  request.getParameter("currentPassword");

		 int userId= Integer.parseInt(session.getAttribute("userId").toString());

		// 2. Call the service for business logic execution
		boolean isResetPassword = ferService.resetPassword(newPassword, userId, currentPassword);

		// 3. Display the status
		PrintWriter out = response.getWriter();

		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
		// Body
		if (isResetPassword) {
			out.println("Password Updation is Successful...!");
		} else {
			out.println("Password Updation is faild ...!");
		}

		// Footer
		LayoutUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
