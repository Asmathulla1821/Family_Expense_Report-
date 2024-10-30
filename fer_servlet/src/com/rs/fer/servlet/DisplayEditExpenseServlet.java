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

import com.rs.fer.bean.Expense;
import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/displayEditExpense")
public class DisplayEditExpenseServlet extends HttpServlet {

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
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));
		session.setAttribute("expenseId", expenseId);

		// 2.
		Expense expense = ferService.getExpense(expenseId);

		// 3.
		out.println("<table align='center' border='2'>");
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>Edit Expense</th>");

		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Expense Type</td>");
		out.println("	<td><input type='text' name='expenseType' value='"+expense.getType()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Date</td>");
		out.println("	<td><input type='text' name='date' value='"+expense.getDate()+"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Price</td>");
		out.println("	<td><input type='text' name='price' value='"+expense.getPrice()+"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Number Of Items</td>");
		out.println("	<td><input type='text' name='numberOfItems' value='"+expense.getNumberOfItems()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Total</td>");
		out.println("	<td><input type='text' name='total' value='"+expense.getTotal()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>By Whom</td>");
		out.println("	<td><input type='text' name='byWhom' value='"+expense.getByWhom()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>");
		out.println("<input type='submit' value='Edit Expense' onclick=\"javascript: submitForm('editExpense')\">");
		out.println("</th>");
		out.println("</tr>");
		
		out.println("</table>");

		// Footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
