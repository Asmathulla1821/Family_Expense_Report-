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

@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {
	FERService ferService = null;

	@Override
	public void init() throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// 1. get the input from UI
		Expense expense = new Expense();
		expense.setType(request.getParameter("expenseType"));
		expense.setDate(request.getParameter("date"));
		expense.setPrice(Float.parseFloat(request.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
		expense.setTotal(Float.parseFloat(request.getParameter("total")));
		expense.setByWhom(request.getParameter("byWhom"));

		
		int expenseId = Integer.parseInt(session.getAttribute("expenseId").toString());

		expense.setId(expenseId);

		// 2. Call the service for business logic execution

		boolean isExpenseEdited = ferService.editExpense(expense);

		// 3. Display the status
		
		// Body
		if (isExpenseEdited) {

			out.println("Expens edited successful...!");
		} else {
			out.println("Expens edit is faild...!");
		}

		// Footer
		LayoutUtil.displayFooter(request, response);

	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
