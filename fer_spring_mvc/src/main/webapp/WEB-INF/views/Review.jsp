<%@page import="com.rs.fer.bean.Address"%>
<%@page import="com.rs.fer.bean.User"%>
<jsp:include page="Layout/Header_LeftFrame.jsp"/>
<%
	// Header and Left Frame

	// Body
	
	// 1.
	User user = (User) session.getAttribute("user");
	Address address = user.getAddress();
	
	// 2.
	out.println("<table align='center' border='2'>");
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>Conform Details</th>");
	
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>First Name</td>");
	out.println("	<td><input type='text' name='firstName' value='" + user.getFirstName() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Middle Name</td>");
	out.println("	<td><input type='text' name='middleName' value='" + user.getMiddleName() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Last Name</td>");
	out.println("	<td><input type='text' name='lastName' value='" + user.getLastName() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Email</td>");
	out.println("	<td><input type='text' name='email' value='" + user.getEmail() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Mobile</td>");
	out.println("	<td><input type='text' name='mobile' value='" + user.getMobile() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Line1</td>");
	out.println("	<td><input type='text' name='lineOne' value='" + address.getLineOne() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Line2</td>");
	out.println("	<td><input type='text' name='lineTwo' value='" + address.getLineTwo() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>City</td>");
	out.println("	<td><input type='text' name='city' value='" + address.getCity() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>State</td>");
	out.println("	<td><input type='text' name='state' value='" + address.getState() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Pin Code</td>");
	out.println("	<td><input type='text' name='pinCode' value='" + address.getPincode() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Country</td>");
	out.println("	<td><input type='text' name='country' value='" + address.getCountry() + "' disabled='true'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>");
	out.println("<input type='submit' value='Conform' onclick=\"javascript: submitForm('updateUser')\">");
	out.println("</th>");
	out.println("</tr>");
	
	out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp"/>