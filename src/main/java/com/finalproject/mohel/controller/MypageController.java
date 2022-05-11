package com.finalproject.mohel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	
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
	
	@GetMapping("userEdit")
	public String userEdit() {
		return "/mypage/userEdit";
	}
	
	@GetMapping("userDel")
	public String userDel() {
		return "/mypage/userDel";
	}
}
