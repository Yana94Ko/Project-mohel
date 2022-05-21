package com.finalproject.mohel.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.MemberVO;

@Controller
public class HomeController {
	
	@Inject
	MemberService memberService;
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for (Cookie c : cookies) {
				if(c.getName().equals("email")) {
					MemberVO vo = new MemberVO();
					vo.setEmail(c.getValue());
					session.setAttribute("userInfo", memberService.selectMember(vo));
				}else {
					session.setAttribute(c.getName(), c.getValue());
				}
			}
		}
		
		return "home";
	}
	
}