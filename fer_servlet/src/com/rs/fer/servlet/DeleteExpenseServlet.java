package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {
	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// handle the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// 1. get the input from UI
		int id = Integer.parseInt(request.getParameter("expenseId").toString());


		// 2. Call the service for business logic execution
		boolean isExpenseDeleted = ferService.deleteExpense(id);

		// 3. Display the status
		if (isExpenseDeleted) {
			out.println("Expens deleted successful...!");
		} else {
			out.println("Expens delettion is faild...!");
		}

		// Footer
		LayoutUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
