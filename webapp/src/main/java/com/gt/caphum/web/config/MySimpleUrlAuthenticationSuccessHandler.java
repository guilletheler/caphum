package com.gt.caphum.web.config;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gt.caphum.web.model.usuarios.Usuario;

@Component
public class MySimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		String targetUrl = usuario.getInitPage();
		
		if(targetUrl == null || targetUrl.isEmpty()) {
			targetUrl = "/index.xhtml";
		}

		if (response.isCommitted()) {
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Response has already been committed. Unable to redirect to "
							+ targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
