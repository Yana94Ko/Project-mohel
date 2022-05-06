package com.finalproject.mohel.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.mohel.VO.ExerciseReplyVO;
import com.finalproject.mohel.service.ExerciseReplyService;

@RestController
public class ExerciseReplyController {
	@Inject
	ExerciseReplyService service;
	
	
	// 댓글 등록
	@RequestMapping(value="/exercise/exerciseReplyWriteOk", method=RequestMethod.POST)
	public int exerciseReplyWriteOk (ExerciseReplyVO vo, HttpSession session) {
		vo.setNickname((String)session.getAttribute("nickName"));
		return service.exerciseReplyWrite(vo);
	}
	// 댓글목록
	@RequestMapping("/exercise/exerciseReplyList")
	public List<ExerciseReplyVO> ridingReplyList(int no) {
		return service.exerciseReplyList(no);
	}

	// 댓글수정
	@PostMapping("/exercise/exerciseReplyEditOk")
	public int exerciseReplyEditOk(ExerciseReplyVO vo, HttpSession session) {
		vo.setNickname((String) session.getAttribute("nickName"));
		return service.exerciseReplyEdit(vo);
	}

	// 댓글삭제
	@GetMapping("/exercise/exercisegReplyDel")
	public int exerciseReplyDelOk(int no, HttpSession session) {
		return service.exerciseReplyDel(no, (String) session.getAttribute("nickName"));
	}
}