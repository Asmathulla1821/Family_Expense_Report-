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

@WebServlet("/expenseReport")
public class ExpenceReportServlet extends HttpServlet {

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

		String expenseType = request.getParameter("expenseType");
		String fromDate = request.getParameter("fromDate");
		String todate = request.getParameter("toDate");
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		// 2.
		List<Expense> expenses = ferService.expenseReport(userId, expenseType, fromDate, todate);

		// 3.

		out.println("<table align='center' border='2'>");
		out.println("<tr>");
		out.println("<th align='center' colspan='7'>Expense Report</th>");

		out.println("</tr>");

		out.println("<tr>");
		out.println("	<th>Expense type</th>");
		out.println("		<th>Date</th>");
		out.println("	<th>Price</th>");
		out.println("	<th>No Of Items</th>");
		out.println("	<th>TOtal</th>");
		out.println("	<th>By Whom</th>");
		out.println("</tr>");

		for (Expense expense : expenses) {
			out.println("<tr>");
			out.println("	<td><input type='text' name='expenseType' value='" + expense.getType() + "' disabled='true'></td>");
			out.println("	<td><input type='text' name='date' value='" + expense.getDate() + "' disabled='true'></td>");
			out.println("	<td><input type='text' name='price' value='" + expense.getPrice() + "' disabled='true'></td>");
			out.println(
					"	<td><input type='text' name='nummberOfItems' value='" + expense.getNumberOfItems() + "' disabled='true'></td>");
			out.println("	<td><input type='text' name='total' value='" + expense.getTotal() + "' disabled='true'></td>");
			out.println("	<td><input type='text' name='byWhom' value='" + expense.getByWhom() + "' disabled='true'></td>");

			out.println("	</tr>");
		}

		out.println("	<tr>");
		out.println("		<td align='center' colspan='7'><a href=''>Print</a></td>");
		out.println("	</tr>");

		out.println("</table>");

		// Footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
