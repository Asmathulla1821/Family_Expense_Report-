<%@page import="com.rs.fer.bean.Address"%>
<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@page import="com.rs.fer.service.FERService"%>

<%! FERService ferService = new FERServiceImpl(); %>

<%
		// handle the request
	// Body

		// 1.
		User user = (User) session.getAttribute("user");
		Address address = user.getAddress();

		// 2.
		boolean isUpdated = ferService.updateUser(user);

		// 3.
		if (isUpdated) {
			session.setAttribute("status", "User Updated successful...!");
		} else {
			session.setAttribute("status", "User upgradation is faild...!");
		}
	
%>

<jsp:include page="Dashboard.jsp"/>