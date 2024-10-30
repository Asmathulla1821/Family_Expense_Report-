<%@page import="com.rs.fer.bean.User"%>
<jsp:include page="Layout/Header_LeftFrame.jsp"/>

<script>
	function validateNameInfoForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.firstName.value.trim() == '') {
			errors += 'Please enter First Name<br>';
		}
		if (form.lastName.value.trim() == '') {
			errors += 'Please enter Last Name<br>';
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
			submitForm('showContactInfo');
		}
	}
</script>

<%
	// 1.
	User user = (User) session.getAttribute("user");
	
	// 3.
	out.println("<table align='center' border='2'>");
	
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>Name Info</th>");
	
	out.println("</tr>");
	
	out.println("<tr style='display: none; color: red' id='errorTrId'>");
	out.println("<td align='left' colspan='2' id='errorTdId'></td>");
	
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>First Name<font color='red'>*</font></td>");
	out.println("	<td><input type='text' name='firstName' value='" + user.getFirstName() + "'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Middle Name</td>");
	out.println("	<td><input type='text' name='middleName' value='" + user.getMiddleName() + "'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Last Name<font color='red'>*</font></td>");
	out.println("	<td><input type='text' name='lastName' value='" + user.getLastName() + "'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>");
	out.println("<input type='button' value='Next' onclick='javascript: validateNameInfoForm()'>");
	out.println("</th>");
	out.println("</tr>");
	
	out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp"/>