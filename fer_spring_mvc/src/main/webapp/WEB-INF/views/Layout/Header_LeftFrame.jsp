<html>
<head>
<title>Dashboard.html</title>

<script>
	function submitForm(path) {

		// get the form object
		var form = document.DashboardForm;

		// Load the path into form object
		form.action = path;

		// Submit the form object
		form.submit();

	}
</script>
</head>

<body>
	<form name="DashboardForm" method="post">
		<table align="center" border="2" height="550px" width="800px">
			<tr height="100px">
				<td align="center" colspan="2">Family Expense Report
					&nbsp;&nbsp;&nbsp;&nbsp; User: ${userName}
					</td>

			</tr>

			<tr>
				<td align="center" width="150px"><br> <a
					href="javascript: submitForm('showAddExpense')">Add Expense</a>
					<br> <br> <a
					href="javascript: submitForm('showEditExpenseOptions')">Edit
						Expense</a><br> <br> <a
					href="javascript: submitForm('showDeleteExpenseOptions')">Delete
						Expense</a><br> <br> <a
					href="javascript: submitForm('showExpenseReport')">Expense
						Report</a><br> <br> <a
					href="javascript: submitForm('showResetPassword')">Reset
						Password</a><br> <br> <a
					href="javascript: submitForm('showNameInfo')">Update Profile</a><br>
					<br> <a href="">Log Out</a><br></td>
				<td align="center">