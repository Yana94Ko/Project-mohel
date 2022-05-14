package com.finalproject.mohel.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.mohel.vo.MemberVO;

@Controller
@RequestMapping("/mypage/")
public class MypageController {

	@GetMapping("userEdit")
	public String userEdit() {
		return "/mypage/userEdit";
	}
	
	@PostMapping("userEditOk")
	@ResponseBody
	public String userEditOk(MemberVO vo, HttpServletRequest request) {
		
		return "redirect:login";
	}
	
	@PostMapping("checkPwd")
	@ResponseBody
	public int checkPwd(@RequestParam HashMap<String, Object> vo, HttpSession session, MultipartFile profileImg) {
//		vo.setEmail((String)session.getAttribute(""));
		MemberVO mem = (MemberVO)session.getAttribute("userInfo");
		System.out.println(mem);
		System.out.println(vo);
		
		System.out.println(profileImg.getOriginalFilename());
		
		return 0;
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
