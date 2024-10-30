<%@page import="com.rs.fer.bean.User"%>
<jsp:include page="Layout/Header_LeftFrame.jsp"/>

<script>
	function validateContactInfoForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.email.value.trim() == '') {
			errors += 'Please enter Email<br>';
		}
		if (form.mobile.value.trim() == '') {
			errors += 'Please enter Mobile<br>';
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
			submitForm('showAddressInfo');
		}
	}
</script>

<%
		// Header and Left Frame
	
	// Body
	
	// 1.
	User user = (User) session.getAttribute("user");
	// 2.

	// 3.
	out.println("<table align='center' border='2'>");
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>Contact Info</th>");
	
	out.println("</tr>");
	
	out.println("<tr style='display: none; color: red' id='errorTrId'>");
	out.println("<td align='left' colspan='2' id='errorTdId'></td>");
	
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Email<font color='red'>*</font></td>");
	out.println("	<td><input type='text' name='email' value='" + user.getEmail() + "'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<td>Mobile<font color='red'>*</font></td>");
	out.println("	<td><input type='text' name='mobile' value='" + user.getMobile() + "'></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("	<th align='center' colspan='2'>");
	out.println("<input type='button' value='Next' onclick='javascript: validateContactInfoForm()'>");
	out.println("</th>");
	out.println("</tr>");
	
	out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp"/>