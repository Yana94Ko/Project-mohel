package com.finalproject.mohel.controller;



import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.service.ExerciseService;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.ExercisePagingVO;
import com.finalproject.mohel.vo.MemberVO;

@Controller
public class HomeController {
	@Inject
	ExerciseService service;
	
	@Inject
	MemberService memberService;
	
	@GetMapping("/")

	public ModelAndView home(ExercisePagingVO pVO, String category, String nickname, HttpSession session, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		if(nickname==null) {
			MemberVO user=(MemberVO)session.getAttribute("userInfo");
			if(user!=null) {
				nickname=user.getNickname();
			}
		}
		
		pVO.setTotalRecord(service.totalRecord(pVO, nickname));
		mav.addObject("lst", service.home_exercise(pVO));
		//System.out.println(category+"/"+service.home_exercise(pVO));
		mav.addObject("pVO", pVO);
		mav.addObject("category", category);
		mav.setViewName("/home");
		
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
		
		return mav;


	}
}

