<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%! FERService ferService = new FERServiceImpl(); %>

<%
		// 1. get the input from UI
	Expense expense = new Expense();
	expense.setType(request.getParameter("expenseType"));
	expense.setDate(request.getParameter("date"));
	expense.setPrice(Float.parseFloat(request.getParameter("price")));
	expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
	expense.setTotal(Float.parseFloat(request.getParameter("total")));
	expense.setByWhom(request.getParameter("byWhom"));
	
	int expenseId = Integer.parseInt(session.getAttribute("expenseId").toString());
	
	expense.setId(expenseId);
	
	// 2. Call the service for business logic execution
	
	boolean isExpenseEdited = ferService.editExpense(expense);
	
	// 3. Display the status
	
	// Body
	if (isExpenseEdited) {
	
		session.setAttribute("status", "Expens edited successful...!");
	} else {
		session.setAttribute("status", "Expens edit is faild...!");
	}
%>

<jsp:include page="Dashboard.jsp"/>