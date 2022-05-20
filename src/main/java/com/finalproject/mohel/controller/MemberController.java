package com.finalproject.mohel.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finalproject.mohel.MohelApplication;
import com.finalproject.mohel.service.Certified;
import com.finalproject.mohel.service.KakaoAPI;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Inject
	MemberService service;
	@Inject
	Certified certified;
	@Inject
	KakaoAPI kakao;

	// 회원가입
	@GetMapping("signup")
	public String signup(Model model, HttpSession session) {
		Object obj = model.getAttribute("kakaoVO");
		if(obj!=null) {
			session.setAttribute("kakaoVO", obj);
		}else {
			session.invalidate();
		}
		
		return "/member/signup";
	}
	
	@PostMapping("signupOk")
	public String signupOk(MemberVO vo, HttpServletRequest request) {
		MohelApplication.profileImgUpload(vo, request);
		service.insertMember(vo);
		
		return "redirect:login";
	}
	
	@PostMapping("searchEmail")
	@ResponseBody
	public int searchEmail(String email) {
		return service.searchEmail(email);
	}
	
	@PostMapping("checkMail")
	@ResponseBody
	public String checkMail(String email) {
		String subject="모두의 헬스 메일 인증번호를 알려드립니다.";
		String key = String.format("%06d", (int)(Math.random()*1000000));
		String htmlText = "<h3>아래의 인증번호 6자리를 인증번호 입력창에 입력해주세요.</h3>";
		htmlText += "<hr>";
		htmlText += "<h2>"+key+"</h2>";
		htmlText += "<hr>";
		
		certified.sendMail(subject, htmlText, email);
		
		return key;
	}
	
	@GetMapping("searchNickname")
	@ResponseBody
	public int searchNickname(String nickname) {
		return service.searchNickname(nickname);
	}
	
	@PostMapping("sendSMS")
	@ResponseBody
	public String sendSMS(String tel) {
		return certified.sendSMS(tel);
	}
	
	
	// 로그인
	@GetMapping("login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("loginOk")
	public String loginOk(HttpSession session, MemberVO vo, RedirectAttributes redirect) {
		MemberVO userInfo = service.selectMember(vo);
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("verify", userInfo.getVerify());
			return "redirect:/";
		}else {
			redirect.addFlashAttribute("email", vo.getEmail());
			redirect.addFlashAttribute("nologin", true);
			return "redirect:login";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("kakaologin")
	public String kakaologin(String code, HttpSession session, RedirectAttributes redirect) {
		JSONObject tokenJson = kakao.getToken(code);
		String accessToken = tokenJson.getString("access_token");
		String refreshToken = tokenJson.getString("refresh_token");
		
		MemberVO kakaoVO = new MemberVO(kakao.getUserInfo(accessToken));
		MemberVO userInfo = service.selectMember(kakaoVO);
		
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("refreshToken", refreshToken);
			session.setAttribute("kakao", true);
			return "redirect:/";
		}else {
			redirect.addFlashAttribute("kakaoVO", kakaoVO);
			return "redirect:signup";
		}
	}
}