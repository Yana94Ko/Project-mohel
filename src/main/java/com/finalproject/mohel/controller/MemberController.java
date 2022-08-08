package com.finalproject.mohel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finalproject.mohel.MohelApplication;
import com.finalproject.mohel.service.Certified;
import com.finalproject.mohel.service.KakaoAPI;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberController{
	
	@Inject
	MemberService service;
	@Inject
	Certified certified;
	@Inject
	KakaoAPI kakao;
	
	ModelAndView mav = new ModelAndView();
	HttpHeaders headers = new HttpHeaders();
	ResponseEntity<String> entity = null;
	
	class MailSender implements Runnable{
		private String subject;
		private String text;
		private String email;

		public MailSender(String subject, String text, String email) {
			this.subject=subject;
			this.text=text;
			this.email=email;
		}
		
		@Override
		public void run() {
			certified.sendMail(subject, text, email);
		}

		@Override
		public String toString() {
			return "MailSender [subject=" + subject + ", text=" + text + ", email=" + email + "]";
		}
	}

	// 회원가입
	@GetMapping("signup")
	public String signup() {
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
	
	// 이메일 인증
	@PostMapping("checkMail")
	@ResponseBody
	public String checkMail(String email) {
		String subject="모헬[모두의 헬스] 메일 인증번호를 알려드립니다.";
		
		String key = String.format("%06d", (int)(Math.random()*1000000));
		StringBuffer htmlText = new StringBuffer();
		htmlText.append("<div style='text-align: center;'>");
//		htmlText.append("<a href='http://localhost:8040'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>");
		htmlText.append("<a href='http://43.200.8.117/'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>");
		htmlText.append("<div style='margin: 0 auto; margin-top: 20px; border-top: 1px solid gray; border-bottom: 1px solid gray; width: 470px; padding: 20px 0; font-size: 12px;'>");
		htmlText.append("<p style='font-size: 14px; font-weight: bold; margin-bottom: 10px;'>모헬[모두의 헬스]에서 보낸 이메일 확인을 위한 인증번호 입니다.</p>");
		htmlText.append("<p>아래의 인증번호 6자리를 입력하여 이메일 인증을 완료해 주세요</p>");
		htmlText.append("<h1 style='margin: 20px 0;'>"+key+"</h1>");
		htmlText.append("<p style='font-weight: bold;'>개인정보 보호를 위해 인증번호는 10분 동안만 유효합니다.</p>");
		htmlText.append("</div>");
		htmlText.append("<p style='margin-top: 20px;'>&#169; 2022 Mohel. All rigths reserved.</p>");
		htmlText.append("</div>");
		
		new Thread(new MailSender(subject, htmlText.toString(), email)).start();
		
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
	public String loginOk(HttpServletResponse response, HttpSession session, MemberVO vo, RedirectAttributes redirect) throws IOException {
		MemberVO userInfo = service.selectMember(vo);
		//System.out.println("회원상태"+userInfo.getVerify());
		if(userInfo!=null) {
			//블랙회원 로그인시
			if(userInfo.getVerify()==9) {
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('정지된 회원입니다. 정지사유는 관리자에게 문의하세요.');location.href='/member/login';</script>");
				out.flush();
				return "redirect:login";
			}
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("verify", userInfo.getVerify());
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			String currentTime = format1.format (System.currentTimeMillis());
			vo.setRecentvisit(currentTime);
			vo.setNickname(userInfo.getNickname());
			service.setRecentvisit(vo);
			setLogCookie(response, session);
			//관리자 로그인시
			if(userInfo.getVerify()==1) {
				return "redirect:/admin/adminMain";
			}else {
				return "redirect:/";
			}
		}else {
			redirect.addFlashAttribute("email", vo.getEmail());
			redirect.addFlashAttribute("nologin", true);
			return "redirect:login";
		}
	}
	
	@GetMapping("kakaologin")
	public String kakaologin(HttpServletResponse response, String code, HttpSession session, HttpServletResponse res,  RedirectAttributes redirect)throws IOException {
		JSONObject tokenJson = kakao.getToken(code);
		String accessToken = tokenJson.getString("access_token");
		String refreshToken = tokenJson.getString("refresh_token");
		
		MemberVO kakaoVO = new MemberVO(kakao.getUserInfo(accessToken));
		MemberVO userInfo = service.selectMember(kakaoVO);
		
		if(userInfo!=null) {
			//블랙회원 로그인시
			if(userInfo.getVerify()==9) {
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('정지된 회원입니다. 정지사유는 관리자에게 문의하세요.');location.href='/member/login';</script>");
				out.flush();
				return "redirect:login";
			}
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("refreshToken", refreshToken);
			session.setAttribute("kakao", "true");
			setLogCookie(res, session);
			if(userInfo.getVerify()==1) {
				return "redirect:/admin/adminMain";
			}else {
				return "redirect:/";
			}
		}else {
			redirect.addFlashAttribute("kakaoVO", kakaoVO);
			return "redirect:signup";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		removeCookies(req, res);
		session.invalidate();
		return "redirect:/";
	}
	
	// 비밀번호 재설정
	@GetMapping("resetPwdCertifiedMail")
	public String resetPwdCertifiedMail() {
		return "/member/resetPwdCertifiedMail";
	}
	
	@PostMapping("changePwdSendMail")
	public ResponseEntity<String> changePwdSendMail(String email, HttpSession session, HttpServletResponse res) throws NoSuchAlgorithmException {
		headers.add("content-Type", "text/html;charset=utf-8");

		StringBuffer body = new StringBuffer();
		if(service.searchEmail(email)==1) {
			// 인증 코드 생성
			String codeListString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-^*?~$@%";
			StringBuffer code = new StringBuffer();
			for(int i=0; i<32; i++) {
				code.append(codeListString.charAt((int)(Math.random()*(codeListString.length()-1))));
			}
			
			session.setAttribute("email", email);
			session.setAttribute("authCode", code.toString());
			session.setAttribute("setSessionTimeStamp", System.currentTimeMillis()/1000);
			session.setMaxInactiveInterval(1800);
			
			String subject="모헬[모두의 헬스] 비밀번호 변경";
			StringBuffer htmlText = new StringBuffer();
			htmlText.append("<div style='text-align: center;'>");
//			htmlText.append("<a href='http://localhost:8040'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>");
			htmlText.append("<a href='http://43.200.8.117/'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>");
			htmlText.append("<div style='margin: 0 auto; margin-top: 20px; border-top: 1px solid gray; border-bottom: 1px solid gray; width: 470px; padding: 20px 0; font-size: 12px;'>");
			htmlText.append("<p style='font-size: 14px; font-weight: bold; margin-bottom: 10px;'>안녕하세요 회원님 비밀번호를 재설정 하시겠어요?</p>");
			htmlText.append("<p>비밀번호를 재설정 하길 원하시면 아래 버튼을 클릭해주세요!</p>");
			htmlText.append("<p>메일이 온 시간부터 30분 내로 비밀번호를 변경 해주세요.</p>");
//			htmlText.append("<div style='margin-top: 30px; margin-bottom: 10px;'><a href='http://localhost:8040/member/codeCheck?authCode="+code.toString()+"' style='background-color: #01c9c6; color: white; border-radius: 5px; padding: 10px 40px; font-size: 16px; font-weight: bold; text-decoration: none;'>비밀번호 재설정</a></div>");
			htmlText.append("<div style='margin-top: 30px; margin-bottom: 10px;'><a href='http://43.200.8.117/member/codeCheck?authCode="+code.toString()+"' style='background-color: #01c9c6; color: white; border-radius: 5px; padding: 10px 40px; font-size: 16px; font-weight: bold; text-decoration: none;'>비밀번호 재설정</a></div>");
			htmlText.append("</div>");
			htmlText.append("<p style='margin-top: 60px;'>&#169; 2022 Mohel. All rigths reserved.</p>");
			htmlText.append("</div>");
			new Thread(new MailSender(subject, htmlText.toString(), email)).start();
			
			body.append("<script>");
			body.append("alert('비밀번호 재설정 메일을 보냈습니다\\n30분 내로 변경해주세요.');");
			body.append("location.href='/member/login';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.OK);
		}else {
			body.append("<script>");
			body.append("alert('해당 메일로 가입된 계정이 없습니다.');");
			body.append("history.back();");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("codeCheck")
	public ResponseEntity<String> codeCheck(String authCode, HttpServletRequest req, RedirectAttributes redirect) throws URISyntaxException {
		HttpSession session = req.getSession();
		String sessionCode = (String)session.getAttribute("authCode");
		
		headers.add("content-Type", "text/html;charset=utf-8");
		if(sessionCode!=null && sessionCode.equals(authCode)
		&& (long)session.getAttribute("expireSession")>0){
			URI resetPwdUri = new URI("resetPwd");
			redirect.addFlashAttribute("redirectAuthCode", authCode);
			headers.setLocation(resetPwdUri);
			entity = new ResponseEntity<String>(headers, HttpStatus.SEE_OTHER);
		}else {
			session.invalidate();
			StringBuffer body = new StringBuffer();
			body.append("<script>");
			body.append("alert('이메일 인증을 다시 진행해주세요.');");
			body.append("location.href='/member/login';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("resetPwd")
	public ModelAndView resetPwd(HttpSession session) {
		if((long)session.getAttribute("expireSession")>0) {
			mav.setViewName("/member/resetPwd");
		}else {
			session.invalidate();
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	
	@PostMapping("resetPwdOk")
	public ResponseEntity<String> resetPwdOk(String authCode, String pwd, HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		StringBuffer body = new StringBuffer();
		headers.add("content-Type", "text/html;charset=utf-8");
		if(email!=null && authCode.equals((String)session.getAttribute("authCode"))
		&& (long)session.getAttribute("expireSession")>0) {
			MemberVO vo = new MemberVO();
			vo.setEmail(email);
			vo.setPwd(pwd);
			service.updatePwd(vo);
			
			body.append("<script>");
			body.append("alert('비밀번호가 변경되었습니다.');");
			body.append("location.href='/member/login';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.OK);
		}else {
			body.append("<script>");
			body.append("alert('이메일 인증을 다시 진행해주세요.');");
			body.append("location.href='/member/login';");
			body.append("</script>");
			entity = new ResponseEntity<String>(body.toString(), headers, HttpStatus.BAD_REQUEST);
		}
		session.invalidate();
		
		return entity;
	}
	
	// 쿠키 생성
	private void setLogCookie(HttpServletResponse res, HttpSession session) {
		Iterator<String> it = session.getAttributeNames().asIterator();
		while(it.hasNext()) {
			String sessionAttributeName = it.next();
			Cookie c = null;
			if(sessionAttributeName.equals("userInfo")) {
				c = new Cookie("email", ((MemberVO)session.getAttribute(sessionAttributeName)).getEmail());
			}else {
				c = new Cookie(sessionAttributeName, session.getAttribute(sessionAttributeName).toString());
			}
			c.setMaxAge(60*60*24*30);
			c.setPath("/");
			res.addCookie(c);
		}
	}
	
	// 쿠키 삭제
	private void removeCookies(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				res.addCookie(cookie);
			}
		}
	}
}