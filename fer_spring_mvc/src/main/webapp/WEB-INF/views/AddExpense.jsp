<jsp:include page="Layout/Header_LeftFrame.jsp" />

<script>
	function validateAddExpenseForm() {
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
			submitForm('addExpense');
		}
	}
</script>
<table align="center" border="2">
	<tr>
		<td align="center" colspan="2">Add Expense</td>

	</tr>


	
	<tr style='display: none; color: red' id='errorTrId'>
		<td align="left" colspan="2" id='errorTdId'></td>

	</tr>

	<tr>
		<td>Expense type<font color="red">*</font></td>
		<td><input type="text" name="type"></td>
	</tr>

	<tr>
		<td>Date<font color="red">*</font></td>
		<td><input type="text" name="date"></td>
	</tr>

	<tr>
		<td>Price<font color="red">*</font></td>
		<td><input type="text" name="price"></td>
	</tr>

	<tr>
		<td>Number Of Items<font color="red">*</font></td>
		<td><input type="text" name="numberOfItems"></td>
	</tr>

	<tr>
		<td>Total<font color="red">*</font></td>
		<td><input type="text" name="total"></td>
	</tr>

	<tr>
		<td>By Whom<font color="red">*</font></td>
		<td><input type="text" name="byWhom"></td>
	</tr>



	<tr>
		<td align='center' colspan='2'><input type='button'
			value='Save Expense' onclick="javascript: validateAddExpenseForm()"></td>
	</tr>




</table>

<jsp:include page="Layout/Footer.jsp" />

