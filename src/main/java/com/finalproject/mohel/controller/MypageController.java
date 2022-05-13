package com.finalproject.mohel.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.finalproject.mohel.MohelApplication;
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
