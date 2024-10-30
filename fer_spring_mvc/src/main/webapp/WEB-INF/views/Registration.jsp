<html>
<head>
<title>Registration.jsp</title>

<script>
	function validateRegistrationForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.RegistrationForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.firstName.value.trim() == '') {
			errors += 'Please enter First Name<br>';
		}
		if (form.lastName.value.trim() == '') {
			errors += 'Please enter Last Name<br>';
		}
		if (form.email.value.trim() == '') {
			errors += 'Please enter Email<br>';
		}
		if (form.userName.value.trim() == '') {
			errors += 'Please enter User Name<br>';
		}
		if (form.password.value.trim() == '') {
			errors += 'Please enter Password<br>';
		}
		if (form.mobile.value.trim() == '') {
			errors += 'Please enter Mobile Number<br>';
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

	function isUsernameAvailable(userName) {
		// 1.
		var xhttp = new XMLHttpRequest();

		// 4.
		xhttp.onload = function() {
			//alert(this.responseText.trim());

			var message = '';
			var colorName = '';

			if (this.responseText.trim() == 'Y') {
				message = "Username is available...!";
				colorName = "green";
			} else {
				message = "Username is not available...!";
				colorName = "red";
			}

			// Get td element and write message to the same
			var emailTdElement = document.getElementById('emailTdId');
			emailTdElement.innerHTML = message;

			// Get the tr element. Apply color to the font and show tr element
			var emailTrElement = document.getElementById('emailTrId');
			emailTrElement.style.color = colorName;
			emailTrElement.style.display = '';

		}

		// 2.
		var url = "isUsernameAvailable?userName=" + userName.trim();
		xhttp.open("GET", url, true);

		// 3.
		xhttp.send();
	}
</script>
</head>

<body>

	<form action="registration" method="post" name="RegistrationForm">
		<table align="center" border="2">
			<tr>
				<th align="center" colspan="2">Registration</th>

			</tr>

			<tr style='display: none; color: red' id='errorTrId'>
				<td align="left" colspan="2" id='errorTdId'></td>

			</tr>

			<tr>
				<td align="center">First Name<font color="red">*</font>
				</td>
				<td align="center"><input type="text" name="firstName"></td>
			</tr>

			<tr>
				<td align="center">Middle Name</td>
				<td align="center"><input type="text" name="middleName"></td>
			</tr>

			<tr>
				<td align="center">Last Name<font color="red">*</font></td>
				<td align="center"><input type="text" name="lastName"></td>
			</tr>

			<tr>
				<td align="center">Email<font color="red">*</font></td>
				<td align="center"><input type="text" name="email"></td>
			</tr>

			<tr>
				<td align="center">User Name<font color="red">*</font></td>
				<td align="center"><input type="text" name="userName"
					onchange="javascript: isUsernameAvailable(this.value)"></td>
			</tr>

			<tr style='display: none;' id='emailTrId'>
				<td align="left" colspan="2" id='emailTdId'></td>

			</tr>

			<tr>
				<td align="center">Password<font color="red">*</font></td>
				<td align="center"><input type="password" name="password"></td>
			</tr>

			<tr>
				<td align="center">Mobile Number<font color="red">*</font></td>
				<td align="center"><input type="text" name="mobile"></td>
			</tr>

			<tr>
				<td align="center" colspan="2"><input type="button"
					value="Register" onclick="javascript: validateRegistrationForm()">
			</tr>




		</table>


	</form>

</body>


</html>