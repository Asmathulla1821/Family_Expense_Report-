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
import com.rs.fer.bean.User;
import com.rs.fer.layout.util.LayoutUtil;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/displayContactInfo")
public class DisplayContactInfoServlet extends HttpServlet {

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
		// 2.
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
		// 3.
		out.println("<table align='center' border='2'>");
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>Contact Info</th>");

		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Email</td>");
		out.println("	<td><input type='text' name='email' value='" + user.getEmail() + "'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Mobile</td>");
		out.println("	<td><input type='text' name='mobile' value='" + user.getMobile() + "'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>");
		out.println("<input type='submit' value='Next' onclick=\"javascript: submitForm('displayAddressInfo')\">");
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
