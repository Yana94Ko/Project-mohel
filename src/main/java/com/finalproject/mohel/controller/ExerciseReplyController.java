package com.finalproject.mohel.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.mohel.service.ExerciseReplyService;
import com.finalproject.mohel.vo.ExerciseReplyVO;
import com.finalproject.mohel.vo.MemberVO;

@RestController
public class ExerciseReplyController {
	@Inject
	ExerciseReplyService service;
	
	// 댓글 등록
	@RequestMapping(value="/exercise/exerciseReplyWriteOk", method=RequestMethod.POST)
	public int exerciseReplyWriteOk (ExerciseReplyVO vo, HttpSession session, HttpServletRequest request) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());

		return service.exerciseReplyWrite(vo);
	}
	// 댓글목록
	@RequestMapping("/exercise/exerciseReplyList")
	public List<ExerciseReplyVO> exerciseReplyList(int no) {
		System.out.println(no);
		List<ExerciseReplyVO> lst=service.exerciseReplyList(no);
		System.out.println(lst.size());
		return lst;
	}

	// 댓글수정
	@PostMapping("/exercise/exerciseReplyEditOk")
	public int exerciseReplyEditOk(ExerciseReplyVO vo, HttpSession session, HttpServletRequest request) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());
		return service.exerciseReplyEdit(vo);
	}

	// 댓글삭제
	@GetMapping("/exercise/exercisegReplyDel")
	public int exerciseReplyDelOk(int no, HttpSession session, HttpServletRequest request) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("userInfo");
		//System.out.println(no+", "+mvo.getNickname());
		return service.exerciseReplyDel(no, mvo.getNickname());
	}
}