<jsp:include page="Layout/Header_LeftFrame.jsp"/>

<script>
	function validateExpenseReportForm() {
		// 1. Get the form object and initiliaze the errors object
		var form = document.DashboardForm;
		var errors = '';

		// 2. Add error to the errors object if the feild values are empty
		if (form.type.value.trim() == '') {
			errors += 'Please enter Expense Type<br>';
		}
		if (form.fromDate.value.trim() == '') {
			errors += 'Please enter From Date<br>';
		}
		if (form.toDate.value.trim() == '') {
			errors += 'Please enter To Date<br>';
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
			submitForm('expenseReport');
		}
	}
</script>

<table align="center" border="2">
	<tr>
		<th align="center" colspan="2">Expense Report</th>

	</tr>
	
	<tr style='display: none; color: red' id='errorTrId'>
		<td align="left" colspan="2" id='errorTdId'></td>

	</tr>

	<tr>
		<td align="center">Expense Type<font color="red">*</font></td>
		<td align="center"><input type="text" name="type"></td>
	</tr>

	<tr>
		<td align="center">From Date<font color="red">*</font></td>
		<td align="center"><input type="text" name="fromDate"></td>
	</tr>

	<tr>
		<td align="center">TO Date<font color="red">*</font></td>
		<td align="center"><input type="text" name="toDate"></td>
	</tr>

	<tr>
		<td align="center" colspan="2">
			<input type='button' value='Expense Report' onclick="javascript: validateExpenseReportForm()">
		</td>

	</tr>




</table>

<jsp:include page="Layout/Footer.jsp"/>
