<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%! FERService ferService = new FERServiceImpl(); %>

<%
		// handle the request
	// Header and left frame
	
	// 1. get the input from UI
	
	String newPassword = request.getParameter("newPassword");
	String currentPassword = request.getParameter("currentPassword");
	
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	
	// 2. Call the service for business logic execution
	boolean isResetPassword = ferService.resetPassword(newPassword, userId, currentPassword);
	
	// 3. Display the status
	
	// Body
	if (isResetPassword) {
		session.setAttribute("status", "Password Updation is Successful...!");
	} else {
		session.setAttribute("status", "Password Updation is faild ...!");
	}
%>

<jsp:include page="Dashboard.jsp"/>