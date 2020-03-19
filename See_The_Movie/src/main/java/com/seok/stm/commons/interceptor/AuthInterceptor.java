package com.seok.stm.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//로그인 인증
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	//경로저장
	private void saveDestination(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("destination : " + (uri + query));
			request.getSession().setAttribute("destination", uri+query);
		}
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute("login") == null) {
			logger.info("current user is not logged");
			saveDestination(request);
			response.sendRedirect(request.getContextPath()+"/user/loginNeed");
			return false;
		}
		
		return true;
	}

}
