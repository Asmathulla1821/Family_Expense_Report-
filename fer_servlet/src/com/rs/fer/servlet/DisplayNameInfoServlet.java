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

@WebServlet("/displayNameInfo")
public class DisplayNameInfoServlet extends HttpServlet {

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
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		// 2.
		User user = ferService.getUser(userId);
		session.setAttribute("user", user);

		// 3.
		out.println("<table align='center' border='2'>");

		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>Name Info</th>");

		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>First Name</td>");
		out.println("	<td><input type='text' name='firstName' value='" + user.getFirstName() + "'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Middle Name</td>");
		out.println("	<td><input type='text' name='middleName' value='" + user.getMiddleName() + "'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Last Name</td>");
		out.println("	<td><input type='text' name='lastName' value='" + user.getLastName() + "'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>");
		out.println("<input type='submit' value='Next' onclick=\"javascript: submitForm('displayContactInfo')\">");
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
