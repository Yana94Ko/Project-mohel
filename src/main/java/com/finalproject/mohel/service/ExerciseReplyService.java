package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.VO.ExerciseReplyVO;


public interface ExerciseReplyService {
	//´ñ±Ûµî·Ï
	public int exerciseReplyWrite(ExerciseReplyVO vo);
	//´ñ±Û¸ñ·Ï
	public List<ExerciseReplyVO> exerciseReplyList(int no);
	//´ñ±Û¼öÁ¤
	public int exerciseReplyEdit(ExerciseReplyVO vo);
	//´ñ±Û»èÁ¦
	public int exerciseReplyDel(int no, String nickname);	
}
