<html>
<head>
<title>Login.jsp</title>

<script>
	function validateLoginForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.LoginForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.userName.value.trim() == '') {
			errors += 'Please enter User Name<br>';
		}
		if (form.password.value.trim() == '') {
			errors += 'Please enter Password<br>';
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
			form.submit();
		}
	}
</script>
</head>

<body>

	<form action="login" method="post" name="LoginForm">
		<font color="red">${status }</font>
		<table align="center" border="2">
			<tr>
				<th align="center" colspan="2">Login</th>

			</tr>

			</tr>
			<tr style='display: none; color: red' id='errorTrId'>
				<td align="left" colspan="2" id='errorTdId'></td>

			</tr>

			<tr>
				<td align="center">User Name<font color="red">*</font></td>
				<td align="center"><input type="text" name="userName"></td>
			</tr>

			<tr>
				<td align="center">Password<font color="red">*</font></td>
				<td align="center"><input type="password" name="password"></td>
			</tr>

			<tr>
				<td align="center" colspan="2"><input type="button"
					value="Login" onclick="javascript: validateLoginForm()">
					&nbsp;&nbsp;&nbsp; <a href="showRegistration"> Registration</a></td>

			</tr>




		</table>


	</form>

</body>


</html>