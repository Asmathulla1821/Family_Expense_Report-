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

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. get the input from UI
		Expense expense = new Expense();
		expense.setType(request.getParameter("expenseType"));
		expense.setDate(request.getParameter("date"));
		expense.setPrice(Float.parseFloat(request.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(request.getParameter("nummberOfItems")));
		expense.setTotal(Float.parseFloat(request.getParameter("total")));
		expense.setByWhom(request.getParameter("byWhome"));

				HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		
		expense.setUserId(userId);

		// 2. Call the service for business logic execution

		boolean isExpenseAdded = ferService.addExpense(expense);

		// 3. Display the status
		PrintWriter out = response.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// Body
		if (isExpenseAdded) {

			out.println("Expens added successful...!");
		} else {
			out.println("Expens add is faild...!");
		}

		// Footer
		LayoutUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}

