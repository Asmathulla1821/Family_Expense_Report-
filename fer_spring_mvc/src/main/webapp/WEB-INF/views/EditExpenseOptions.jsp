<%@page import="java.util.List"%>
<%@page import="com.rs.fer.bean.Expense"%>
<jsp:include page="Layout/Header_LeftFrame.jsp" />


<script>
	function validateEditExpenseOptionsForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.expenseId.value.trim() == '') {
			errors += 'Please enter ExpenseId<br>';
		}

		// 3. Show the errors if present otherwise submit the form

		if (errors != '') {
			//alert(errors);

			// Write the errors to the second row td...
			var divElement = document.getElementById('errorDivId');
			divElement.innerHTML = errors;
			// Show the second row
			var Element = document.getElementById('errorId');
			Element.style.display = '';//block

		} else {
			submitForm('showEditExpense');
		}
	}
</script>

<%
	// Header and Left Frame

// Body

List<Expense> expenses = (List<Expense>) session.getAttribute("expenseOptions");

if (expenses.isEmpty()) {
	session.setAttribute("status", "Expense is not found to edit...!");
} else {

	out.println("<div id='errorDivId' style='color:red'></div>");

	out.println("ExpenseId: <font color='red' id='errorId'>*</font>");
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
	out.println("<input type='button' value='Next' onclick=\"javascript:validateEditExpenseOptionsForm()\">");

}
%>

<jsp:include page="Layout/Footer.jsp" />