package com.finalproject.mohel.controller;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.finalproject.mohel.MohelApplication;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.service.MypageService;
import com.finalproject.mohel.vo.MemberVO;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	
	@Inject
	MypageService service;
	@Inject
	MemberService memberService;
	
	ModelAndView mav = new ModelAndView();
	HttpHeaders headers = new HttpHeaders();
	ResponseEntity<String> entity = null;

	@GetMapping("userEdit")
	public String userEdit() {
		return "/mypage/userEdit";
	}
	
	@PostMapping("userEditOk")
	public ResponseEntity<String> userEditOk(MemberVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
		vo.setEmail(userInfo.getEmail());
		
		MohelApplication.profileImgUpload(vo, request);
		
		headers.add("content-Type", "text/html;charset=utf-8");
		
		String imgRealPath = null;
		if(service.updateUserInfo(vo)==1) {
			if(!((MemberVO)session.getAttribute("userInfo")).getProfile().equals(vo.getProfile())) {
				imgRealPath = session.getServletContext().getRealPath(((MemberVO)session.getAttribute("userInfo")).getProfile());
			}
			session.setAttribute("userInfo", memberService.selectMember(vo));
			
			String body = "<script>";
			body += "alert('회원 정보가 수정되었습니다.');";
			body += "location.href='/mypage/userEdit';";
			body += "</script>";
			entity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}else {
			imgRealPath = session.getServletContext().getRealPath(vo.getProfile());
			
			String body = "<script>";
			body += "alert('회원 정보 수정에 실패하였습니다.\\n비밀번호를 확인해주세요.');";
			body += "history.back();";
			body += "</script>";
			entity = new ResponseEntity<String>(body, headers, HttpStatus.BAD_REQUEST);
		}
		
		File f = new File(imgRealPath);
		if(f.exists()) {
			f.delete();
		}
		
		return entity;
	}
	
	@GetMapping("myComment")
	public String myComment() {
		return "/mypage/myComment";
	}
	
	@GetMapping("myExercise")
	public String myExercise() {
		return "/mypage/myExercise";
	}
	
	@GetMapping("myWrite")
	public String myWrite() {
		return "/mypage/myWrite";
	}
	
	@GetMapping("userDel")
	public String userDel() {
		return "/mypage/userDel";
	}
}
