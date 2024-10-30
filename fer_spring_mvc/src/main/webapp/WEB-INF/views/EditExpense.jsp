<%@page import="com.rs.fer.bean.Expense"%>
<jsp:include page="Layout/Header_LeftFrame.jsp" />

<script>
	function validateEditExpenseForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.type.value.trim() == '') {
			errors += 'Please enter Expense Type<br>';
		}
		if (form.date.value.trim() == '') {
			errors += 'Please enter Date<br>';
		}
		if (form.price.value.trim() == '') {
			errors += 'Please enter Price<br>';
		}
		if (form.numberOfItems.value.trim() == '') {
			errors += 'Please enter Number Of Items<br>';
		}
		if (form.total.value.trim() == '') {
			errors += 'Please enter Total<br>';
		}
		if (form.byWhom.value.trim() == '') {
			errors += 'Please enter By Whom<br>';
		}

		// 3. Show the errors if present otherwise submit the form

		if (errors != '') {
			//alert(errors);

			// Write the errors to the second row td...
			var tdElement = document.getElementById('errorTdId');
			tdElement.innerHTML = errors;
			// Show the second row
			var trElement = document.getElementById('errorTrId');
			trElement.style.display = '';//block

		} else {
			submitForm('editExpense');
		}
	}
</script>


<%
	// 2.
Expense expense = (Expense) session.getAttribute("expense");

// 3.
out.println("<table align='center' border='2'>");
out.println("<tr>");
out.println("	<th align='center' colspan='2'>Edit Expense</th>");

out.println("</tr>");

out.println("<tr style='display: none; color: red' id='errorTrId'>");
out.println("<td align='left' colspan='2' id='errorTdId'></td>");

out.println("</tr>");

out.println("<tr>");
out.println("	<td>Expense Type<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='type' value='" + expense.getType() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Date<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='date' value='" + expense.getDate() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Price<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='price' value='" + expense.getPrice() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Number Of Items<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='numberOfItems' value='" + expense.getNumberOfItems() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Total<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='total' value='" + expense.getTotal() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>By Whom<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='byWhom' value='" + expense.getByWhom() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<th align='center' colspan='2'>");
out.println("<input type='button' value='EditExpense' onclick=\"javascript:validateEditExpenseForm()\">");
out.println("</th>");
out.println("</tr>");

out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp" />
