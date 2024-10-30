<%@page import="java.util.List"%>
<%@page import="com.rs.fer.bean.Expense"%>
<jsp:include page="Layout/Header_LeftFrame.jsp"/>

<%
		List<Expense> expenses = (List<Expense>) session.getAttribute("expenses");
	
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
		out.println("	<td><input type='text' name='type' value='" + expense.getType() + "' disabled='true'></td>");
		out.println("	<td><input type='text' name='date' value='" + expense.getDate() + "' disabled='true'></td>");
		out.println("	<td><input type='text' name='price' value='" + expense.getPrice() + "' disabled='true'></td>");
		out.println("	<td><input type='text' name='numberOfItems' value='" + expense.getNumberOfItems()
		+ "' disabled='true'></td>");
		out.println("	<td><input type='text' name='total' value='" + expense.getTotal() + "' disabled='true'></td>");
		out.println("	<td><input type='text' name='byWhom' value='" + expense.getByWhom() + "' disabled='true'></td>");
	
		out.println("	</tr>");
	}
	
	out.println("	<tr>");
	out.println("		<td align='center' colspan='7'><a href=''>Print</a></td>");
	out.println("	</tr>");
	
	out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp"/>