package com.finalproject.mohel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.mohel.vo.PagingVO;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	//게시판
	@GetMapping("adminMain")
	public ModelAndView adminMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/adminMain");
		return mav;
	}
}
