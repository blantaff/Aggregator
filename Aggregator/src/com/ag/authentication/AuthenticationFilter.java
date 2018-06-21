package com.ag.authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class AuthenticationFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig config;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		String auth_key = (String) ((HttpServletRequest) req).getSession().getAttribute("Auth_Key");

		if (auth_key == "valid") {
			chain.doFilter(req, resp);
		}else {
			((HttpServletResponse) resp).sendRedirect("/Aggregator/login.xhtml");
		}
		
	}
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	public void destroy() {
		config = null;
	}
}