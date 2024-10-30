<jsp:include page="Layout/Header_LeftFrame.jsp"/>

<script>
	function validateResetPasswordForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.currentPassword.value.trim() == '') {
			errors += 'Please enter Current Password<br>';
		}
		if (form.newPassword.value.trim() == '') {
			errors += 'Please enter New Password<br>';
		}
		if (form.conformPassword.value.trim() == '') {
			errors += 'Please enter Conform Password<br>';
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
			submitForm('resetPassword');
		}
	}
</script>
<table align="center" border="2">
	<tr>
		<th align="center" colspan="2">Update Password</th>

	</tr>
	
	<tr style='display: none; color: red' id='errorTrId'>
		<td align="left" colspan="2" id='errorTdId'></td>

	</tr>

	<tr>
		<td align="center">Current Password<font color="red">*</font></td>
		<td align="center"><input type="password" name="currentPassword"></td>
	</tr>

	<tr>
		<td align="center">New Password<font color="red">*</font></td>
		<td align="center"><input type="password" name="newPassword"></td>
	</tr>

	<tr>
		<td align="center">Conform Password<font color="red">*</font></td>
		<td align="center"><input type="password" name="conformPassword"></td>
	</tr>

	<tr>
		<td align="center" colspan="2">
		<input type="button" value="Update Password" onclick="javascript: validateResetPasswordForm()">
		</td>

	</tr>




</table>

<jsp:include page="Layout/Footer.jsp"/>
