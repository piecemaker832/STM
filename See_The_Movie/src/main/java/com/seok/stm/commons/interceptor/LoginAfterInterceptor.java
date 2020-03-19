package com.seok.stm.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class LoginAfterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath()+"/user/loginAfter");
			return false;
		}
		
		return true;
	}
}
