package com.food.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println("weeeeeeeeeeeeeeeeeeee");
		if(roles.contains("ROLE_ADMIN"))
		{
			response.sendRedirect("/admin/admin-dashboard");
		}
		else
		{   System.out.println("fffffffffffffffffffff");
			response.sendRedirect("/user/user-dashboard");
		}
	}

}
