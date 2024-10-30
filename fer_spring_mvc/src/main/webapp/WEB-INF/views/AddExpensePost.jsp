<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%! FERService ferService = new FERServiceImpl(); %>

<%
		// 1. get the input from UI
	Expense expense = new Expense();
	expense.setType(request.getParameter("type"));
	expense.setDate(request.getParameter("date"));
	expense.setPrice(Float.parseFloat(request.getParameter("price")));
	expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
	expense.setTotal(Float.parseFloat(request.getParameter("total")));
	expense.setByWhom(request.getParameter("byWhom"));
	
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	
	expense.setUserId(userId);
	
	// 2. Call the service for business logic execution
	
	boolean isExpenseAdded = ferService.addExpense(expense);
	
	// 3. Display the status
	
	// Body
	if (isExpenseAdded) {
	
		session.setAttribute("status", "Expens added successful...!");
	} else {
		session.setAttribute("status", "Expens add is faild...!");
	}
%>

<jsp:include page="Dashboard.jsp"/>