<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%! FERService ferService = new FERServiceImpl(); %>

<%
		// handle the request
	
	// 1. get the input from UI
	int id = Integer.parseInt(request.getParameter("expenseId").toString());
	
	// 2. Call the service for business logic execution
	boolean isExpenseDeleted = ferService.deleteExpense(id);
	
	// 3. Display the status
	if (isExpenseDeleted) {
		session.setAttribute("status", "Expens deleted successful...!");
	} else {
		session.setAttribute("status", "Expens delettion is faild...!");
	}
%>

<jsp:include page="Dashboard.jsp"/>