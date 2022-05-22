package com.finalproject.mohel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.finalproject.mohel.vo.MemberVO;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();

		if ((MemberVO)session.getAttribute("userInfo") != null) {
			return true;
		} else {
			response.sendRedirect("/member/login");
			return false;
		}
	}
}
