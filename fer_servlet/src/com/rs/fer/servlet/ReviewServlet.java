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

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

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
		address.setLineOne(request.getParameter("line1"));
		address.setLineTwo(request.getParameter("line2"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setPincode(request.getParameter("pinCode"));
		address.setCountry(request.getParameter("country"));
		// 3.
		out.println("<table align='center' border='2'>");
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>Conform Details</th>");

		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>First Name</td>");
		out.println("	<td><input type='text' name='firstName' value='"+user.getFirstName()+"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Middle Name</td>");
		out.println("	<td><input type='text' name='middleName' value='"+user.getMiddleName() +"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Last Name</td>");
		out.println("	<td><input type='text' name='lastName' value='"+user.getLastName()+"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Email</td>");
		out.println("	<td><input type='text' name='email' value='"+user.getEmail()+"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Mobile</td>");
		out.println("	<td><input type='text' name='mobile' value='"+user.getMobile() +"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Line1</td>");
		out.println("	<td><input type='text' name='line1' value='"+address.getLineOne()+"' disabled='true'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("	<td>Line2</td>");
		out.println("	<td><input type='text' name='line2' value='"+address.getLineTwo() +"' disabled='true'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>City</td>");
		out.println("	<td><input type='text' name='city' value='"+address.getCity() +"' disabled='true'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>State</td>");
		out.println("	<td><input type='text' name='state' value='"+address.getState() +"' disabled='true'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Pin Code</td>");
		out.println("	<td><input type='text' name='pinCode' value='"+address.getPincode() +"' disabled='true'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<td>Country</td>");
		out.println("	<td><input type='text' name='country' value='"+address.getCountry() +"' disabled='true'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("	<th align='center' colspan='2'>");
		out.println("<input type='submit' value='Conform' onclick=\"javascript: submitForm('updateUser')\">");
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
