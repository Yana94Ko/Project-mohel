package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.VO.ExerciseReplyVO;


public interface ExerciseReplyService {
	//댓글등록
	public int exerciseReplyWrite(ExerciseReplyVO vo);
	//댓글목록
	public List<ExerciseReplyVO> exerciseReplyList(int no);
	//댓글수정
	public int exerciseReplyEdit(ExerciseReplyVO vo);
	//댓글삭제
	public int exerciseReplyDel(int no, String nickname);	
}