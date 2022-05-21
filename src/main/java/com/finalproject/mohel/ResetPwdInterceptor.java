package com.finalproject.mohel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class ResetPwdInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String authCode = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie c : cookies) {
				if(c.getName().equals("authCode") && c.getValue().length()!=0) {
					authCode = c.getValue();
				}
			}
		}
		
		System.out.println("interceptor authCode: "+authCode);
		System.out.println();
		if(authCode!=null) {
			return true;
		}else {
			response.sendRedirect("/member/login");
			return false;
		}
	}
}
