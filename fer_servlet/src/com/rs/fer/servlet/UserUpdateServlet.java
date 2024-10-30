package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/updateUser")
public class UserUpdateServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header and Left Frame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// Body

		// 1.
		User user = (User) session.getAttribute("user");
		Address address = user.getAddress();

		// 2.
		boolean isUpdated = ferService.updateUser(user);

		// 3.
		if (isUpdated) {
			out.println("User Updated successful...!");
		} else {
			out.println("User upgradation is faild...!");
		}
		// Footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
