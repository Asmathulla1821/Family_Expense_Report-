
<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%!FERService ferService = new FERServiceImpl();%>
<%
		// handle the request
	
	// 1. get the input from UI
	User user = new User();
	
	user.setFirstName(request.getParameter("firstName"));
	user.setMiddleName(request.getParameter("middleName"));
	user.setLastName(request.getParameter("lastName"));
	user.setEmail(request.getParameter("email"));
	user.setMobile(request.getParameter("mobileNumber"));
	user.setUserName(request.getParameter("userName"));
	user.setPassword(request.getParameter("password"));
	
	// 2. Call the service for business logic execution
	String path = null;
			
	boolean isUsernameAvailable = ferService.isUsernameAvailable(user.getUserName());
	if(isUsernameAvailable){
		boolean isRegister = ferService.registration(user);
		
		// 3. Display the status
		
		
		if (isRegister) {
			out.println("User registration is successful...!");
			path = "Login.jsp";
		} else {
			out.println("User registration is faild...!");
			path = "Registration.jsp";
		
		}
	}else{
		out.println("UserName is not available. Please choose something else...");
		path = "Registration.jsp";
		
	}
%>

<jsp:include page="<%=path %>"/>