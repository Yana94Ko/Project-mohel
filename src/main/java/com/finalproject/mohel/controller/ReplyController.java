package com.finalproject.mohel.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.mohel.service.ReplyService;
import com.finalproject.mohel.vo.MemberVO;
import com.finalproject.mohel.vo.ReplyVO;

@RestController
@RequestMapping("/mohel")
public class ReplyController {
	@Inject
	ReplyService service;
	
	//댓글 등록
	@RequestMapping(value="/reply/writeOk", method=RequestMethod.POST)
	public int writeOk(ReplyVO vo, HttpSession session) {
		System.out.println(vo.getBoardNo());
		MemberVO mvo = (MemberVO)session.getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());
		return service.replyWrite(vo);
	}	
	//댓글 목록 가져오기
	@RequestMapping("/reply/list")
	public List<ReplyVO> list(int boardNo){
		return service.replyList(boardNo);
	}
	//댓글 수정
	@PostMapping("/reply/editOk")
	public int editOk(ReplyVO vo, HttpSession session) {
		MemberVO mvo = (MemberVO)session.getAttribute("userInfo");
		vo.setNickname(mvo.getNickname());
		return service.replyEdit(vo);
	}
	//댓글 삭제
	@GetMapping("/reply/del")
	public int delOk(int no, HttpSession session) {
		MemberVO mvo = (MemberVO)session.getAttribute("userInfo");
		System.out.println(mvo.getNickname() + no);
		return service.replyDel(no, mvo.getNickname());
	}
}