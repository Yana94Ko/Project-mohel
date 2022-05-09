package com.finalproject.mohel.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finalproject.mohel.service.Certified;
import com.finalproject.mohel.service.KakaoAPI;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.MemberVO;

@RestController
@RequestMapping("/member/")
public class MemberController {
	
	@Inject
	MemberService service;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	Certified certified;
	@Autowired
	KakaoAPI kakao;
	
	ModelAndView mav = new ModelAndView();

	// 회원가입
	@GetMapping("signup")
	public ModelAndView signup() {
		mav.clear();
		mav.setViewName("/member/signup");
		return mav;
	}
	
	@PostMapping("signupOk")
	public ModelAndView signupOk(MemberVO vo, HttpServletRequest request) {
		
//		배포시 폴더경로 변경
//		String path = "D:\\study\\Multi Campus\\Project-mohel\\profile";
		String path = request.getSession().getServletContext().getRealPath("/img/profile");
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		MultipartFile file = mr.getFile("filename");
		if(!file.getOriginalFilename().equals("")) {
			String orgFileName = file.getOriginalFilename();
			int point = orgFileName.lastIndexOf(".");
			String ext = orgFileName.substring(point+1);
			
			File f = new File(path, System.currentTimeMillis()+"."+ext);
			
			orgFileName = f.getName();
			
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			vo.setProfile(orgFileName);
		}else {
			vo.setProfile("defaultProfile.png");
		}
		
		service.insertMember(vo);
		
		mav.setViewName("redirect:login");
		return mav;
	}
	
	@GetMapping("userEdit")
	public ModelAndView userEdit() {
		mav.setViewName("/member/userEdit");
		return mav;
	}
	
	@PostMapping("searchEmail")
	public int searchEmail(String email) {
		return service.searchEmail(email);
	}
	
	@PostMapping("checkMail")
	public String checkMail(String email) {
		return certified.checkMail(email, javaMailSender);
	}
	
	@GetMapping("searchNickname")
	public int searchNickname(String nickname) {
		return service.searchNickname(nickname);
	}
	
	@PostMapping("sendSMS")
	public String sendSMS(String tel) {
		return certified.sendSMS(tel);
	}
	
	
	// 로그인
	@GetMapping("login")
	public ModelAndView login(RedirectAttributes ra) {
		mav.setViewName("/member/login");
		return mav;
	}
	
	@PostMapping("loginOk")
	public ModelAndView loginOk(HttpSession session, MemberVO vo, RedirectAttributes redirect) {
		MemberVO userInfo = service.selectMember(vo);
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}else {
			redirect.addFlashAttribute("email", vo.getEmail());
			redirect.addFlashAttribute("nologin", true);
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@GetMapping("kakaologin")
	public ModelAndView kakaologin(String code, HttpSession session, RedirectAttributes redirect) {
		MemberVO kakaoVO = new MemberVO(kakao.getUserInfo(kakao.getAccessToken(code)));
		MemberVO userInfo = service.selectMember(kakaoVO);
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			mav.setViewName("redirect:/");
		}else {
			
		}
		
		return mav;
	}
	
}
