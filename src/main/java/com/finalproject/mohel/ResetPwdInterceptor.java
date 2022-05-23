package com.finalproject.mohel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class ResetPwdInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String authCode = null;
		if((String)session.getAttribute("authCode") != null) {
			session.setAttribute("expireSession", (long)session.getMaxInactiveInterval()-(System.currentTimeMillis()/1000-(long)session.getAttribute("setSessionTimeStamp")));
			authCode = (String)session.getAttribute("authCode");
		}
		if(authCode!=null) {
			return true;
		}else {
			response.sendRedirect("/member/login");
			return false;
		}
	}
}
