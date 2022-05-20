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
		
		String htmlText = "<div style='text-align: center;'>";
		htmlText += "<a href='http://localhost:8040'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>";
		htmlText += "<div style='margin: 0 auto; margin-top: 20px; border-top: 1px solid gray; border-bottom: 1px solid gray; width: 470px; padding: 20px 0; font-size: 12px;'>";
		htmlText += "<p style='font-size: 14px; font-weight: bold; margin-bottom: 10px;'>모헬[모두의 헬스]에서 보낸 이메일 확인을 위한 인증번호 입니다.</p>";
		htmlText += "<p>아래의 인증번호 6자리를 입력하여 이메일 인증을 완료해 주세요</p>";
		htmlText += "<h1 style='margin: 20px 0;'>"+key+"</h1>";
		htmlText += "<p style='font-weight: bold;'>개인정보 보호를 위해 인증번호는 10분 동안만 유효합니다.</p>";
		htmlText += "</div>";
		htmlText += "<p style='margin-top: 60px;'>&#169; 2022 Mohel. All rigths reserved.</p>";
		htmlText += "</div>";
		
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