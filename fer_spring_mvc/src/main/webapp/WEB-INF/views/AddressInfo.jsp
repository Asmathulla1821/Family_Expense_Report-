<%@page import="com.rs.fer.bean.Address"%>
<%@page import="com.rs.fer.bean.User"%>
<jsp:include page="Layout/Header_LeftFrame.jsp" />

<script>
	function validateAddressInfoForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.lineOne.value.trim() == '') {
			errors += 'Please enter Line One<br>';
		}

		if (form.city.value.trim() == '') {
			errors += 'Please enter City<br>';
		}
		if (form.state.value.trim() == '') {
			errors += 'Please enter State<br>';
		}
		if (form.pincode.value.trim() == '') {
			errors += 'Please enter PinCode<br>';
		}
		if (form.country.value.trim() == '') {
			errors += 'Please enter Country<br>';
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
			submitForm('showReview');
		}
	}
</script>

<%
	// Header and Left Frame

// Body

// 1.
User user = (User) session.getAttribute("user");
Address address = user.getAddress();

// 2.
// 3.
out.println("<table align='center' border='2'>");
out.println("<tr>");
out.println("	<th align='center' colspan='2'>Address Info</th>");

out.println("</tr>");

out.println("<tr style='display: none; color: red' id='errorTrId'>");
out.println("<td align='left' colspan='2' id='errorTdId'></td>");

out.println("</tr>");

out.println("<tr>");
out.println("	<td>Line1<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='lineOne' value='" + address.getLineOne() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Line2</td>");
out.println("	<td><input type='text' name='lineTwo' value='" + address.getLineTwo() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>City<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='city' value='" + address.getCity() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>State<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='state' value='" + address.getState() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Pin Code<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='pincode' value='" + address.getPincode() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<td>Country<font color='red'>*</font></td>");
out.println("	<td><input type='text' name='country' value='" + address.getCountry() + "'></td>");
out.println("</tr>");

out.println("<tr>");
out.println("	<th align='center' colspan='2'>");
out.println("<input type='button' value='Next' onclick=\"javascript: validateAddressInfoForm()\">");
out.println("</th>");
out.println("</tr>");

out.println("</table>");
%>

<jsp:include page="Layout/Footer.jsp" />