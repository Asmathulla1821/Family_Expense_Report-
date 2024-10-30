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

@WebServlet("/displayDeleteExpenseOptions")
public class DisplayDeleteExpenseOptionsServlet extends HttpServlet {

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
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		List<Expense> expenses = ferService.expenseOptions(userId);

		if (expenses.isEmpty()) {
			out.println("Expense is not found to delete...!");
		} else {

			out.println("ExpenseId: ");
			out.println("&nbsp;&nbsp;");
			out.println("<select name='expenseId'>");
			out.println("   <option value=''>Please Select the Expense Id</option>");

			int id = 0;
			String text = null;
			for (Expense expense : expenses) {
				id = expense.getId();
				text = id + "--" + expense.getType() + "--" + expense.getDate() + "--" + expense.getPrice() + "--"
						+ expense.getNumberOfItems() + "--" + expense.getTotal() + "--" + expense.getByWhom();
				out.println("	<option Value='" + id + "'>" + text + "</option>");
			}

			out.println("</select>");
			out.println("&nbsp;&nbsp;");
			out.println("<input type='submit' value='Delete Expense' onclick=\"javascript: submitForm('deleteExpense')\">");

		}
		// Footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {

		ferService = null;
	}

}
