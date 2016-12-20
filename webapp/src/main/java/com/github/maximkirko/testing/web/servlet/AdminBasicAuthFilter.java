package com.github.maximkirko.testing.web.servlet;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.maximkirko.testing.services.IAuthenticationService;
import com.github.maximkirko.testing.web.utils.WebUtils;

public class AdminBasicAuthFilter implements Filter {

	@Inject
	private IAuthenticationService authService;

	@Override
	public void init(FilterConfig config) throws ServletException {
		authService = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext())
				.getBean(IAuthenticationService.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		org.eclipse.jetty.server.Request req = (org.eclipse.jetty.server.Request) request;
		org.eclipse.jetty.server.Response res = (org.eclipse.jetty.server.Response) response;

		String[] credentials = WebUtils.getCredentials(req);

		boolean isCredentialsResolved = credentials != null && credentials.length == 2;
		if (!isCredentialsResolved) {
			res.sendError(401);
			return;
		}

		String username = credentials[0];
		String password = credentials[1];
		if (authService.validateUserRole(username)) {
			chain.doFilter(request, response);
		} else {
			res.sendError(401);
		}

	}

	@Override
	public void destroy() {
	}

}
