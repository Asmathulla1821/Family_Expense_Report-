package com.rs.fer.servlet.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FERFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("Filter.init()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {

		System.out.println("Filter.doFilter()");

		// 1. To get the parameter names dynamically
		Enumeration<String> parameterNames = request.getParameterNames();

		String parameterName = null;
		String parameterValue = null;
		boolean isSpecialCharPresent = false;

		// 2.
		while (parameterNames.hasMoreElements()) {

			parameterName = parameterNames.nextElement();

			parameterValue = request.getParameter(parameterName);

			if (parameterValue.contains("~")) {

				isSpecialCharPresent = true;

				break;
			}
		}

		if (isSpecialCharPresent) {

			request.getRequestDispatcher("Error/Error.html").forward(request, response);
		} else {
			filterchain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

		System.out.println("Filter.distroy()");
	}
}
