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

@WebServlet("/displayAddressInfo")
public class DisplayAddressInfoServlet extends HttpServlet {

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
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));
		// 3.
		out.println("<table align='center' border='2'>");
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>Address Info</th>");

		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Line1</td>");
		out.println("	<td><input type='text' name='line1' value='"+address.getLineOne()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Line2</td>");
		out.println("	<td><input type='text' name='line2' value='"+address.getLineTwo() +"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>City</td>");
		out.println("	<td><input type='text' name='city' value='"+address.getCity() +"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>State</td>");
		out.println("	<td><input type='text' name='state' value='"+address.getState() +"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Pin Code</td>");
		out.println("	<td><input type='text' name='pinCode' value='"+address.getPincode() +"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Country</td>");
		out.println("	<td><input type='text' name='country' value='"+address.getCountry() +"'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>");
		out.println("<input type='submit' value='Next' onclick=\"javascript: submitForm('review')\">");
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
