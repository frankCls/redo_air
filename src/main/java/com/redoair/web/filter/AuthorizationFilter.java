package com.redoair.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.redoair.domain.Role;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml", "*.jsf" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();

			if (reqURI.indexOf("/index.jsf") >= 0 || reqURI.indexOf("/login.jsf") >= 0 || reqURI.indexOf("/searchResults.jsf") >= 0 || reqURI.indexOf("/details.jsf") >= 0
					|| reqURI.indexOf("/register.jsf") >= 0 || (reqURI.indexOf("/partner.jsf") < 0 && ses != null
							&& ses.getAttribute("userName") != null && ses.getAttribute("role") == Role.PAYER)
					|| reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else if ((reqURI.indexOf("/partner.jsf") >= 0 && ses != null && ses.getAttribute("userName") != null
					&& ses.getAttribute("role") == Role.PARTNER) || reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);

			else
				resp.sendRedirect(reqt.getContextPath() + "/login.jsf");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}

}
