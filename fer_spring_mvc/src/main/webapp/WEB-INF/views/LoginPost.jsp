
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
		// handle the request
	
	// 1. get the input from UI
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	
	// 2. Call the service for business logic execution
	int userId = ferService.login(userName, password);
	
	// 3. Display the status
	String path = null;
	if (userId > 0) {
	
		session.setAttribute("userId", userId);
		session.setAttribute("userName", userName);
	
		// Header and left frame
	
		// Body
		session.setAttribute("status", "Welcome to user: " + userName + "...!");
		path = "Dashboard.jsp";
	} else {
		out.println("Incorrect username/Password please try again...!");
		path = "Login.jsp";
	
	}
%>

<jsp:include page="<%=path %>" />